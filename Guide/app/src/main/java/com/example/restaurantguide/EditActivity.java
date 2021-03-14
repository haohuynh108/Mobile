package com.example.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private EditText inpName, inpAddress, inpPhone, inpDescription;
    AutoCompleteTextView inpTag;
    private String name, address, phone ,description,tag,rating,id;
    private RatingBar ratingBar;
    private Button add;
    private boolean EditMode = false;
    private DatabaseHelper dbHelper ;

    private static final String[] tags = { "Vegetarian","Fast Food","Asian Cuisine","Western Cuisine","BBQ","Dessert","Drinks" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        dbHelper = new DatabaseHelper(this);
        add = findViewById(R.id.addButton);
        inpName = findViewById(R.id.restaurant_name);
        inpAddress = findViewById(R.id.restaurant_address);
        inpDescription = findViewById(R.id.restaurant_desc);
        inpPhone = findViewById(R.id.restaurant_phone);
        inpTag = findViewById(R.id.restaurant_tag);
        ratingBar = findViewById(R.id.rating);

        //Intent
        Intent intent = getIntent();
        EditMode = intent.getBooleanExtra("EditMode",EditMode);
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        address = intent.getStringExtra("ADDRESS");
        phone = intent.getStringExtra("PHONE");
        description = intent.getStringExtra("DESC");
        tag = intent.getStringExtra("TAG");
        rating = intent.getStringExtra("RATING");

        if (EditMode){
            id = intent.getStringExtra("ID");
            name = intent.getStringExtra("NAME");
            address = intent.getStringExtra("ADDRESS");
            phone = intent.getStringExtra("PHONE");
            description = intent.getStringExtra("DESC");
            tag = intent.getStringExtra("TAG");
            rating = intent.getStringExtra("RATING");

            inpAddress.setText(address);
            inpName.setText(name);
            inpPhone.setText(phone);
            inpDescription.setText(description);
            inpTag.setText(tag);
            ratingBar.setRating(Float.parseFloat(rating));

        }

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
        if (EditMode){
            System.out.println(id);
            dbHelper.updateInfo(""+id,
                    ""+name,
                    ""+address,
                    ""+phone,
                    ""+description,
                    ""+tag,
                    ""+rating);
        }
        else {
            dbHelper.insertInfo(
                    "" + name,
                    "" + address,
                    "" + phone,
                    "" + description,
                    "" + tag,
                    "" + rating
            );
        }

    }


}
