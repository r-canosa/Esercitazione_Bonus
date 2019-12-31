/**
 * Created by Riccardo Canosa 65372 on $DATE$.
 */

package com.example.esercitazionebonus;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logout;
    private TextView message;
    private TextView nome = null;
    private TextView pass = null;
    private TextView citta = null;
    private TextView compleanno = null;
    private TextView modificaPassword;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nome = (TextView) findViewById(R.id.etUsername);
        pass = (TextView) findViewById(R.id.etPass);
        citta = (TextView) findViewById(R.id.etCity);
        compleanno = (TextView) findViewById(R.id.etBirth);
        logout = (Button) findViewById(R.id.btnBack2);
        message = (TextView) findViewById(R.id.tvMessage);
        modificaPassword = (TextView) findViewById(R.id.tvModifyPassword);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String utente = extras.getString("user");
            String passa = extras.getString("pass");
            String cittadina = extras.getString("city");
            String compl = extras.getString("date");
            message.setText("Benvenuto, " + String.valueOf(utente) + ".");
            nome.setText(String.valueOf(utente));
            pass.setText(String.valueOf(passa));
            citta.setText(String.valueOf(cittadina));
            compleanno.setText(String.valueOf(compl));
        }
        modificaPassword.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvModifyPassword:
                Intent i = new Intent(this, ModificaPassword.class);
                i.putExtra("user", nome.getText().toString());
                i.putExtra("pass", pass.getText().toString());
                i.putExtra("city", citta.getText().toString());
                i.putExtra("date", compleanno.getText().toString());
                startActivity(i);
                break;
            case R.id.btnBack2:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
