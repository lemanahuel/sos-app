package com.example.nahuelultrabook.ch_app_v11;

import android.content.Intent;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    private Spinner careersSpinner;
    private ListView lstOpciones;
    private ListView workshopsList;
    final String[] datos = new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
    private ArrayAdapter<String> careerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        careersSpinner = (Spinner)findViewById(R.id.careersSpinner);
        ArrayAdapter<CharSequence> careersAdapter = ArrayAdapter.createFromResource(this, R.array.careers, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> workshopsAdapter = ArrayAdapter.createFromResource(this, R.array.workshops, android.R.layout.simple_list_item_1);
        workshopsList = (ListView)findViewById(R.id.workshopsList);
        workshopsList.setAdapter(workshopsAdapter);

        registerForContextMenu(workshopsList);

        careersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        careersSpinner.setAdapter(careersAdapter);
        careersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                Bundle b = new Bundle();
                if (parent.getSelectedItemId() != 0) {
                    b.putString("career", parent.getSelectedItemId() + "");
                    Intent intent = new Intent(MainActivity.this, CourseActivity.class);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_cursos:
                intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about_me:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_workshop, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.CtxLblOpc1:
                Toast toast = Toast.makeText(getApplicationContext(), "Te pre-inscribiste!", Toast.LENGTH_LONG);
                toast.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
