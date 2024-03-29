package com.om.heroesapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.om.heroesapi.apiurl.Posturl;
import com.om.heroesapi.heroesapi.UserAPI;
import com.om.heroesapi.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
EditText editText,editText1;
Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.etname);
        editText1=findViewById(R.id.etpass);
        button=findViewById(R.id.btn_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loadata();
                String username=editText.getText().toString();
                String password=editText1.getText().toString();
                if(TextUtils.isEmpty(username)){
                    editText.setError("Username Required!");
                    editText.requestFocus();
                }if(TextUtils.isEmpty(password)){
                    editText1.setError("Password Required!");
                    editText1.requestFocus();
                }


            }
        });

        button1=findViewById(R.id.btn_up);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                Intent intent=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Loadata() {
        final User user=new User(editText.getText().toString(),editText1.getText().toString());
        Posturl posturl=new Posturl();
        final Call<Void> logcall=posturl.getInstance().login(user);
        logcall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "No User Found!", Toast.LENGTH_SHORT).show();
                    Log.d("Error","Code "+response.code());
                    return;
                }
                Toast.makeText(MainActivity.this, "Welcome! "+user.getUsername(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,DashBoard.class);
                intent.putExtra("user",user.getUsername());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
