package com.example.nahuelultrabook.ch_app_v11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup objectiveRadio;
    CheckBox otherPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Opciones");

        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        Integer objective = prefs.getInt("objective", 1);
        Boolean other = prefs.getBoolean("otherPreference", false);
         objectiveRadio = (RadioGroup)findViewById(R.id.GrbGrupo1);
         otherPreference = (CheckBox)findViewById(R.id.otherPreference);

        objectiveRadio.clearCheck();
        if (objective.equals(1)) {
            objectiveRadio.check(R.id.RbOpcion1);
        } else {
            objectiveRadio.check(R.id.RbOpcion2);
        }
        if (other.equals(true)) {
            otherPreference.setChecked(true);
        }

        Button btnSave = (Button)findViewById(R.id.BtnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("objective", objectiveRadio.getCheckedRadioButtonId());
                editor.putBoolean("otherPreference", otherPreference.isChecked());
                editor.commit();
                Toast toastMissingData = Toast.makeText(getApplicationContext(), "guardardo papu", Toast.LENGTH_LONG);
                toastMissingData.show();
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
        Intent intent;

        switch (item.getItemId()) {
            case R.id.action_cursos:
                intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about_me:
                intent = new Intent(SettingsActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
