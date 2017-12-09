package com.example.android.todolist_as2551;
//libraries used
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.android.todolist_as2551.R.id.taskname;

public class MainActivity extends AppCompatActivity {

    //declarations
    EditText editName;
    Button additembutton;
    EditText editDes;
    ArrayList<todoactivities> item = new ArrayList<todoactivities>();


    // ArrayAdapter<String> itemadapter;
    ListView lViewItem;
    int i;
    CheckBox checkb;
    CustomAdapter itemadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File f1 = getBaseContext().getFileStreamPath("myfile.txt");
        //raad file
        if (f1.exists()) {
            System.out.println("Found text file!");
            readFile();
        }
        //\finding view ids
        editName = (EditText) findViewById(taskname);
        editDes = (EditText) findViewById(R.id.taskdes);
        additembutton = (Button) findViewById(R.id.addbutton);
        lViewItem = (ListView) findViewById(R.id.listview);
        checkb = (CheckBox) findViewById(R.id.cb1);


        itemadapter = new CustomAdapter(this, item);
        lViewItem.setAdapter(itemadapter);
        //function to perform deletion on long click
        lViewItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                item.remove(position);
                itemadapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_LONG).show();
                return false;
            }

        });
        //function to add task name and task description
        additembutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (editName.getText().toString().equals("") || editDes.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter the details", Toast.LENGTH_SHORT).show();
                } else {

                    todoactivities addTaskDetails = new todoactivities(editName.getText().toString(), editDes.getText().toString(), 0);

                    itemadapter.add(addTaskDetails);
                    editName.setText("");
                    editDes.setText("");
                    //writing data to file
                    writefile();

                }
            }

        });

    }
    //functio to write to file
    public void writefile() {
        try {

            FileOutputStream fout = openFileOutput("myfile.txt", MODE_PRIVATE);
            OutputStreamWriter opwriter = new OutputStreamWriter(fout);
            opwriter.append(editName.getText() + " " + editDes.getText());

            for (todoactivities task : item) {
                String tname = task.getTaskwhat();
                String taskdescrip = task.getTaskwhy();
                opwriter.write(tname + "\t" + taskdescrip + "\n");
            }
            Toast.makeText(getBaseContext(), "Written to file", Toast.LENGTH_SHORT).show();
            opwriter.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    //function to read file
    public void readFile() {
        try {
            Scanner input = new Scanner(openFileInput("myfile.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] lines = line.split("\t");
                System.out.println(lines[0] + " " + lines[1]);
                todoactivities addTask = new todoactivities(lines[0], lines[1], 0);
                item.add(addTask);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        writefile();
    }

    @Override
    public void onSaveInstanceState(Bundle state2) {
        super.onSaveInstanceState(state2);
        state2.putString("task name", editName.getText().toString());
        state2.putString("task description", editDes.getText().toString());
        state2.putSerializable("list", item);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editName.setText(savedInstanceState.getString("editName"));
        editDes.setText(savedInstanceState.getString("editDes"));
        item = (ArrayList<todoactivities>) savedInstanceState.getSerializable("list");
        System.out.println(item.get(0).getTaskwhat());
        itemadapter = new CustomAdapter(this, item);
        lViewItem.setAdapter(itemadapter);

    }
}

