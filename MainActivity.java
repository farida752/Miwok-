package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView colors;
    TextView numbers;
    TextView familyMembers;
    TextView phrases;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViwes();
        setListeners(colors,Colors.class);
        setListeners(numbers,Numbers.class);
        setListeners(familyMembers,FamilyMembers.class);
        setListeners(phrases,Phrases.class);


    }


    public void findViwes() {
        colors=findViewById(R.id.textview_colors);
        numbers=findViewById(R.id.textview_numbers);
        familyMembers=findViewById(R.id.textview_familyMembers);
        phrases=findViewById(R.id.textview_phrases);
    }

    public void setListeners(TextView textView, final Class activity){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,activity)  ;
                startActivity(intent);
            }
        });

    }

}