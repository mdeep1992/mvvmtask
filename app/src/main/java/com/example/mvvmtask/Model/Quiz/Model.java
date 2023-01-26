package com.example.mvvmtask.Model.Quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("selectedtext")
    @Expose
    private String selectedtext;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    int position;
    public Model(String question, String selectedtext,int position) {
        this.question = question;
        this.selectedtext = selectedtext;
        this.position = position;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSelectedtext() {
        return selectedtext;
    }

    public void setSelectedtext(String selectedtext) {
        this.selectedtext = selectedtext;
    }



}
