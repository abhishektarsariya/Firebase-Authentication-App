package com.tabhi.firebaseauthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button lgout;
    FirebaseAuth uhAuth;
    TextView uname;
    String nam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname=(TextView)findViewById(R.id.textView_UserName) ;
        lgout=(Button)findViewById(R.id.btnLogout);
        uhAuth=FirebaseAuth.getInstance();

        nam= uhAuth.getCurrentUser().getEmail();

        if(uhAuth.getCurrentUser()!=null)
        {
            uname.setText(nam);
        }

        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

    }
}
