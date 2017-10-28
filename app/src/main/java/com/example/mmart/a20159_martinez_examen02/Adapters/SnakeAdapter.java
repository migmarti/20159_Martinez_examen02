package com.example.mmart.a20159_martinez_examen02.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mmart.a20159_martinez_examen02.Objects.Snake;
import com.example.mmart.a20159_martinez_examen02.R;

/**
 * Created by MMART on 10/27/2017.
 */
public class SnakeAdapter extends ArrayAdapter<Snake> {
    public SnakeAdapter(Context context) {
        super(context, R.layout.row_snake, R.id.textSnake);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView = super.getView(position, convertView, parent);
        TextView txtInfo = (TextView) objectView.findViewById(R.id.textSnake);

        final Snake snake = this.getItem(position);
        txtInfo.setText(snake.print(position));

        return objectView;
    }
}