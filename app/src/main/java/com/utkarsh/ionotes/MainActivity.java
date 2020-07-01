package com.utkarsh.ionotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView info;
    private TextView Sign;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name =findViewById(R.id.etname);
        Password=findViewById(R.id.etpassword);
        Login=findViewById(R.id.button);
        info=findViewById(R.id.tvinfo);
        Sign=findViewById(R.id.tvsignup);

//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validate(Name.getText().toString(),Password.getText().toString());
//            }
//            });
        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,demo.class);
                startActivity(intent);
            }
        });

    }

//    private void validate(String userName,String userPassword){
//        if(userName.equals("Admin")&&userPassword.equals("12345"))
//        {
//            startActivity(new Intent(MainActivity.this, SignUp.class));
//
//        }
//        else
//        {
//            counter--;
//            info.setText("No. of attempts left -"+String.valueOf(counter));
//            if(counter==0)
//                Login.setEnabled(false);
//        }
//
//    }
}
