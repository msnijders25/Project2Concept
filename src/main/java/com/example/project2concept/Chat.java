package com.example.project2concept;

import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

class Chat {

    String onderwerp;
    String datum;
    ArrayList<String> chatBerichten;

    Chat(){
        chatBerichten = new ArrayList<>();
    }

    public void setOnderwerp(String onderwerp){
        this.onderwerp = onderwerp;
    }

    public String getOnderwerp(){
        return onderwerp;
    }

    public void setDatum(String datum){
        this.datum = datum;
    }

    public String getDatum(){
        return onderwerp;
    }

    public void setChatBerichten(String bericht){
        chatBerichten.add(bericht);
    }

    public ArrayList<String> getChatBerichten(){
        return chatBerichten;
    }
}

