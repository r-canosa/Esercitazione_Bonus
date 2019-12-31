/**
 * Created by Riccardo Canosa 65372 on $DATE$.
 */

package com.example.esercitazionebonus;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "RegistrationActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText username = null;
    private EditText password = null;
    private EditText repeatPassword = null;
    private EditText city = null;
    private boolean birthday = false;
    private Button registrati;
    private Button back;
    private TextView error;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        username = (EditText) findViewById(R.id.etNewUsername);
        password = (EditText) findViewById(R.id.etNewPassword);
        repeatPassword = (EditText) findViewById(R.id.etNewPassword2);
        city = (EditText) findViewById(R.id.etBirthday);
        back = (Button) findViewById(R.id.btnBackAgain);
        registrati = (Button) findViewById(R.id.btnRegistration);
        error = (TextView) findViewById(R.id.tvErrorRegistration);

        registrati.setOnClickListener(this);
        back.setOnClickListener(this);
        mDisplayDate.setOnClickListener(this);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anno, int mese, int giorno) {
                mese = mese+1;
                Log.d(TAG, "onDateSet: gg/mm/aaa: " + giorno + "/" + mese + "/" + anno);
                String date = giorno + "/" + mese + "/" + anno;
                mDisplayDate.setText(date);
                birthday = true;
            }
        };
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvDate:
                Calendar cal = Calendar.getInstance();
                int anno = cal.get(Calendar.YEAR);
                int mese = cal.get(Calendar.MONTH);
                int giorno = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        RegistrationActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        anno, mese, giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            case R.id.btnRegistration:
                if  ((TextUtils.isEmpty(username.getText().toString())) ||
                        (TextUtils.isEmpty(password.getText().toString())) ||
                        (TextUtils.isEmpty(repeatPassword.getText().toString())) ||
                        (TextUtils.isEmpty(city.getText().toString())) ||
                        (birthday == false))
                {
                    error.setText("Completare tutti i campi prima di continuare.");
                } else {
                    if (!(TextUtils.equals(password.getText().toString(), repeatPassword.getText().toString()))) {
                        error.setText("Attenzione, le password non coincidono.");
                    } else {
                        String nome = username.getText().toString();
                        String pass = password.getText().toString();
                        String citta = city.getText().toString();
                        String compleanno = mDisplayDate.getText().toString();

                        UserFactory listaUtenti = new UserFactory();
                        User registeredData = new User (nome, pass, citta, compleanno);
                        listaUtenti.AddUserToList(registeredData);

                        Intent intent = new Intent (RegistrationActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.btnBackAgain:
                Intent intent = new Intent (RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
