package com.example.lims_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Newbatchform extends AppCompatActivity {

    final String batchURL = Config.URL_ADDBATCH;
    EditText receivedby,batchname,cocnumber,datetimereceived,noofsamples,containers,specialinstructions,selectednames,selectedpackages,selectedflags,duedate,shipperreference ;
    Spinner site,client,ponumber,project,quote,submittedby,invoiceto,sampletype,prepcode,status,reportto,addpackage,addflag,shipper;
    Button btnaddbatch,btnlogout ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newbatchform);

        receivedby = (EditText) findViewById(R.id.txtreceivedby);
        site = (Spinner) findViewById(R.id.site);
        batchname = (EditText) findViewById(R.id.txtbatchname);
        cocnumber = (EditText) findViewById(R.id.txtcocnumber);
        client = (Spinner) findViewById(R.id.client);
        datetimereceived = (EditText) findViewById(R.id.txtdate);
        ponumber = (Spinner) findViewById(R.id.txtponumber);
        project=(Spinner) findViewById(R.id.txtproject);
        quote = (Spinner) findViewById(R.id.txtquote);
        submittedby = (Spinner) findViewById(R.id.txtsubmittedby);
        invoiceto = (Spinner) findViewById(R.id.txtinvoiceto);
        sampletype = (Spinner) findViewById(R.id.txtsampletype);
        prepcode = (Spinner) findViewById(R.id.txtprepcode);
        noofsamples = (EditText) findViewById(R.id.txtnoofsamples);
        containers = (EditText)findViewById(R.id.txtcontainers);
        status = (Spinner) findViewById(R.id.txtstatus);
        specialinstructions = (EditText) findViewById(R.id.txtinstructions);
        reportto = (Spinner) findViewById(R.id.txtreportto);
        selectednames = (EditText) findViewById(R.id.txtselectednames);
        addpackage = (Spinner) findViewById(R.id.txtaddpackage);
        selectedpackages = (EditText) findViewById(R.id.txtselectedpackages);
        addflag = (Spinner) findViewById(R.id.txtaddflag);
        selectedflags = (EditText) findViewById(R.id.txtselectedflags);
        duedate = (EditText) findViewById(R.id.txtduedate);
        shipper = (Spinner) findViewById(R.id.txtshipper);
        shipperreference = (EditText) findViewById(R.id.txtshipperreference);
        btnaddbatch=(Button) findViewById(R.id.btnaddbatch);
        btnlogout=(Button) findViewById(R.id.btnlogout);
        btnaddbatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(receivedby.getText().toString())||TextUtils.isEmpty(batchname.getText().toString())||TextUtils.isEmpty(cocnumber.getText().toString())||TextUtils.isEmpty(noofsamples.getText().toString())||TextUtils.isEmpty(specialinstructions.getText().toString())||TextUtils.isEmpty(selectednames.getText().toString())||TextUtils.isEmpty(selectedpackages.getText().toString())||TextUtils.isEmpty(selectedflags.getText().toString()) ||TextUtils.isEmpty(duedate.getText().toString())||TextUtils.isEmpty(shipperreference.getText().toString()) || TextUtils.isEmpty(containers.getText().toString())) {

                    alertbox("Invalid","Enter All Fields");
                    btnaddbatch.setEnabled(true);
                    return;
                }else {
                    addbatch();
                }
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener(){

                                         @Override
                                         public void onClick(View view) {
                                             Intent intent = new Intent(Newbatchform.this, SplashActivity.class);

                                             startActivity(intent);

                                         }
                                     }


        );
    }
    private void addbatch() {





        //Call our volley library
        StringRequest stringRequest = new StringRequest(Request.Method.POST,batchURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
//
                       //     Toast.makeText(getApplicationContext(),response.toString(), Toast.LENGTH_SHORT).show();
                            JSONObject obj = new JSONObject(response);
                            if (obj.getBoolean("error")) {

                                alertbox("Invalid",obj.getString("message"));
                              //  email.setText("");
                              //  password.setText("");
                            } else {


//                                String loginuser = obj.getString("username");
                                //    Toast.makeText(getApplicationContext(),loginuser, Toast.LENGTH_SHORT).show();


                                finish();
                                Intent intent = new Intent(Newbatchform.this,Menu.class);

                                startActivity(intent);

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

                params.put("receivedby", receivedby.getText().toString());
                params.put("site", site.getSelectedItem().toString());
                params.put("batchname", batchname.getText().toString());
                params.put("cocnumber", cocnumber.getText().toString());
                params.put("client", client.getSelectedItem().toString());
                params.put("datetimereceived", datetimereceived.getText().toString());
                params.put("ponumber", ponumber.getSelectedItem().toString());
                params.put("project", project.getSelectedItem().toString());
                params.put("quote", quote.getSelectedItem().toString());
                params.put("submittedby", submittedby.getSelectedItem().toString());
                params.put("invoiceto", invoiceto.getSelectedItem().toString());
                params.put("sampletype", sampletype.getSelectedItem().toString());
                params.put("prepcode", prepcode.getSelectedItem().toString());
                params.put("noofsample", noofsamples.getText().toString());
                params.put("containers", containers.getText().toString());
                params.put("status", status.getSelectedItem().toString());
                params.put("specialinstructions", specialinstructions.getText().toString());
                params.put("reportto", reportto.getSelectedItem().toString());
                params.put("selectednames", selectednames.getText().toString());

                params.put("addpackage", addpackage.getSelectedItem().toString());
                params.put("selectedpackages", selectedpackages.getText().toString());


                params.put("addflag", addflag.getSelectedItem().toString());
                params.put("selectedflags", selectedflags.getText().toString());
                params.put("duedate", duedate.getText().toString());


                params.put("shipper", shipper.getSelectedItem().toString());
                params.put("shipperreference", shipperreference.getText().toString());


                return params;
            }
        };
        VolleySingleton.getInstance(Newbatchform.this).addToRequestQueue(stringRequest);
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
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }


        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        }
    }
}


