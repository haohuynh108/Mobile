package com.example.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class AddRestaurantActivity extends AppCompatActivity {

    private EditText inpName, inpAddress, inpPhone, inpDescription;
    AutoCompleteTextView inpTag;
    private String name, address, phone ,description,tag,rating;
    private RatingBar ratingBar;
    private Button add;

    private DatabaseHelper dbHelper ;

    private static final String[] tags = { "Vegetarian","Fast Food","Asian Cuisine","Western Cuisine","BBQ","Dessert","Drinks","Quiet","Fresh","Healthy","Noodles","Pizza", "Hamburger","Steak","Chinese Dumpling","Seafood","Fries","Party","Family-friendly","Sushi", "Sashimi","Birthday","Romantic","Highest Rated", "Hottest"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        dbHelper = new DatabaseHelper(this);
        add = findViewById(R.id.addButton);
        inpName = findViewById(R.id.restaurant_name);
        inpAddress = findViewById(R.id.restaurant_address);
        inpDescription = findViewById(R.id.restaurant_desc);
        inpPhone = findViewById(R.id.restaurant_phone);
        inpTag = findViewById(R.id.restaurant_tag);
        ratingBar = findViewById(R.id.rating);
        //Create suggestion for tag input field
        AutoCompleteTextView editText = findViewById(R.id.restaurant_tag);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,tags);
        editText.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });



    }



    private void getData() {
        name = ""+inpName.getText().toString().trim();
        address = ""+inpAddress .getText().toString().trim();
        phone = ""+inpPhone.getText().toString().trim();
        description = ""+inpDescription.getText().toString().trim();
        rating = ""+ ratingBar.getRating();
        tag = ""+inpTag.getText().toString().trim();
        long id = dbHelper.insertInfo(
                ""+name,
                ""+address,
                ""+phone,
                ""+description,
                ""+tag,
                ""+rating
        );


    }







}