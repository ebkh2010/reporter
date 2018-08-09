package ir.khorramnejad.ebrahim.Report;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.florent37.materialtextfield.MaterialTextField;

import ir.khorramnejad.ebrahim.R;

public class ActivityTitle extends Activity{



    MaterialTextField Title;
    EditText eTitle,eDisc;
    private Button btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);



        Title = findViewById(R.id.txt_title);

        eTitle = findViewById(R.id.etxt_title);
        eDisc = findViewById(R.id.etxt_disc);

        btnnext = findViewById(R.id.btn_tittle_next);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });






    }

}