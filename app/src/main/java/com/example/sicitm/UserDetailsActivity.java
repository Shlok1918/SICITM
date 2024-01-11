package com.example.sicitm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailsActivity extends AppCompatActivity {

    private TextView textViewPaperID;
    private TextView textViewTitle;
    private TextView textViewPdfPath;

    private Button buttonViewPdf;
    private TextView textViewAbstract;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        textViewPaperID = findViewById(R.id.textViewPaperID);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewPdfPath = findViewById(R.id.textViewPdfPath);
        textViewAbstract = findViewById(R.id.textViewAbstract);
        buttonViewPdf= findViewById(R.id.buttonViewPdf);

        // Retrieve user details from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String paperid = intent.getStringExtra("paperid");
            String title = intent.getStringExtra("title");
            String abs_tract = intent.getStringExtra("abs_tract");
            String pdfPath = intent.getStringExtra("pdfPath");

            // Display user details
            textViewPaperID.setText("paperid: " + paperid );
            textViewTitle.setText("Email: " + title);
            textViewAbstract.setText("Abstract: " + abs_tract);
            textViewPdfPath.setText("PDF Path: " + pdfPath);

            buttonViewPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open the PDF file using an intent
                    Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                    pdfIntent.setDataAndType(Uri.parse(pdfPath), "application/pdf");
                    pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(pdfIntent);
                    } catch (Exception e) {
                        // Handle exception if there's no PDF viewer installed on the device
                        Toast.makeText(UserDetailsActivity.this, "No PDF viewer found.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}