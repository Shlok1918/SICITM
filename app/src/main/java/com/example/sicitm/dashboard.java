package com.example.sicitm;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class dashboard extends AppCompatActivity {

    Toolbar dashTool;
    Bitmap bitmap;
    Button generate, logout;
    ImageView qrcode;

    TextView userName;

    GoogleSignInClient gClient;
    GoogleSignInOptions gOptions;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        logout = findViewById(R.id.logout);
        userName = findViewById(R.id.userName);
        gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gClient = GoogleSignIn.getClient(this, gOptions);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        qrcode = findViewById(R.id.qrcode);
        String username = getIntent().getStringExtra("username");

        generate = findViewById(R.id.generate);
        generate.setOnClickListener(view -> {
            // Database fetching***********************************
            Cursor cursor = databaseHelper.getUser(username);

            if (cursor.moveToFirst()) {
                // Retrieve values from the cursor
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String pass = cursor.getString(cursor.getColumnIndex("password"));
                String[] userDetails = {email, pass};
                String userDetailsString = Arrays.toString(userDetails);
                // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
                QRGEncoder qrgEncoder = new QRGEncoder(userDetailsString, null, QRGContents.Type.TEXT, 800);
                qrgEncoder.setColorBlack(Color.TRANSPARENT);
                qrgEncoder.setColorWhite(Color.BLACK);
                try {
                    // Getting QR-Code as Bitmap
                    bitmap = qrgEncoder.getBitmap();
                    // Setting Bitmap to ImageView
                    qrcode.setImageBitmap(bitmap);
                } catch (Exception e) {
                    Log.v(TAG, e.toString());
                }
            }

            cursor.close();

        });

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    return true;
                case R.id.ourTeam:
                    startActivity(new Intent(getApplicationContext(), team_profiles.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.workshop:
                    startActivity(new Intent(getApplicationContext(), workshop_page.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.register:
                    startActivity(new Intent(getApplicationContext(), paper_registration.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        GoogleSignInAccount gAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (gAccount != null) {
            String gName = gAccount.getDisplayName();
            userName.setText(gName);
        }

        logout.setOnClickListener(view -> {
            gClient.signOut().addOnCompleteListener(task -> {
                finish();
                startActivity(new Intent(dashboard.this, login.class));
            });
        });

        // Initialize toolbar
        dashTool = findViewById(R.id.tool_dash);
        setSupportActionBar(dashTool);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        dashTool.setTitle("Dashboard");
        dashTool.setSubtitle("main page");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemID = item.getItemId();
        if (itemID == R.id.opt_gallary) {
            Intent gallery = new Intent(getApplicationContext(), dashboard.class);
            startActivity(gallery);
            Toast.makeText(this, "Gallery view", Toast.LENGTH_SHORT).show();
        } else if (itemID == R.id.opt_new) {
            Toast.makeText(this, "Opening new page", Toast.LENGTH_SHORT).show();
        } else if (itemID == R.id.opt_save) {
            Toast.makeText(this, "Content saved", Toast.LENGTH_SHORT).show();
        } else if (itemID == R.id.opt_upload) {
            Toast.makeText(this, "Upload the file", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
