package com.example.vuphu.app.login_signUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.vuphu.app.MainActivity;
import com.example.vuphu.app.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void signUp(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
