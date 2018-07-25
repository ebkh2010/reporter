package ir.khorramnejad.ebrahim.Report;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.florent37.materialtextfield.MaterialTextField;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.sql.Time;

import ir.khorramnejad.ebrahim.R;
import ir.khorramnejad.ebrahim.uploadapp.MainActivity;

public class ActivityTimeDate extends Activity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    TextView txtTime,txtDate;
    Button btnTime,btnDate;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        txtTime = findViewById(R.id.txt_time);
        txtDate = findViewById(R.id.txt_date);

        btnTime = findViewById(R.id.btn_time);
        btnDate = findViewById(R.id.btn_date);



        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar nowDate = Calendar.getInstance();
                final DatePickerDialog dpd = DatePickerDialog.newInstance(
                        ActivityTimeDate.this,
                        nowDate.get(Calendar.YEAR),
                        nowDate.get(Calendar.MONTH),
                        nowDate.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Calendar nowTime = Calendar.getInstance();
            final TimePickerDialog tpd = TimePickerDialog.newInstance(
                    ActivityTimeDate.this,
                    nowTime.get(Calendar.HOUR_OF_DAY),
                    nowTime.get(Calendar.MINUTE),
                    nowTime.get(Calendar.SECOND),
                    true
            );
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        txtDate.setText(date);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time = "You picked the following time: "+hourOfDay+"h"+minute+"m"+second;
        txtTime.setText(time);
    }

//
}