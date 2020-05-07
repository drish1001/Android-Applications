package com.example.mail_sender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSend = findViewById(R.id.sendMail);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent si = new Intent(Intent.ACTION_SEND);
                si.setType("message/rfc822");
                si.putExtra(Intent.EXTRA_EMAIL, new String[]{"write_email_here.com"});
                si.putExtra(Intent.EXTRA_SUBJECT, "Enter Subject");
                si.putExtra(Intent.EXTRA_TEXT, "Congrats, your application works");
                startActivity(Intent.createChooser(si,"Choose Mail App"));
            }
        });
    }
}