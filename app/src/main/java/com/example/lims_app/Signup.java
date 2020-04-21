package com.example.lims_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class Signup extends AppCompatActivity {
    //change this to match your url
    final String loginURL = Config.URL_REGISTER;
    EditText firstname,lastname,email,password,phone;
    Button btnSubmit;
    Spinner gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firstname=(EditText) findViewById(R.id.txtfirstname);
        lastname=(EditText) findViewById(R.id.txtlastname);
        email=(EditText) findViewById(R.id.txtlogin);
        password=(EditText) findViewById(R.id.txtpassword);
        phone=(EditText) findViewById(R.id.txtphone);
        btnSubmit=(Button) findViewById(R.id.btnsubmit);
        gender=(Spinner) findViewById(R.id.gender);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText().toString())||TextUtils.isEmpty(phone.getText().toString())||TextUtils.isEmpty(lastname.getText().toString())||TextUtils.isEmpty(firstname.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {

                        alertbox("Invalid","Enter All Fields");
                    btnSubmit.setEnabled(true);
                    return;
                }else {
                    userregister();
                }
            }
        });
    }

    private void userregister() {





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


//                                String loginuser = obj.getString("username");
                            //    Toast.makeText(getApplicationContext(),loginuser, Toast.LENGTH_SHORT).show();


                                finish();
                                Intent intent = new Intent(Signup.this, MainActivity.class);

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
                params.put("email", email.getText().toString());
                params.put("password", password.getText().toString());
                params.put("firstname", firstname.getText().toString());
                params.put("lastname", lastname.getText().toString());
                params.put("gender", gender.getSelectedItem().toString());
                params.put("number", phone.getText().toString());



                return params;
            }
        };
        VolleySingleton.getInstance(Signup.this).addToRequestQueue(stringRequest);
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
