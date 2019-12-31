/**
 * Created by Riccardo Canosa 65372 on $DATE$.
 */

package com.example.esercitazionebonus;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class ModificaPassword extends AppCompatActivity implements View.OnClickListener {

    private Button conferma;
    private Button back;
    private TextView message;
    private TextView nome = null;
    private TextView pass = null;
    private TextView citta = null;
    private TextView compleanno = null;
    private EditText modificaPassword;
    private EditText modificaPassword2;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        conferma = (Button) findViewById(R.id.btnConfirm);
        back = (Button) findViewById(R.id.btnBackM);
        message = (TextView) findViewById(R.id.tvErrorM);
        nome = (TextView) findViewById(R.id.tvUsernameM);
        pass = (TextView) findViewById(R.id.tvPassM);
        citta = (TextView) findViewById(R.id.tvCityM);
        compleanno = (TextView) findViewById(R.id.tvBirthM);
        modificaPassword = (EditText) findViewById(R.id.etNewPass1);
        modificaPassword2 = (EditText) findViewById(R.id.etNewPass2);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String utente = extra.getString("user");
            String passa = extra.getString("pass");
            String cittadina = extra.getString("city");
            String compl = extra.getString("date");
            nome.setText(utente);
            pass.setText(passa);
            citta.setText(cittadina);
            compleanno.setText(compl);
        }
        message.setText("");

        conferma.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnConfirm:
                if (TextUtils.isEmpty(modificaPassword.getText().toString()) || (TextUtils.isEmpty(modificaPassword2.getText().toString()))) {
                    message.setText("Riempire i campi prima di continuare.");
                } else {
                    if (modificaPassword.getText().toString().equals(pass.getText().toString())) {
                        message.setText("Scegliere una password diversa da quella attuale.");
                    } else {
                        if (!modificaPassword.getText().toString().equals(modificaPassword2.getText().toString())) {
                            message.setText("Le due password non coincidono. Si prega di ricontrollare.");
                        }else{
                            User user = new User(nome.getText().toString(), pass.getText().toString(), citta.getText().toString(), compleanno.getText().toString());
                            UserFactory listaUtenti = new UserFactory();
                            listaUtenti.modifyPassword(user, modificaPassword.getText().toString());
                            message.setText("Password cambiata con successo.");
                            Handler handler = new Handler();
                            Intent i = new Intent(this, SecondActivity.class);
                            i.putExtra("user", nome.getText().toString());
                            i.putExtra("pass", modificaPassword.getText().toString());
                            i.putExtra("city", citta.getText().toString());
                            i.putExtra("date", compleanno.getText().toString());
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    startActivity(i);
                                }
                            }, 1000);
                        }
                    }
                }
                break;
            case R.id.btnBackM:
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
    }
}
