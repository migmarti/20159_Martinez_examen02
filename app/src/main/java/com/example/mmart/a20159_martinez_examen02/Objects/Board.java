package com.example.mmart.a20159_martinez_examen02.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by MMART on 10/27/2017.
 */
public class Board {
    String id;
    String name;
    String author;
    ArrayList<Ladder> ladders;
    ArrayList<Snake> snakes;

    public Board() {

    }

    public String print(int i) {
        String print = "";
        print += "Board Name: " + this.name + "\n";
        print += "Author: " + this.author + "\n";
        return print;
    }



    public ArrayList<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(ArrayList<Snake> snakes) {
        this.snakes = snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(ArrayList<Ladder> ladders) {
        this.ladders = ladders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject= new JSONObject();
        try {
            JSONArray jLadders = new JSONArray();
            for (Ladder l : this.ladders) {
                JSONObject jLadder = new JSONObject();
                jLadder.put("beginning", l.getBegin());
                jLadder.put("end", l.getEnd());
                jLadders.put(jLadder);
            }

            JSONArray jSnakes = new JSONArray();
            for (Snake s : this.snakes) {
                JSONObject jSnake = new JSONObject();
                jSnake.put("beginning", s.getBegin());
                jSnake.put("end", s.getEnd());
                jSnakes.put(jSnake);
            }

            jsonObject.put("name", this.name);
            jsonObject.put("author", this.author);
            jsonObject.put("ladders", jLadders);
            jsonObject.put("snakes", jSnakes);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
