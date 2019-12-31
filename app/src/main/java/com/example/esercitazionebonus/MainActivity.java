/**
 * Created by Riccardo Canosa 65372 on $DATE$.
 */

package com.example.esercitazionebonus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText Name = null;
    private EditText Password = null;
    private Button Login;
    private TextView Registrati;
    private TextView Errore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        Registrati = (TextView) findViewById(R.id.tvRegister);
        Errore = (TextView) findViewById(R.id.tvError);
        Errore.setText("");
        Login.setOnClickListener(this);
        Registrati.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (TextUtils.isEmpty(Name.getText().toString()) && (TextUtils.isEmpty(Password.getText().toString()))) {
                    Errore.setText("Inserire le credenziali prima di continuare.");
                } else {
                    if (TextUtils.isEmpty(Name.getText().toString())) {
                        Errore.setText("Inserire un nome utente prima di continuare.");
                    } else {
                        if (TextUtils.isEmpty(Password.getText().toString())) {
                            Errore.setText("Inserire una password prima di continuare.");
                        } else {
                                User user = new User (null, null, null, null);
                                UserFactory listaUtenti = new UserFactory();
                                user = listaUtenti.searchUser(Name.getText().toString(), Password.getText().toString());
                                if (TextUtils.isEmpty(user.city))
                                    Errore.setText("Dati non trovati.");
                                else {
                                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                    intent.putExtra("user", user.username);
                                    intent.putExtra("pass", user.password);
                                    intent.putExtra("city", user.city);
                                    intent.putExtra("date", user.date);
                                    startActivity(intent);
                                    }
                                }
                            }
                        }
                break;
            case R.id.tvRegister:
                Intent register = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
        }
    }
}

