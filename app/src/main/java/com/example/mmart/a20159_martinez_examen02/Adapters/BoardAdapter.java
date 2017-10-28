package com.example.mmart.a20159_martinez_examen02.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mmart.a20159_martinez_examen02.Objects.Board;
import com.example.mmart.a20159_martinez_examen02.Objects.Ladder;
import com.example.mmart.a20159_martinez_examen02.Objects.Snake;
import com.example.mmart.a20159_martinez_examen02.R;

import java.util.ArrayList;

/**
 * Created by MMART on 10/27/2017.
 */
public class BoardAdapter extends ArrayAdapter<Board> {
    public BoardAdapter(Context context) {
        super(context, R.layout.row_board, R.id.textBoard);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView = super.getView(position, convertView, parent);
        TextView txtInfo = (TextView) objectView.findViewById(R.id.textBoard);
        TextView txtSnakes = (TextView) objectView.findViewById(R.id.textSnakes);
        TextView txtLadders = (TextView) objectView.findViewById(R.id.textLadders);

        final Board board = this.getItem(position);
        txtInfo.setText(board.print(position));

        ArrayList<Snake> snakes = board.getSnakes();
        ArrayList<Ladder> ladders = board.getLadders();
        String ss = "Snakes:\n", ls = "Ladders:\n";
        if (snakes != null) {
            for (Snake s : snakes) {
                ss += s.getBegin() + " -> " + s.getEnd() + "\n";
            }
        }
        if (ladders != null) {
            for (Ladder l : ladders) {
                ls += l.getBegin() + " -> " + l.getEnd() + "\n";
            }
        }

        txtLadders.setText(ls);
        txtSnakes.setText(ss);

        return objectView;
    }
}
