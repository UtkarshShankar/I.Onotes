package com.utkarsh.ionotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNo extends AppCompatActivity {
    String verificationcodebysystem;
    Button verify_btn;
    EditText editText;
    TextView textView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        verify_btn=findViewById(R.id.button2);
        editText=findViewById(R.id.etOTPbyUser);
        textView=findViewById(R.id.textView);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        String phoneNo=getIntent().getStringExtra("phoneNo").trim();
        sendVerificationCode(phoneNo);
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=editText.getText().toString();
                if(code.isEmpty()||code.length()<6)
                {
                    editText.setError("Wrong OTP");
                    editText.requestFocus();
                    return;

                }
                progressBar.setVisibility(View.VISIBLE);
                verifycode(code);
            }
        });

    }



    private void sendVerificationCode(String phoneNo)
    {
        phoneNo="+91"+phoneNo;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,        // Phone number to verify
                10,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationcodebysystem=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code= phoneAuthCredential.getSmsCode();
            if (code!=null)
            {
                editText.setText(verificationcodebysystem);
                progressBar.setVisibility(View.VISIBLE);
                verifycode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyPhoneNo.this,e.getMessage(),Toast.LENGTH_SHORT);

        }
    };
    private void verifycode(String code)
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationcodebysystem, code);
        signInWithCredentials(credential);

    }

    private void signInWithCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(VerifyPhoneNo.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(VerifyPhoneNo.this,"Verification Done",Toast.LENGTH_SHORT);
//                    Intent intent=new Intent(getApplicationContext(),UserScreen.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);

                }
                else {
                    Toast.makeText(VerifyPhoneNo.this,"Verification Unsuccessfull",Toast.LENGTH_SHORT);
                }

            }
        });

    }
}
