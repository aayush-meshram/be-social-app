package com.geniobits.besocialapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geniobits.besocialapp.customVariable.UserInfo;

public class ListOfPost extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserInfo info = getIntent().getParcelableExtra("userInfo");
        textView = findViewById(R.id.postTitle);

        textView.setText(info.getUserName());

    }
}
