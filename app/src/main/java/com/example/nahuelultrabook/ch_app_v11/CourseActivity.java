package com.example.nahuelultrabook.ch_app_v11;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    public TextView careerTitle;
    public ListView levelsList;
    public String currentCareerId;
    public String currentCareerLevels;
    public String currentLevelId;
    String[] levelsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Bundle b = this.getIntent().getExtras();
        currentCareerId = b.getString("career");

        levelsList = (ListView)findViewById(R.id.levelsList);

        SqlHelper usdbh = new SqlHelper(CourseActivity.this, null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        if (db != null) {

            String[] fieldsCareers = new String[] {"_id", "title", "levels"};
            String[] argsCareers = new String[] {currentCareerId};

            try {
                Cursor c = db.query("Careers", fieldsCareers, "_id=?", argsCareers, null, null, null);
                if (c.moveToFirst()) {
                    setTitle(c.getString(1));
                    currentCareerLevels = c.getString(2);
                    String levels = currentCareerLevels;
                    Log.d("currentCareerLevels", currentCareerLevels);

                    if (db != null) {
                        String[] fieldsLevels = new String[] {"_id", "title", "enable"};
                        List<String> items = Arrays.asList(levels.split("\\s*,\\s*"));
                        //levelsArray = new String[items.size()];
                        ArrayList<String> levelsArray = new ArrayList<String>();
                        //mylist.add(mystring); //this adds an element to the list.

                        for (int i = 0; i < items.size(); i++) {
                            String[] id = new String[] {items.get(i)};

                            try {
                                c = db.query("Levels", fieldsLevels, "_id=?", id, null, null, null);
                                if (c != null && c.moveToFirst()) {
                                    Log.d(c.getString(0), c.getString(1));
                                    levelsArray.add(c.getString(0) + "-" + c.getString(1) + "-" + c.getString(2));
                                }
                            } catch (Exception ex){
                                Toast toastError = Toast.makeText(getApplicationContext(), "No encontre nada " + ex.toString(), Toast.LENGTH_LONG);
                                toastError.show();
                            }
                        }

                        ArrayAdapter levelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, levelsArray);
                        levelsList.setAdapter(levelAdapter);
                        registerForContextMenu(levelsList);
                    }
                }
            } catch (Exception ex){
                Toast toastError = Toast.makeText(getApplicationContext(), "No encontre nada " + ex.toString(), Toast.LENGTH_LONG);
                toastError.show();
            }
        }

        Button btnCreateLevel = (Button) findViewById(R.id.btnCreateLevel);

        btnCreateLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                TextView fieldLevelTitle = (TextView) findViewById(R.id.fieldLevelTitle);
                CheckBox fieldLevelStatus = (CheckBox) findViewById(R.id.fieldLevelStatus);
                String levelTitle = fieldLevelTitle.getText().toString();
                Boolean levelStatus = fieldLevelStatus.isChecked();

                if (levelTitle.equals("") || fieldLevelStatus.equals("")) {
                    Toast toastMissingData = Toast.makeText(getApplicationContext(), "como que te faltan datos papu", Toast.LENGTH_LONG);
                    toastMissingData.show();
                } else {
                    SqlHelper usdbh = new SqlHelper(CourseActivity.this, null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();

                    ContentValues Level = new ContentValues();
                    Level.put("title", levelTitle);
                    Level.put("enable", !!levelStatus);
                    ContentValues Career = new ContentValues();

                    if (db != null) {
                        try {
                            Cursor c = db.rawQuery("SELECT * FROM Levels", null);
                            Level.put("_id", c.getCount() + 1);
                            db.insert("Levels", null, Level);
                            Career.put("levels", currentCareerLevels + "," + (c.getCount() + 1));
                            db.update("Careers", Career, "_id=" + currentCareerId, null);
                            startActivity(getIntent());
                        } catch (Exception ex){
                            Toast toastError = Toast.makeText(getApplicationContext(), "mmmmalisimo no paso nada " + ex.toString(), Toast.LENGTH_LONG);
                            toastError.show();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(levelsList.getAdapter().getItem(info.position).toString());

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_course, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        SqlHelper usdbh = new SqlHelper(CourseActivity.this, null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        ContentValues valores = new ContentValues();
        Toast toast;
        String[] args;
        String currentLevelId = levelsList.getAdapter().getItem(info.position).toString().split("\\s*-\\s*")[0];

        switch (item.getItemId()) {
            case R.id.CtxLblOpc1:
                valores.put("enable", "false");
                args = new String[]{currentLevelId + ""};
                db.update("Levels", valores, "_id=?", args);

                toast = Toast.makeText(getApplicationContext(), "Deshabilitado!", Toast.LENGTH_LONG);
                toast.show();
                startActivity(getIntent());
                return true;
            case R.id.CtxLblOpc2:
                valores.put("enable", "true");
                args = new String[]{currentLevelId + ""};
                db.update("Levels", valores, "_id=?", args);

                toast = Toast.makeText(getApplicationContext(), "Habilitado!", Toast.LENGTH_LONG);
                toast.show();
                startActivity(getIntent());
                return true;
            case R.id.CtxLblOpc3:
                db.delete("Levels", "_id=" + currentLevelId + "", null);

                toast = Toast.makeText(getApplicationContext(), "Eliminado!", Toast.LENGTH_LONG);
                toast.show();
                startActivity(getIntent());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.action_cursos:
                intent = new Intent(CourseActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                intent = new Intent(CourseActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about_me:
                intent = new Intent(CourseActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
