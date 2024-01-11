package com.example.sicitm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sicitm.databinding.ActivityRegisterBinding;

public class register extends AppCompatActivity {

    ActivityRegisterBinding binding;
    DatabaseHelper databaseHelper;
//    EditText email, password, cnf_pass;
//    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signUpemail.getText().toString();
                String password = binding.signUppassword.getText().toString();
                String confirmPassword = binding.confirmPassword.getText().toString();

                if (email.equals("")||password.equals("")||confirmPassword.equals("")){
                    Toast.makeText(register.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }else{
                    if (password.equals(confirmPassword)){
                        Boolean checkuserEmail = databaseHelper.checkEmail(email);

                        if(checkuserEmail == false){
                            Boolean insert  = databaseHelper.insertData(email,password);

                            if (insert == true){
                                Toast.makeText(register.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(register.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(register.this, "User Already Exists,plz login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(register.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });



    }
}