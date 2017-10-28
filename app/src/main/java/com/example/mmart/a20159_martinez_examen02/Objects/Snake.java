package com.example.mmart.a20159_martinez_examen02.Objects;

/**
 * Created by MMART on 10/27/2017.
 */
public class Snake {
    String boardId;
    int begin;
    int end;

    public Snake() {

    }

    public String print(int i) {
        String print = "";
        print += "Snake #" + i + "\n";
        print += "Begin: " + this.begin + "\n";
        print += "End: " + this.end + "\n";
        return print;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
