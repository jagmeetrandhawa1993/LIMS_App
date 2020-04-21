package com.example.lims_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    Button btnNewSample;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView t=(TextView) findViewById(R.id.textView3);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("userlogin");
            //The key argument here must match that used in the other activity
            t.setText(value);
        }

        btnNewSample=(Button) findViewById(R.id.btnNewSample);
        btnLogout = (Button) findViewById(R.id.btnlogout);
        btnNewSample.setOnClickListener(new View.OnClickListener(){

                                         @Override
                                         public void onClick(View view) {
                                             Intent intent = new Intent(Menu.this, Newbatchform.class);

                                             startActivity(intent);

                                         }
                                     }


        );


        btnLogout.setOnClickListener(new View.OnClickListener(){

                                         @Override
                                         public void onClick(View view) {
                                             Intent intent = new Intent(Menu.this, SplashActivity.class);

                                             startActivity(intent);

                                         }
                                     }


        );
    }
}