package com.om.heroesapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.om.heroesapi.apiurl.Posturl;
import com.om.heroesapi.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
private EditText editText,editText1,editText2;
private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editText=findViewById(R.id.etname);
        editText1=findViewById(R.id.etpass);
        editText2=findViewById(R.id.etconpass);

        button=findViewById(R.id.btn_up);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
                String username=editText.getText().toString();
                String password=editText1.getText().toString();
                String conpass=editText2.getText().toString();
                if(TextUtils.isEmpty(username)){
                    editText.setError("Username is Empty!");
                    editText.requestFocus();
                } if(TextUtils.isEmpty(password)){
                    editText1.setError("Password is Empty!");
                    editText1.requestFocus();
                } if(TextUtils.isEmpty(conpass)){
                    editText2.setError("Confirm-Password Empty!");
                    editText2.requestFocus();
                }
            }
        });


    }

    private void Save() {
       String name=editText.getText().toString();
       String password=editText1.getText().toString();
       String conpassword=editText2.getText().toString();
        if(password.equals(conpassword)) {


            User user = new User(name, password);

            //adding URL
            Posturl posturl = new Posturl();
            //UserAPI heroesAPI= Posturl.getInstance().create(UserAPI.class);

            final Call<Void> voidCall = posturl.getInstance().register(user);

            voidCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(SignupActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    editText1.setText("");
                    editText2.setText("");
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(SignupActivity.this, "Error !" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(SignupActivity.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
