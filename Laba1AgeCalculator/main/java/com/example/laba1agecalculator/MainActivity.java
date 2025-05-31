package com.example.laba1agecalculator;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private TextView dateTimeText;
    private Button calculateButtonMinutes;
    private Button calculateButtonHours;
    private Button calculateButtonMonths;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTimeText = findViewById(R.id.textViewBirthdayDate);
        calculateButtonMinutes = findViewById(R.id.buttonMinutes);
        calculateButtonHours = findViewById(R.id.buttonHours);
        calculateButtonMonths = findViewById(R.id.buttonMonths);
        resultTextView = findViewById(R.id.textViewResult);

        dateTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                                // Сохраняю выбранную дату и обновляю текстовое поле
                                String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                                dateTimeText.setText(selectedDate);
                                // После выбора даты вызваю TimePicker
                                showTimePicker();
                            }
                            private void showTimePicker() {
                                Calendar calendar = Calendar.getInstance();
                                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                                int minute = calendar.get(Calendar.MINUTE);

                                TimePickerDialog timePickerDialog = new TimePickerDialog(
                                        MainActivity.this,
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                                                // Обновляю текстовое поле с выбранным временем
                                                String time = selectedHour + ":" + String.format("%02d", selectedMinute);
                                                dateTimeText.append(" " + time);
                                            }
                                        },
                                        hour, minute, true);
                                timePickerDialog.show();
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        calculateButtonMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAgeInMinutes();
            }
        });

        calculateButtonHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAgeInHours();
            }
        });

        calculateButtonMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAgeInMonths();
            }
        });
    };

    private void calculateAgeInMinutes() {
        String birthdateString = dateTimeText.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthdate = sdf.parse(birthdateString);
            if (birthdate != null) {
                long minutes = getAgeInMinutes(birthdate);
                resultTextView.setText("Ваш возраст в минутах: " + minutes);
            }
        } catch (ParseException e) {
            resultTextView.setText("Необходимо заполнить дату и время рождения");
        }
    }

    private long getAgeInMinutes(Date birthdate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthdate);

        Calendar now = Calendar.getInstance();

        long ageInMillis = now.getTimeInMillis() - birth.getTimeInMillis();
        return ageInMillis / (1000 * 60); // Конвертирую миллисекунды в минуты
    }

    private void calculateAgeInHours() {
        String birthdateString = dateTimeText.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthdate = sdf.parse(birthdateString);
            if (birthdate != null) {
                long hours = getAgeInHours(birthdate);
                resultTextView.setText("Ваш возраст в часах: " + hours);
            }
        } catch (ParseException e) {
            resultTextView.setText("Необходимо заполнить дату и время рождения");
        }
    }

    private long getAgeInHours(Date birthdate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthdate);

        Calendar now = Calendar.getInstance();

        long ageInMillis = now.getTimeInMillis() - birth.getTimeInMillis();
        return ageInMillis / (1000 * 60 * 60); // Конвертирую миллисекунды в часы
    }

    private void calculateAgeInMonths() {
        String birthdateString = dateTimeText.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthdate = sdf.parse(birthdateString);
            if (birthdate != null) {
                long months = getAgeInMonths(birthdate);
                resultTextView.setText("Ваш возраст в месяцах: " + months);
            }
        } catch (ParseException e) {
            resultTextView.setText("Необходимо заполнить дату и время рождения");
        }
    }

    private long getAgeInMonths(Date birthdate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthdate);

        Calendar now = Calendar.getInstance();

        long ageInMillis = now.getTimeInMillis() - birth.getTimeInMillis();
        return ageInMillis / -(1000 * 60 * 60 * 24 * 30); // Конвертирую миллисекунды в месяцы
    }

}



