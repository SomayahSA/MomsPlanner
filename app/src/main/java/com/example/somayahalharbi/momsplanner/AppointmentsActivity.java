package com.example.somayahalharbi.momsplanner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentsActivity extends AppCompatActivity {
    @BindView(R.id.fab)
    FloatingActionButton addAppointmentFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        ButterKnife.bind(this);
        addAppointmentFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAppointment();
            }
        });

    }

    private void addAppointment() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_appointment, null);
        dialogBuilder.setView(dialogView);
        // ButterKnife.bind(this, dialogView);
        //TODO: replace this with Butterknife
        final EditText apptTitle = dialogView.findViewById(R.id.appt_title);
        final Button addButton = dialogView.findViewById(R.id.add_appt_button);
        final Button cancelButton = dialogView.findViewById(R.id.cancel_appt_button);
        final EditText apptLocation = dialogView.findViewById(R.id.appt_location);
        final EditText apptDate = dialogView.findViewById(R.id.appt_date);
        final EditText apptTime = dialogView.findViewById(R.id.appt_time);

        //TODO: replace dummy data

        // Spinner
        String[] owners = {"Faisal", "Somayah", "Sarah"};


        final Spinner ownersSpinner = dialogView.findViewById(R.id.owner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, owners);
        ownersSpinner.setAdapter(adapter);
        ownersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO: implement this

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //TODO: extract this part to reuse it between all activities.
        //Date Picker Dialog
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                apptDate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        apptDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(dialogView.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Time picker dialog
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                myCalendar.set(Calendar.HOUR, hour);
                myCalendar.set(Calendar.MINUTE, minutes);
                //TODO: show AM or PM
               /*
               String myFormat = "hh:mm a"; // your own format
               SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
               String  formated_time = sdf.format(myCalendar.getTime());
               apptTime.setText(formated_time);
               */
                apptTime.setText(hour + ":" + minutes);


            }
        };
        apptTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(dialogView.getContext(), time,
                        myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),
                        true).show();
            }
        });


        final AlertDialog dialog = dialogBuilder.create();
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: add to do task functionality goes here
            }

        });
        dialog.show();
    }



}
