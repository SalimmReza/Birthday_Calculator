package com.example.birthday_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Person;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button birth, today, calculate;
    TextView result;
    DatePickerDialog.OnDateSetListener dateSetListener1, dateSetListener2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birth= findViewById(R.id.btn_birth_id);
        today= findViewById(R.id.btn_today_id);
        calculate= findViewById(R.id.btn_calculate);
        result= findViewById(R.id.tv_result_id);

        Calendar calendar = Calendar.getInstance();
        int year= calendar.get(Calendar.YEAR);
        int month= calendar.get(Calendar.MONTH);
        int day= calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date= simpleDateFormat.format(Calendar.getInstance().getTime());
        today.setText(date);

        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity
                        .this , android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener1, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener1 = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                String date = day+ "/" +month + "/" +year;
                birth.setText(date);

            }
        };

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity
                        .this , android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener2, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener2 = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                String date = day+ "/" +month + "/" +year;
                today.setText(date);

            }
        };

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdate = birth.getText().toString();
                String edate = today.getText().toString();
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date date1 = simpleDateFormat1.parse(sdate);
                    Date date2 = simpleDateFormat1.parse(edate);

                    long startdate= date1.getTime();
                    long enddate = date2.getTime();

                    if (startdate <= enddate)
                    {
                        Period period = new Period(startdate , enddate , PeriodType.yearMonthDay());
                        int years = period.getYears();
                        int months = period.getMonths();
                        int days = period.getDays();

                        result.setText(years+ "Years | " +months+" Months | " +days+"Days");

                    }else
                    {
                        Toast.makeText(getApplicationContext()
                                , "Enter Correct Birthdate"
                                ,Toast.LENGTH_LONG).show();

                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });




    }
}