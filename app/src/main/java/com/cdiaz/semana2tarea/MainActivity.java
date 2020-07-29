package com.cdiaz.semana2tarea;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etName;
    TextInputEditText etBirthDate;
    TextInputEditText etPhone;
    TextInputEditText etEmail;
    TextInputEditText etDescription;

    private DateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        initComponents();
    }

    private void initComponents() {
        etName = findViewById(R.id.etName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etDescription = findViewById(R.id.etDescription);
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        if (!etBirthDate.getText().toString().trim().isEmpty()) {
            try {
                newFragment.setSelectedDate(dateFormat.parse(etBirthDate.getText().toString().trim()));
            } catch (ParseException e) {
                Log.w("DatePicker", "Error en formato de fecha");
            }
        }
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void confirmValues(View v) {
        Intent intent = new Intent(MainActivity.this, ConfirmarActivity.class);
        intent.putExtra(ConfirmarActivity.EXTRA_NAME, etName.getText().toString());
        intent.putExtra(ConfirmarActivity.EXTRA_BIRTH_DATE, etBirthDate.getText().toString());
        intent.putExtra(ConfirmarActivity.EXTRA_PHONE, etPhone.getText().toString());
        intent.putExtra(ConfirmarActivity.EXTRA_EMAIL, etEmail.getText().toString());
        intent.putExtra(ConfirmarActivity.EXTRA_DESCRIPTION, etDescription.getText().toString());
        startActivity(intent);
    }

    private void selectedDate(Date date) {
        etBirthDate.setText(dateFormat.format(date));
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private Date selectedDate;

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            if (selectedDate != null) {
                c.setTime(selectedDate);
            }
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            final Calendar c = Calendar.getInstance();
            c.set(year, month, day, 0, 0);

            ((MainActivity) getActivity()).selectedDate(c.getTime());
        }

        public void setSelectedDate(Date selectedDate) {
            this.selectedDate = selectedDate;
        }
    }
}