package com.example.sicitm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class workshop_page extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Toolbar dashTool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_page);


        bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.workshop);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(),dashboard.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;

                case R.id.ourTeam:
                    startActivity(new Intent(getApplicationContext(),team_profiles.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;

                case R.id.workshop:
                    return true;

                case R.id.register:
                    startActivity(new Intent(getApplicationContext(),paper_registration.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        dashTool = findViewById(R.id.tool_dash);

        setSupportActionBar(dashTool);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        dashTool.setTitle("Dashboard");
        dashTool.setSubtitle("main page");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.opt_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemID = item.getItemId();
        if(itemID==R.id.opt_gallary){
            Intent gallary = new Intent(getApplicationContext(),dashboard.class);
            startActivity(gallary);
            Toast.makeText(this, "Gallary view", Toast.LENGTH_SHORT).show();
        } else if (itemID==R.id.opt_new) {
            Toast.makeText(this, "opening new page", Toast.LENGTH_SHORT).show();
        }else if (itemID==R.id.opt_save) {
            Toast.makeText(this, "content saved", Toast.LENGTH_SHORT).show();
        }else if (itemID==R.id.opt_upload) {
            Toast.makeText(this, "upload the file", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}