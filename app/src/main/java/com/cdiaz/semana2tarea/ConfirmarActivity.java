package com.cdiaz.semana2tarea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmarActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_BIRTH_DATE = "birthDate";
    public static final String EXTRA_PHONE = "phone";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_DESCRIPTION = "description";

    TextView tvName;
    TextView tvBirthDate;
    TextView tvPhone;
    TextView tvEmail;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);
        initComponents();

        Bundle params = getIntent().getExtras();
        tvName.setText(params.getString(EXTRA_NAME, ""));
        tvBirthDate.setText(params.getString(EXTRA_BIRTH_DATE, ""));
        tvPhone.setText(params.getString(EXTRA_PHONE, ""));
        tvEmail.setText(params.getString(EXTRA_EMAIL, ""));
        tvDescription.setText(params.getString(EXTRA_DESCRIPTION, ""));

    }

    private void initComponents() {
        tvName = findViewById(R.id.tvName);
        tvBirthDate = findViewById(R.id.tvBirthDate);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescription = findViewById(R.id.tvDescription);
    }

    public void editValues(View v) {
        finish();
    }
}