package com.example.mmart.a20159_martinez_examen02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mmart.a20159_martinez_examen02.Adapters.BoardAdapter;
import com.example.mmart.a20159_martinez_examen02.Objects.Board;
import com.example.mmart.a20159_martinez_examen02.Objects.Ladder;
import com.example.mmart.a20159_martinez_examen02.Objects.Snake;
import com.example.mmart.a20159_martinez_examen02.Utils.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnCreate, btnSync;
    BoardAdapter boardAdapter;
    ListView listBoards;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnSync = (Button) findViewById(R.id.buttonSync);
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://107.170.247.123:2403/snakes-ladders";
        String url2 = "https://api.myjson.com/bins/1dypzn";
        listBoards = (ListView) findViewById(R.id.listBoards);
        boardAdapter = new BoardAdapter(this);
        listBoards.setAdapter(boardAdapter);

        final JsonArrayRequest boardArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String result = "";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        boardAdapter.add(createBoard(response.getString(i)));
                        boardAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getBaseContext(), "GET successful", Toast.LENGTH_SHORT).show();
                System.out.println(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
                startActivity(intent);
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queue.add(boardArrayRequest);
            }
        });

    }

    public Board createBoard(String response) {
        Board board = new Board();
        ArrayList<Ladder> ladders = new ArrayList<>();
        ArrayList<Snake> snakes = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray jSnakes = jsonObject.getJSONArray("snakes");
            JSONArray jLadders = jsonObject.getJSONArray("ladders");

            for (int i = 0; i < jSnakes.length(); i++) {
                Snake s = new Snake();
                JSONObject row = jSnakes.getJSONObject(i);
                s.setBegin(row.getInt("beginning"));
                s.setEnd(row.getInt("end"));
                snakes.add(s);
            }

            for (int i = 0; i < jLadders.length(); i++) {
                Ladder l = new Ladder();
                JSONObject row = jLadders.getJSONObject(i);
                l.setBegin(row.getInt("beginning"));
                l.setEnd(row.getInt("end"));
                ladders.add(l);
            }

            board.setId(jsonObject.getString("id"));
            board.setName(jsonObject.getString("name"));
            board.setAuthor(jsonObject.getString("author"));
            board.setSnakes(snakes);
            board.setLadders(ladders);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return board;
    }
}
