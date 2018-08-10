package ir.khorramnejad.ebrahim.Report;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.florent37.materialtextfield.MaterialTextField;

import ir.khorramnejad.ebrahim.R;
import ir.khorramnejad.ebrahim.Splash.G;

public class ActivityTitle extends Activity{




    EditText eTitle,eDisc;
    private Button next,back;
    String txt_title,txt_desc;
    TextView txtdisc,txttitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        if (G.reportMode == 1)
        {
            txt_title = "عنوان اتفاق ";
            txt_desc = "شرح اتفاق";
        }else if (G.reportMode==2){
            txt_title = "عنوان ایراد فنی";
            txt_desc = "شرح ایراد فنی";
        }else if (G.reportMode == 3){
            txt_title = "عنوان تعمیر";
            txt_desc = "شرح تعمیرات";
        }


        txttitle = findViewById(R.id.txt_title_title);
        txtdisc = findViewById(R.id.txt_title_disc);

        eTitle = findViewById(R.id.etxt_title);
        eDisc = findViewById(R.id.etxt_disc);

        txttitle.setText(txt_title);
        txtdisc.setText(txt_desc);

        eDisc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                eDisc.setHeight(500);
            }
        });



        next = findViewById(R.id.btn_title_next);
        back = findViewById(R.id.btn_title_back);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (G.reportMode != 3)
                {
                    Intent intent = new Intent(ActivityTitle.this,
                            ActivityPlaceImportant.class);
                    startActivity(intent);
                    finish();
                }else
                {
                    Intent intent = new Intent(ActivityTitle.this,
                            ActivityTimeDate.class);
                    startActivity(intent);
                    finish();
                }
            }
        });




    }

}