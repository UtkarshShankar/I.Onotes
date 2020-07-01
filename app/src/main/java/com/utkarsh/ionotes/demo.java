package com.utkarsh.ionotes;

import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class demo extends AppCompatActivity {

    EditText Name1,Username,Email,Password,Phone;
    Button Gobtn,Backbtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Name1=findViewById(R.id.etName);
        Username=findViewById(R.id.etUsername);
        Email=findViewById(R.id.etEmail);
        Password=findViewById(R.id.etPass);
        Phone=findViewById(R.id.etPhone);
        Gobtn=findViewById(R.id.btGo);
        Backbtn=findViewById(R.id.btBack);


        Gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("User");
                String name=Name1.getText().toString();
                String username=Username.getText().toString();
                String phone=Phone.getText().toString();
                String email=Email.getText().toString();
                String password=Password.getText().toString();

                Intent intent=new Intent(getApplicationContext(), VerifyPhoneNo.class);
                intent.putExtra("phoneNo",phone );
                startActivity(intent);

//                UserHelperClass helperClass=new UserHelperClass(name,username,email,password,phone);
//                reference.child(phone).setValue(helperClass);
            }
        });

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(demo.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
