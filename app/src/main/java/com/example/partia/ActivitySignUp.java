package com.example.partia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.LoginRequest;
import com.example.partia.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySignUp extends AppCompatActivity {
    EditText email,password;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // apiInterface = APIClient.getClient().create(APIInterface.class);
        email = findViewById(R.id.editText_Email_SU);
        password = findViewById(R.id.editText_Password_SU);
     }

    public void login_btn_clicked(View view) {
        Intent intent = new Intent(this, ActivityLogin.class);
        this.startActivity(intent);
    }

    public void signUp_btn_clicked(View view) {
        if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
            Toast.makeText(ActivitySignUp.this,"email/Password Required",Toast.LENGTH_LONG).show();
        } else{
            signUp();
        }
    }

    private void signUp() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail(email.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        Call<LoginResponse> loginResponseCall = APIClient.getAPIInterface().doSignUp(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ActivitySignUp.this,"Welcome to PARTIA",Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(ActivitySignUp.this,MainActivity.class);
                            intent.putExtra("EXTRA_USER_SESSION_EMAIL", response.body().getUserEmail());
                            startActivity(intent);
                            // startActivity(new Intent(ActivityLogin.this,MainActivity.class).putExtra("data", lognResponse..getUserId()));
                        }
                    },700);
                }else {
                    Toast.makeText(ActivitySignUp.this,"User exist!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(ActivitySignUp.this,"Throwable" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
