package com.example.huzdi.taskkeeper.First;

/**
 * Created by huzdi on 16.10.2017.
 */

public class Task {

    private boolean isComplete = false;
    private String description;


    public Task(){
        this(false);
    }

    private Task(boolean isComplete){
        this.isComplete = isComplete;
    }

    public boolean isCompleted(){
        return isComplete;
    }

    public void setcomplete(boolean isComplete){
        this.isComplete = isComplete;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }


}
