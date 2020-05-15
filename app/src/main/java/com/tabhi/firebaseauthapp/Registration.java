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
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Registration extends AppCompatActivity {

    TextView temptxt1;
    EditText uName,uEmail,uPassword,uPhonenumber;
    ProgressBar progressBarR;
    FirebaseAuth uAuth;
   // FirebaseFirestore ufireStore;
    Button btnReg;
    //String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        temptxt1 = (TextView)findViewById(R.id.textView_temp1);

        uAuth = FirebaseAuth.getInstance();
        //ufireStore=FirebaseFirestore.getInstance();

        btnReg = (Button)findViewById(R.id.btnReg);

        progressBarR = findViewById(R.id.progressBarReg);

        uName=(EditText)findViewById(R.id.editText_Name);
        uEmail=(EditText)findViewById(R.id.editText_Email);
        uPassword=(EditText)findViewById(R.id.editText_Password);
        uPhonenumber=(EditText)findViewById(R.id.editText_PhoneNumber);

        if(uAuth.getCurrentUser() !=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String remail=uEmail.getText().toString().trim();
                String rpassword=uPassword.getText().toString().trim();
                String rname=uName.getText().toString().trim();
                String rphno=uPhonenumber.getText().toString().trim();

                if(TextUtils.isEmpty(rname)){
                    uName.setError("Name is Require");
                    return;
                }

                if(TextUtils.isEmpty(remail)){
                    uEmail.setError("Email is Require");
                    return;
                }
                if(TextUtils.isEmpty(rpassword)){
                    uPassword.setError("Password is Require");
                    return;
                }
                if(rpassword.length() < 6){
                    uPassword.setError("Password must be more then 6 Characters");
                    return;
                }
                if(TextUtils.isEmpty(rphno)){
                    uPhonenumber.setError("Phone Number is Require");
                    return;
                }
                if(rphno.length() < 10){
                    uPhonenumber.setError("Phone number must be 10 Digits");
                    return;
                }

                progressBarR.setVisibility(View.VISIBLE);

                uAuth.createUserWithEmailAndPassword(remail,rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Registration.this,"Register Successfull",Toast.LENGTH_SHORT).show();
                            /*userid=uAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = ufireStore.collection("Users").document(userid);
                            Map<String,Object> user=new HashMap<>();
                            user.put("Full-Name", rname);
                            user.put("Email",remail);
                            user.put("Phone-Number",rphno);*/
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(Registration.this,"Error. "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            progressBarR.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


        temptxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
