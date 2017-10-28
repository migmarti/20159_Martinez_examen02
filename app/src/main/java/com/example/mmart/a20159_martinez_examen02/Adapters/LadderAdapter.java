package com.example.mmart.a20159_martinez_examen02.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mmart.a20159_martinez_examen02.Objects.Ladder;
import com.example.mmart.a20159_martinez_examen02.R;

/**
 * Created by MMART on 10/27/2017.
 */
public class LadderAdapter extends ArrayAdapter<Ladder> {
    public LadderAdapter(Context context) {
        super(context, R.layout.row_ladder, R.id.textLadder);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView = super.getView(position, convertView, parent);
        TextView txtInfo = (TextView) objectView.findViewById(R.id.textLadder);

        final Ladder ladder = this.getItem(position);
        txtInfo.setText(ladder.print(position));

        return objectView;
    }
}
