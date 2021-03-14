package com.example.restaurantguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;

import com.example.restaurantguide.Adapter.SearchAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter searchAdapter;

    MaterialSearchBar materialSearchBar;

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.addFabButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity();
            }
        });
        //init View
        recyclerView = (RecyclerView)findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        materialSearchBar = (MaterialSearchBar)findViewById(R.id.search_bar);


        //init db
        db = new DatabaseHelper(this);
        //Search bar setup
        materialSearchBar.setHint("Restaurant name or tag");
        materialSearchBar.setCardViewElevation(10);
      //Search
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    searchAdapter = new SearchAdapter(getBaseContext(),db.getRestaurant());
                    recyclerView.setAdapter(searchAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        searchAdapter = new SearchAdapter(this,db.getRestaurant());
        recyclerView.setAdapter(searchAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        switch (id){
            case R.id.add:
                AddActivity();
                break;
            case R.id.about:
                About();
                break;
        }
        return super .onOptionsItemSelected(item);
    }

    private void AddActivity(){
        Intent start = new Intent(getApplicationContext(),AddRestaurantActivity.class);
        start.putExtra("EditMode",false);
        startActivity(start);
    }
    private void About(){
        Intent start = new Intent(getApplicationContext(),AboutActivity.class);
        startActivity(start);
    }

    private void startSearch(String text){
        searchAdapter = new SearchAdapter(this,db.findRestaurantByName(text));
        recyclerView.setAdapter(searchAdapter);
    }
}