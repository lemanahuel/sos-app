package com.example.nahuelultrabook.ch_app_v11;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public Button btnSubmit;
    public Button btnSignin;
    public EditText emailField;
    public EditText passField;
    public String Email;
    public String Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        emailField = (EditText) findViewById(R.id.emailField);
        passField = (EditText) findViewById(R.id.passField);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            Pass = passField.getText().toString();
            Email = emailField.getText().toString();

            if (Email.equals("") || Pass.equals("")) {
                Toast toastMissingData = Toast.makeText(getApplicationContext(), "como que te faltan datos papu", Toast.LENGTH_LONG);
                toastMissingData.show();
            } else {
                SqlHelper usdbh = new SqlHelper(LoginActivity.this, null, 1);
                SQLiteDatabase db = usdbh.getWritableDatabase();
                String[] fields = new String[] {"_id"};
                String[] args = new String[] {Email, Pass};

                if (db != null) {
                    try {
                        Cursor c = db.query("Users", fields, "email=? and password=?", args, null, null, null);
                        if (c.moveToFirst()) {
                            Bundle b = new Bundle();
                            b.putString("UserSession", c.getString(0));
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    } catch (Exception ex){
                        Toast toastError = Toast.makeText(getApplicationContext(), "No te encontre papu " + ex.toString(), Toast.LENGTH_LONG);
                        toastError.show();
                    }
                }
            }
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
            startActivity(intent);
            }
        });
    }
}
