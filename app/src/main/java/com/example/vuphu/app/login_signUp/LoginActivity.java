package com.example.vuphu.app.login_signUp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.MainActivity;
import com.example.vuphu.app.object.SignUpToken;
import com.example.vuphu.app.R;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pre;
    private SharedPreferences.Editor edit;

    private EditText emailInput ;
    private EditText passInput ;

    private String mail ;
    private String pass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pre =getSharedPreferences("data", MODE_PRIVATE);
        edit=pre.edit();

        Intent intent = getIntent() ;

        mail = intent.getStringExtra("email");
        pass = intent.getStringExtra("pass");

        init();


    }

    private void init() {

        emailInput = findViewById(R.id.edt_email);
        passInput = findViewById(R.id.edt_pass);

        emailInput.setText(mail);
        passInput.setText(pass);


    }

    public void signIn(View view) {

        if (postSignIn().equals("Auth successful")) {

            if (mail.equals("admin@admin.com")) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                edit.putString("type_user", "admin");
                edit.commit();
            } else {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                edit.putString("type_user", "user");
                edit.commit();
            }
        } else {
            Toast.makeText(this, "incorrect email or password", Toast.LENGTH_SHORT).show();
        }
    }

    public void signUp(View view) {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    private String postSignIn() {
        RequestParams params = new RequestParams();
        final SignUpToken[] token = new SignUpToken[1];

        AsyncHttpApi.post("/user/login",params,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                String json = response.toString();

                Gson gson = new Gson() ;
                token[0] = gson.fromJson(json,SignUpToken.class);

            }
        });

        return token[0].getToken().toString();
    }
}
