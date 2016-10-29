package com.example.nahuelultrabook.ch_app_v11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nahuel ultrabook on 10/13/2016.
 */

public class SqlHelper extends SQLiteOpenHelper {

    String sqlCreateUsers = "CREATE TABLE Users (_id INTEGER, email TEXT, password TEXT, name TEXT)";
    String sqlCreateCareers = "CREATE TABLE Careers (_id INTEGER, title TEXT, levels TEXT)";
    String sqlCreateLevels = "CREATE TABLE Levels (_id INTEGER, title TEXT, enable TEXT)";

    public SqlHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "DB", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateUsers);
        db.execSQL(sqlCreateCareers);
        db.execSQL(sqlCreateLevels);

        db.execSQL("INSERT INTO Careers (_id, title, levels) VALUES (1, \"Desarrollador FrontEnd\", \"1,2,3\") ");
        db.execSQL("INSERT INTO Careers (_id, title, levels) VALUES (2, \"Desarrollador FullStack\", \"1,2,4\") ");
        db.execSQL("INSERT INTO Careers (_id, title, levels) VALUES (3, \"Desarrollador Mobile\", \"1,2,5\") ");
        db.execSQL("INSERT INTO Levels (_id, title, enable) VALUES (2, \"Programador Web\", \"true\") ");
        db.execSQL("INSERT INTO Levels (_id, title, enable) VALUES (3, \"Experto FrontEnd\", \"true\") ");
        db.execSQL("INSERT INTO Levels (_id, title, enable) VALUES (4, \"Experto FullStack\", \"true\") ");
        db.execSQL("INSERT INTO Levels (_id, title, enable) VALUES (5, \"Experto Mobile\", \"true\") ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Careers");
        db.execSQL("DROP TABLE IF EXISTS Levels");
        db.execSQL(sqlCreateUsers);
        db.execSQL(sqlCreateCareers);
        db.execSQL(sqlCreateLevels);
    }
}
