package com.example.partia;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.Event;
import com.example.partia.model.LoginRequest;
import com.example.partia.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {
    EditText email,password;
    APIInterface apiInterface;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        email = findViewById(R.id.editText_Email);
        password = findViewById(R.id.editText_Password);

    }

    public void login_btn_clicked(View view) {
        if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
            Toast.makeText(ActivityLogin.this,"email/Password Required",Toast.LENGTH_LONG).show();
        } else{
            login();
        }
    }

    public void signUp_btn_clicked(View view) {
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail(email.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        Call<LoginResponse> loginResponseCall = apiInterface.getUserService().userLogin(loginRequest);
    }
}
