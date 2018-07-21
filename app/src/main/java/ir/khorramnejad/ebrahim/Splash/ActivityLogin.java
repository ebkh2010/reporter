package ir.khorramnejad.ebrahim.Splash;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.florent37.materialtextfield.MaterialTextField;

import ir.khorramnejad.ebrahim.R;

public class ActivityLogin extends Activity{


    TextView loginReport;
    MaterialTextField User,Pass;
    EditText euser,epass;
    Button btnLogin;
    String mainUser = "ebkh",
            mainPass= "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginReport = findViewById(R.id.txt_login);
        User = findViewById(R.id.txt_use);
        Pass = findViewById(R.id.txt_pass);
        euser = findViewById(R.id.etxt_user);
        epass = findViewById(R.id.etxt_pass);

        btnLogin = findViewById(R.id.b_login);

        epass.setTransformationMethod(new PasswordTransformationMethod());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(euser.getText().toString().equals(mainUser))
                {
                    if (epass.getText().toString().equals( mainPass))
                    {
                        //go to main activity
                        loginReport.setText("Succsess!");
                    }else{
                        loginReport.setText("Username is correct but password is not!");
                    }
                }
                else
                    {
                    loginReport.setText("Username is incorrect! user is:" + euser.getText().toString());
                }

            }
        });






    }

}