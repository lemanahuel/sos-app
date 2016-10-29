package com.example.nahuelultrabook.ch_app_v11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("Sobre Coderhouse");
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
                intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                intent = new Intent(AboutActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about_me:
                intent = new Intent(AboutActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
