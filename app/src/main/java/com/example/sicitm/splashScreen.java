package com.example.sicitm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class splashScreen extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView logo = findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.popup_animation);
        logo.startAnimation(animation);


        Thread background = new Thread(){
            @Override
            public void run() {
                try {

                    sleep(2*1000);
                    Intent i= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);

                    finish();
                }
                catch (Exception e){
                    Toast.makeText(splashScreen.this, "Error loading screen", Toast.LENGTH_SHORT).show();

                }
            }
        };background.start();
    }
}