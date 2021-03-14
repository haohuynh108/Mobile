package com.example.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    TextView txtName,txtAddress,txtPhone,txtDesc,txtTag;
    RatingBar ratingBar;
    String id,name,address,phone,description,tag,rating;
    Button btnEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        txtName = findViewById(R.id.restaurant_name);
        txtAddress = findViewById(R.id.restaurant_address);
        txtPhone = findViewById(R.id.restaurant_phone);
        txtDesc = findViewById(R.id.restaurant_desc);
        txtTag = findViewById(R.id.restaurant_tag);
        ratingBar = findViewById(R.id.rating);
        btnEmail = findViewById(R.id.btnEmail);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        address = intent.getStringExtra("ADDRESS");
        phone = intent.getStringExtra("PHONE");
        description = intent.getStringExtra("DESC");
        tag = intent.getStringExtra("TAG");
        rating = intent.getStringExtra("RATING");

        txtName.setText(name);
        txtAddress.setText(address);
        txtPhone.setText(phone);
        txtDesc.setText(description);
        txtTag.setText(tag);
        ratingBar.setRating(Float.parseFloat(rating));

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmail();
            }
        });


    }

    private void openEmail() {
        Intent emailIntent = new Intent(this,EmailActivity.class);
        emailIntent.putExtra("NAME",name);
        emailIntent.putExtra("ADDRESS",address);
        emailIntent.putExtra("PHONE",phone);
        emailIntent.putExtra("DESC",description);
        emailIntent.putExtra("TAG",tag);
        emailIntent.putExtra("RATING",rating);
        this.startActivity(emailIntent);
    }
}