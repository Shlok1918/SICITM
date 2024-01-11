package com.example.sicitm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button login,register;
    TextView bro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.Login);
        register = findViewById(R.id.Register);
        bro=findViewById(R.id.broucher);


        bro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(Intent.ACTION_VIEW,Uri.parse("https://simsr.s3.ap-south-1.amazonaws.com/Sictimk.pdf"));
                startActivity(b);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(getApplicationContext(),login.class);
                startActivity(l);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r = new Intent(getApplicationContext(),register.class);
                startActivity(r);
            }
        });


    }


    public void openUrl(View view){

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://simsr.somaiya.edu/en/conference/SICTIM"));
        startActivity(browserIntent);
    }
}