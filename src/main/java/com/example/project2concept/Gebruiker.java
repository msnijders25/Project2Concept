package com.example.project2concept;

import com.example.project2concept.Chat;

import java.util.ArrayList;

class Gebruiker {

    private String gebruikersNaam;
    private String wachtWoord;

    private ArrayList<Chat> chats;

    Gebruiker(String gebruikersNaam, String wachtWoord){
        this.gebruikersNaam = gebruikersNaam;
        this.wachtWoord = wachtWoord;
    }

    public void setGebruikersNaam(String gebruikersNaam){
        this.gebruikersNaam = gebruikersNaam;
    }
    public String getGebruikersNaam(){
        return gebruikersNaam;
    }
    public void setWachtWoord(String wachtWoord){
        this.wachtWoord = wachtWoord;
    }
    public String getWachtWoord(){
        return wachtWoord;
    }

    public void setChats(Chat chat){
        chats.add(new Chat());
    }


    public ArrayList<Chat> getChats(){
        return chats;
    }
}
//
