package ir.khorramnejad.ebrahim.Report;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import ir.khorramnejad.ebrahim.R;
import ir.khorramnejad.ebrahim.Splash.G;

public class ActivityTimeDate extends Activity {



    private Button next,back;

    TextView txtTime,txtDate;

    MaterialBetterSpinner yearSpinner,mounthSpinner,daySpinner,
                            hourSpinner,minutSpinner,secoundSpinner;

    String titleTime,titleDate;

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


        next = findViewById(R.id.btn_date_next);
        back = findViewById(R.id.btn_date_back);

        if (G.reportMode == 1)
        {
            titleDate = "تاریخ اتفاق را وارد کنید.";
            titleTime = "زمان اتفاق را وارد کنید.";
        }else if (G.reportMode==2){
            titleDate = "تاریخ مشاهده ایراد فنی را وارد کنید.";
            titleTime = "زمان مشاهده ایراد فنی را وارد کنید.";
        }else if (G.reportMode == 3){
            titleDate = "تاریخ انجام تعمیر را وارد کنید.";
            titleTime = "حدود زمان انجام تعمیر را وارد کنید.";
        }


        txtTime = findViewById(R.id.txt_time);
        txtDate = findViewById(R.id.txt_date);
        txtTime.setText(titleTime);
        txtDate.setText(titleDate);

        yearSpinner = findViewById(R.id.year_spinner);
        final ArrayAdapter<String> yadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, year);

        yearSpinner.setAdapter(yadapter);
        yearSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                G.jyear = Integer.parseInt(String.valueOf(parent.getItemAtPosition(position).toString()));
            }
        });




        mounthSpinner = findViewById(R.id.mounth_spinner);
        ArrayAdapter<String> madapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, mounth);
        mounthSpinner.setAdapter(madapter);
        mounthSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                G.jmounth = position + 1;
                if ((G.jmounth <= 6) & (G.jday == 31)) G.jday = 30;

            }
        });



        daySpinner = findViewById(R.id.day_spinner);

        if (G.jmounth < 6) {
            ArrayAdapter<String> dadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                    android.R.layout.simple_dropdown_item_1line, day1);
            daySpinner.setAdapter(dadapter);
        } else {
            ArrayAdapter<String> dadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                    android.R.layout.simple_dropdown_item_1line, day2);
            daySpinner.setAdapter(dadapter);
        }

        daySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                G.jday = position + 1;

            }
        });


        hourSpinner = findViewById(R.id.hour_spinner);
        ArrayAdapter<String> hadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, hour);
        hourSpinner.setAdapter(hadapter);
        hourSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                G.hTime = position + 1;

            }
        });


        minutSpinner = findViewById(R.id.minut_spinner);
        ArrayAdapter<String> minadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, minut);
        minutSpinner.setAdapter(minadapter);
        minutSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                G.mTime = position + 1;

            }
        });



        secoundSpinner = findViewById(R.id.secound_spinner);
        ArrayAdapter<String> sadapter = new ArrayAdapter<String>(ActivityTimeDate.this,
                android.R.layout.simple_dropdown_item_1line, minut);
        secoundSpinner.setAdapter(sadapter);
        secoundSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                G.sTime = position + 1;

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (G.reportMode !=3){
                    Intent intent = new Intent(ActivityTimeDate.this,
                            ActivityPlaceImportant.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(ActivityTimeDate.this,
                            ActivityTitle.class);
                    startActivity(intent);
                    finish();
                }


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTimeDate.this,
                        ActivitySelectReport.class);
                startActivity(intent);
                finish();
            }
        });

    }

}

