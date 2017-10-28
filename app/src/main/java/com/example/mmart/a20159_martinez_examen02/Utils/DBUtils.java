package com.example.mmart.a20159_martinez_examen02.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MMART on 9/22/2017.
 */
public class DBUtils extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="BoardDB";
    public static final int DATABASE_VERSION = 1;

    public static final String BOARD_TABLE="Boards";
    public static final String BOARD_BASEID = "baseId";
    public static final String BOARD_ID = "id";
    public static final String BOARD_NAME = "name";
    public static final String BOARD_AUTHOR ="author";

    public static final String SNAKE_TABLE ="Snake";
    public static final String SNAKE_BASEID = "baseId";
    public static final String SNAKE_BEGIN = "beginning";
    public static final String SNAKE_END = "end";
    public static final String SNAKE_BOARDID = "boardId";

    public static final String LADDER_TABLE ="Ladders";
    public static final String LADDER_BASEID = "baseId";
    public static final String LADDER_BEGIN = "beginning";
    public static final String LADDER_END = "end";
    public static final String LADDER_BOARDID = "boardId";

    public static final String CREATE_BOARDS =
            "CREATE TABLE "+ BOARD_TABLE + "(" +
                    BOARD_BASEID + " INTEGER PRIMARY KEY, " +
                    BOARD_ID + " TEXT, " +
                    BOARD_NAME + " TEXT, " +
                    BOARD_AUTHOR + " TEXT)";

    public static final String CREATE_SNAKES =
            "CREATE TABLE "+ SNAKE_TABLE + "(" +
                    SNAKE_BASEID + " INTEGER PRIMARY KEY, " +
                    SNAKE_BEGIN +" INTEGER, " +
                    SNAKE_END + " INTEGER" +
                    SNAKE_BOARDID + " TEXT)";

    public static final String CREATE_LADDERS =
            "CREATE TABLE "+ LADDER_TABLE + "(" +
                    LADDER_BASEID + " INTEGER PRIMARY KEY, " +
                    LADDER_BEGIN +" INTEGER, " +
                    LADDER_END + " INTEGER" +
                    LADDER_BOARDID + " TEXT)";

    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOARDS);
        db.execSQL(CREATE_SNAKES);
        db.execSQL(CREATE_LADDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOARD_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + SNAKE_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + LADDER_TABLE + "");
        onCreate(db);
    }
}
