package ir.khorramnejad.ebrahim.Report;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import ir.khorramnejad.ebrahim.R;
import ir.khorramnejad.ebrahim.Splash.G;

public class ActivitySelectReport extends Activity  {

    Button happen,tecnical,fixing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        happen = findViewById(R.id.btn_hapenning);
        tecnical = findViewById(R.id.btn_tecnical);
        fixing = findViewById(R.id.btn_fixing);

        happen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G.reportMode=1;
                Intent intent = new Intent(ActivitySelectReport.this,
                        ActivityTimeDate.class);
                startActivity(intent);
                finish();

            }
        });

        tecnical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G.reportMode=2;
                Intent intent = new Intent(ActivitySelectReport.this,
                        ActivityTimeDate.class);
                startActivity(intent);
                finish();

            }
        });

        fixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G.reportMode=3;
                Intent intent = new Intent(ActivitySelectReport.this,
                        ActivityTimeDate.class);
                startActivity(intent);
                finish();

            }
        });

    }

}

