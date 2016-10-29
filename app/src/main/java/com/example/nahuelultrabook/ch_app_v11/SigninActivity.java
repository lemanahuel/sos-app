package com.example.nahuelultrabook.ch_app_v11;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {

    public Button btnLogin;
    public Button btnSubmit;
    public EditText nameField;
    public EditText emailField;
    public EditText passField;
    public String Email;
    public String Pass;
    public String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        nameField = (EditText) findViewById(R.id.nameField);
        emailField = (EditText) findViewById(R.id.emailField);
        passField = (EditText) findViewById(R.id.passField);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            Name = nameField.getText().toString();
            Email = emailField.getText().toString();
            Pass = passField.getText().toString();

            if (Name.equals("") || Email.equals("") || Pass.equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Dale man ingresa todos los datos", Toast.LENGTH_LONG);
                toast.show();
            } else {
                ContentValues User = new ContentValues();
                SqlHelper usdbh = new SqlHelper(SigninActivity.this, null, 1);
                SQLiteDatabase db = usdbh.getWritableDatabase();

                User.put("_id", 1);
                User.put("name", Name);
                User.put("email", Email);
                User.put("password", Pass);

                try {
                    String[] campos = new String[]{"_id"};
                    String[] args = new String[]{emailField.getText().toString()};
                    Cursor c = db.query("Users", campos, "email=?", args, null, null, null);

                    if (c.moveToFirst()) {
                        Toast toastMailExist = Toast.makeText(getApplicationContext(), "Ya existe el mail..", Toast.LENGTH_LONG);
                        toastMailExist.show();
                    } else {
                        try {
                            db.insert("Users", null, User);
                            Intent loginIntent = new Intent(SigninActivity.this, LoginActivity.class);
                            SigninActivity.this.startActivity(loginIntent);
                        } catch (Exception ex) {
                            Toast toastErrorInsert = Toast.makeText(getApplicationContext(), "Como que no pude insertar nada, algo en la DB esta fallando " + ex, Toast.LENGTH_LONG);
                            toastErrorInsert.show();
                        }
                    }
                } catch (Exception ex) {
                    Toast toastErrorBuscar = Toast.makeText(getApplicationContext(), "No pude comprar el mail, algo en la DB esta fallando: " + ex, Toast.LENGTH_LONG);
                    toastErrorBuscar.show();
                }
            }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
            startActivity(intent);
            }
        });
    }
}
