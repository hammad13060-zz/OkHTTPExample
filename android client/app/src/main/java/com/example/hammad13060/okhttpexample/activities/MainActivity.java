package com.example.hammad13060.okhttpexample.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hammad13060.okhttpexample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddUserClick(View view) {

    }

    public void onGetAllUsersClick(View view) {
        Intent intent = new Intent(this, AllUsersActivity.class);
        startActivity(intent);
    }

    public void onGetUserClick(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}
