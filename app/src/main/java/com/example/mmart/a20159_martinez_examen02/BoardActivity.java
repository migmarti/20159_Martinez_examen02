package com.example.mmart.a20159_martinez_examen02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mmart.a20159_martinez_examen02.Adapters.LadderAdapter;
import com.example.mmart.a20159_martinez_examen02.Adapters.SnakeAdapter;
import com.example.mmart.a20159_martinez_examen02.Objects.Board;
import com.example.mmart.a20159_martinez_examen02.Objects.Ladder;
import com.example.mmart.a20159_martinez_examen02.Objects.Snake;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BoardActivity extends AppCompatActivity {
    TextView txtName, txtAuthor, txtLadderBegin, txtLadderEnd, txtSnakeBegin, txtSnakeEnd;
    Button btnAddLadder, btnAddSnake, btnMoves, btnClear, btnPost;

    SnakeAdapter snakeAdapter;
    ListView listLadder;
    ArrayList<Snake> snakes = new ArrayList<Snake>();

    LadderAdapter ladderAdapter;
    ListView listSnake;
    ArrayList<Ladder> ladders = new ArrayList<Ladder>();

    String url = "http://107.170.247.123:2403/snakes-ladders";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        final RequestQueue queue = Volley.newRequestQueue(this);

        listSnake = (ListView) findViewById(R.id.listSnakes);
        snakeAdapter = new SnakeAdapter(this);
        listSnake.setAdapter(snakeAdapter);

        listLadder = (ListView) findViewById(R.id.listLadders);
        ladderAdapter = new LadderAdapter(this);
        listLadder.setAdapter(ladderAdapter);

        txtName = (TextView) findViewById(R.id.editName);
        txtAuthor = (TextView) findViewById(R.id.editAuthor);
        txtLadderBegin = (TextView) findViewById(R.id.editLadderBegin);
        txtLadderEnd = (TextView) findViewById(R.id.editLadderEnd);
        txtSnakeBegin = (TextView) findViewById(R.id.editSnakeBegin);
        txtSnakeEnd = (TextView) findViewById(R.id.editSnakeEnd);

        btnAddLadder = (Button) findViewById((R.id.buttonAddLadder));
        btnAddSnake = (Button) findViewById((R.id.buttonAddSnake));
        btnMoves = (Button) findViewById((R.id.buttonMoves));
        btnClear = (Button) findViewById((R.id.buttonClear));
        btnPost = (Button) findViewById((R.id.buttonPost));

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ladderAdapter.clear();
                snakeAdapter.clear();
                snakes.clear();
                ladders.clear();
                ladderAdapter.notifyDataSetChanged();
                snakeAdapter.notifyDataSetChanged();
            }
        });

        btnAddLadder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int begin = Integer.parseInt(txtLadderBegin.getText().toString());
                int end = Integer.parseInt(txtLadderEnd.getText().toString());
                if (begin < end && begin > 1 && end < 100) {
                    Ladder ladder = new Ladder();
                    ladder.setBegin(begin);
                    ladder.setEnd(end);
                    ladderAdapter.add(ladder);
                    ladderAdapter.notifyDataSetChanged();
                    ladders.add(ladder);
                }
            }
        });

        btnAddSnake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int begin = Integer.parseInt(txtSnakeBegin.getText().toString());
                int end = Integer.parseInt(txtSnakeEnd.getText().toString());
                if (begin > end && end > 1 && begin < 100) {
                    Snake snake = new Snake();
                    snake.setBegin(begin);
                    snake.setEnd(end);
                    snakeAdapter.add(snake);
                    snakeAdapter.notifyDataSetChanged();
                    snakes.add(snake);
                }

            }
        });

        //Referencia: http://www.geeksforgeeks.org/snake-ladder-problem-2/

        btnMoves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int moves[] = new int[100];
                for (int i = 0; i < moves.length; i++)
                    moves[i] = -1;

                for (Ladder l : ladders) {
                    int start = l.getBegin() - 1;
                    int destination = l.getEnd() - 1;
                    moves[start] = destination;
                }

                for (Snake s : snakes) {
                    int start = s.getBegin() - 1;
                    int destination = s.getEnd() - 1;
                    moves[start] = destination;
                }

                Toast.makeText(getApplicationContext(),
                        "Min Dice throws required is " + getMinDiceThrows(moves, 100),
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ladders.size() > 0 && snakes.size() > 0) {
                    Board board = new Board();
                    board.setName(txtName.getText().toString());
                    board.setAuthor(txtAuthor.getText().toString());
                    board.setLadders(ladders);
                    board.setSnakes(snakes);
                    JSONObject json = board.toJSON();
                    JsonObjectRequest jor = uploadJSON(json, url);
                    queue.add(jor);
                }
            }
        });
    }

    public void onBackPressed() {
        finish();
    }

    static int getMinDiceThrows(int move[], int n)
    {
        int visited[] = new int[n];
        Queue<qentry> q = new LinkedList<>();
        qentry qe = new qentry();
        qe.v = 0;
        qe.dist = 0;
        visited[0] = 1;
        q.add(qe);
        while (!q.isEmpty())
        {
            qe = q.remove();
            int v = qe.v;
            if (v == n - 1)
                break;
            for (int j = v + 1; j <= (v + 6) && j < n; ++j)
            {
                if (visited[j] == 0)
                {
                    qentry a = new qentry();
                    a.dist = (qe.dist + 1);
                    visited[j] = 1;
                    if (move[j] != -1)
                        a.v = move[j];
                    else
                        a.v = j;
                    q.add(a);
                }
            }
        }
        return qe.dist;
    }

    static class qentry
    {
        int v;
        int dist;
    }

    public JsonObjectRequest uploadJSON(final JSONObject jsonBody, String url) {
        final JsonObjectRequest jsonPostRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(),
                                "Successfully uploaded object",
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                "Error uploading: " + error.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        return jsonPostRequest;
    }
}
