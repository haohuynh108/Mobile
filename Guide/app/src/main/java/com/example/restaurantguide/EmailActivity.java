package com.example.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends AppCompatActivity {
    String name,address,phone,description,tag,rating;
    EditText txtTo,txtSubject,txtBody;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        final Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        address = intent.getStringExtra("ADDRESS");
        phone = intent.getStringExtra("PHONE");
        description = intent.getStringExtra("DESC");
        tag = intent.getStringExtra("TAG");
        rating = intent.getStringExtra("RATING");
        btnSend = findViewById(R.id.btnSend);

        txtTo = findViewById(R.id.email_to);
        txtSubject = findViewById(R.id.email_subject);
        txtBody = findViewById(R.id.email_body);
        String subject = "Check out this restaurant!!!";
        String body = "Hi, I have come across this great restaurant, here is the information:"+
                "\nRestaurant name: "+name+
                "\nAddress: "+address+
                "\nPhone number: "+phone+
                "\nDescription: "+description+
                "\nTags: "+tag+
                "\nRating: "+rating+" star(s)";

        txtSubject.setText(subject);
        txtBody.setText(body);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_VIEW
                        , Uri.parse("mailto:"+txtTo.getText().toString()));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, txtSubject.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_TEXT,txtBody.getText().toString());
                startActivity(emailIntent);
            }
        });
    }
}