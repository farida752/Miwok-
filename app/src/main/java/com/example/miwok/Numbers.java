package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {
    RecyclerView numbersRecyclerView;
    RecyclerView.LayoutManager numbersLayoutManager;
    AdapterData1 numbersAdapter;
    ArrayList<data1> numbers;
    static boolean IS_IN_ACTION_MODE=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        numbersRecyclerView=findViewById(R.id.numbersRecyclerView);

        numbersLayoutManager=new LinearLayoutManager(this);
        numbersRecyclerView.setLayoutManager(numbersLayoutManager);

        numbers=new ArrayList<>();
        numbers.add(new data1("one","lutti",R.drawable.number_one));
        numbers.add(new data1("two","otiiko",R.drawable.number_two));
        numbers.add(new data1("three","tolookosu",R.drawable.number_three));
        numbers.add(new data1("four","oyyisa",R.drawable.number_four));
        numbers.add(new data1("massokka","five",R.drawable.number_five));
        numbers.add(new data1("six","temmokka",R.drawable.number_six));
        numbers.add(new data1("seven","kenekaku",R.drawable.number_seven));
        numbers.add(new data1("eight","velona",R.drawable.number_eight));
        numbers.add(new data1("nine","ninona",R.drawable.number_nine));
        numbers.add(new data1("ten","tennona",R.drawable.number_ten));

        //numbersAdapter=new AdapterData1(numbers,R.color.numbers/*,IS_IN_ACTION_MODE*/);
        //numbersRecyclerView.setAdapter(numbersAdapter);
        HandleData1 family_members_Handler=new HandleData1(numbers,R.color.numbers,numbersRecyclerView,this);
    }
}