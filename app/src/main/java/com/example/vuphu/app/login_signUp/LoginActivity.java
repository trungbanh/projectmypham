package com.example.vuphu.app.login_signUp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.vuphu.app.MainActivity;
import com.example.vuphu.app.R;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pre;
    private SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pre =getSharedPreferences("data", MODE_PRIVATE);
        edit=pre.edit();
    }

    public void signIn(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        edit.putString("type_user", "admin");
        edit.commit();
    }

    public void signUp(View view) {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }
}
