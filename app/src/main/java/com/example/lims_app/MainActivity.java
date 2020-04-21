package com.example.lims_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText email,password;


    Button btnLogin;
    Button btnSignUp;
    Vibrator v;
    //change this to match your url
    final String loginURL = Config.URL_LOGIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email=(EditText) findViewById(R.id.txtlogin);
        password=(EditText) findViewById(R.id.txtpass);
        btnLogin=(Button) findViewById(R.id.btnlogin);
        btnSignUp=(Button) findViewById(R.id.btnSignUp);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    email.setError("Please enter your email");
                    email.requestFocus();
                    // Vibrate for 100 milliseconds
                    v.vibrate(100);
                    btnLogin.setEnabled(true);
                    return;
                }else {
                    userlogin();
                }
            }
        });

btnSignUp.setOnClickListener(new View.OnClickListener(){

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, Signup.class);

        startActivity(intent);

    }
    }


);

    }





    private void userlogin() {

        //first getting the values
        final String email1 = email.getText().toString();
        final String password1 = password.getText().toString();



        //Call our volley library
        StringRequest stringRequest = new StringRequest(Request.Method.POST,loginURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
//

                            JSONObject obj = new JSONObject(response);
                            if (obj.getBoolean("error")) {

                               alertbox("Invalid",obj.getString("message"));
                                email.setText("");
                                password.setText("");
                            } else {

                                //getting user name
                                String loginuser = obj.getString("username");
                                Toast.makeText(getApplicationContext(),loginuser, Toast.LENGTH_SHORT).show();


                                finish();
                                Intent intent = new Intent(MainActivity.this, Menu.class);
                                intent.putExtra("userlogin", loginuser);
                                startActivity(intent);
                             ;

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Connection Error"+error, Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email1);
                params.put("password", password1);

                return params;
            }
        };
        VolleySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
    }


    public void alertbox(String title,String msg)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setTitle(title);
        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
