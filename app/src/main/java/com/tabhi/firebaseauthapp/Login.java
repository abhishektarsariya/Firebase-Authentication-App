package com.tabhi.firebaseauthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText lemail,lpassword;
    Button btnlg;
    ProgressBar progressBarL;
    FirebaseAuth ulAuth;
    TextView temptext2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        temptext2 = (TextView) findViewById(R.id.textView_temp2);

        temptext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });

        lemail=(EditText)findViewById(R.id.editText_EmailLogin);
        lpassword=(EditText)findViewById(R.id.editText_PasswordLogin);
        progressBarL =findViewById(R.id.progressBarLogin);
        btnlg=(Button)findViewById(R.id.btnLogin);
        ulAuth=FirebaseAuth.getInstance();

        btnlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=lemail.getText().toString().trim();
                String password=lpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    lemail.setError("Email is Require");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    lpassword.setError("Password is Require");
                    return;
                }
                if(password.length() < 6){
                    lpassword.setError("Password must be more then 6 Characters");
                    return;
                }

                progressBarL.setVisibility(View.VISIBLE);

                ulAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(Login.this,"Error. "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            progressBarL.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });
    }
}
