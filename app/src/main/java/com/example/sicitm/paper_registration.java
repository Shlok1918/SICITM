package com.example.sicitm;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class paper_registration extends AppCompatActivity {

    private static final int PICK_PDF_REQUEST = 1;

    BottomNavigationView bottomNavigationView;
    Button buttonRegister, buttonSelectPdf, buttonUploadPdf;
    Toolbar dashTool;
    EditText paperID, title, abs_tract;
    private TextView textViewPdfStatus;
    private Uri selectedPdfUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_registration);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.register);

        paperID = findViewById(R.id.paperid);
        title = findViewById(R.id.title);
        abs_tract = findViewById(R.id.abs_tract);
        textViewPdfStatus = findViewById(R.id.textViewPdfStatus);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonSelectPdf = findViewById(R.id.buttonSelectPdf);
        buttonUploadPdf = findViewById(R.id.buttonUploadPdf);

        final int MAX_WORD_COUNT = 300;

        abs_tract.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String[] words = charSequence.toString().trim().split("\\s+");
                int wordCount = words.length;

                if (wordCount > MAX_WORD_COUNT) {
                    abs_tract.setEnabled(false);
                } else {
                    abs_tract.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String paperid = paperID.getText().toString();
                String paper_title = title.getText().toString();
                String paper_abstract = abs_tract.getText().toString();
                String pdfPath = (selectedPdfUri != null) ? selectedPdfUri.toString() : "";

                Intent intent = new Intent(paper_registration.this, UserDetailsActivity.class);
                intent.putExtra("paperid", paperid);
                intent.putExtra("title", paper_title);
                intent.putExtra("abs_tract", paper_abstract);
                intent.putExtra("pdfPath", pdfPath);
                startActivity(intent);
            }
        });

        buttonSelectPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPdfFile();
            }
        });

        buttonUploadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPdfUri != null) {
                    // Perform PDF upload logic here
                }
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), dashboard.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
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
                    return true;
            }
            return false;
        });

        dashTool = findViewById(R.id.tool_dash);

        setSupportActionBar(dashTool);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        dashTool.setTitle("Dashboard");
        dashTool.setSubtitle("main page");
    }

    private void selectPdfFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedPdfUri = data.getData();
            textViewPdfStatus.setText("PDF Status: " + selectedPdfUri.getLastPathSegment());
            buttonUploadPdf.setEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.opt_menu, menu);
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
        }
        return super.onOptionsItemSelected(item);
    }
}
