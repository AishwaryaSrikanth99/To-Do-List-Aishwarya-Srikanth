package com.example.android.todolist_as2551;

import java.io.Serializable;

/**
 * Created by Aishwarya Srikanth on 10/1/2017.
 */
//defining functions here
public class todoactivities implements Serializable {
    String taskwhat;
    String taskwhy;
    int x=0;
    public todoactivities()
    {

    }
    public todoactivities(String taskwhat, String taskwhy, int x)
    {
        this.taskwhat = taskwhat;
        this.taskwhy = taskwhy;
        this.x = x;


    }
    public  String getTaskwhat() {
        return  taskwhat;
    }
    public void setTaskwhat(String taskwhat) {
        this.taskwhat=taskwhat;
    }
    public String getTaskwhy(){
        return taskwhy;
    }

    public void setTaskwhy(String taskwhy)
    {
        this.taskwhy = taskwhy;
    }
    public int getValue()
    {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
}
