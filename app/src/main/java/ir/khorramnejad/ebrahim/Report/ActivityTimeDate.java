package ir.khorramnejad.ebrahim.Report;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import ir.khorramnejad.ebrahim.R;

public class ActivityTimeDate extends Activity {



    MaterialBetterSpinner yearSpinner,mounthSpinner,daySpinner,
                            hourSpinner,minutSpinner,secoundSpinner;

    String[] year = {"1396","1397","1398","1399","1400","1401","1402","1403","1404","1405"};
    String[] mounth = { "فروردین","اردیبهشت","خرداد","تیر",
                        "مرداد","شهریور","مهر","آبان",
                        "آذر","دی","بهمن","اسفند"};
    String[]  day1 = {   "1","2","3","4","5","6","7","8",
                        "9","10","11","12","13","14","15","16",
                        "17","18","19","20","21","22","23","24",
                        "25","26","27","28","29","30","31"};
    String[]  day2 = {   "1","2","3","4","5","6","7","8",
                        "9","10","11","12","13","14","15","16",
                        "17","18","19","20","21","22","23","24",
                        "25","26","27","28","29","30"};
    String[] hour = {"1","2","3","4","5","6","7","8","9","10",
                        "11","12","13","14","15","16","17","18","19","20","21","22","23"};
    String[]  minut = {   "1","2","3","4","5","6","7","8",
                            "9","10","11","12","13","14","15","16",
                            "17","18","19","20","21","22","23","24",
                            "25","26","27","28","29","30",
                            "31","32","33","34","35","36","37","38",
                            "39","40","41","42","43","44","45","46",
                            "47","48","49","50","51","52","53","54",
                            "55","56","57","58","59"};

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_date);




        yearSpinner = findViewById(R.id.year_spinner);
        ArrayAdapter<String> yadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, year);
        yearSpinner.setAdapter(yadapter);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mounthSpinner = findViewById(R.id.mounth_spinner);
        ArrayAdapter<String> madapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, mounth);
        mounthSpinner.setAdapter(madapter);

        daySpinner = findViewById(R.id.day_spinner);
        ArrayAdapter<String> dadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, day1);
        daySpinner.setAdapter(dadapter);

        hourSpinner = findViewById(R.id.hour_spinner);
        ArrayAdapter<String> hadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, hour);
        hourSpinner.setAdapter(hadapter);

        minutSpinner = findViewById(R.id.minut_spinner);
        ArrayAdapter<String> minadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, minut);
        minutSpinner.setAdapter(minadapter);

        secoundSpinner = findViewById(R.id.secound_spinner);
        ArrayAdapter<String> sadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, minut);
        secoundSpinner.setAdapter(sadapter);

    }




}

