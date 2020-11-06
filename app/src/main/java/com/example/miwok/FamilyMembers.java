package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class FamilyMembers extends AppCompatActivity {
    RecyclerView familyMembersRecyclerView;
    RecyclerView.LayoutManager familyMembersLayoutManager;
    AdapterData1 familyMembersAdapter;
    ArrayList<data1> familyMembers;
    static boolean IS_IN_ACTION_MODE=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);

        familyMembersRecyclerView=findViewById(R.id.familyMembersRecyclerView);

        familyMembersLayoutManager=new LinearLayoutManager(this);
        familyMembersRecyclerView.setLayoutManager(familyMembersLayoutManager);

        familyMembers=new ArrayList<>();
        familyMembers.add(new data1("grand father","mopa",R.drawable.family_grandfather));
        familyMembers.add(new data1("grand mother","mata",R.drawable.family_grandmother));
        familyMembers.add(new data1("father","apa",R.drawable.family_father));
        familyMembers.add(new data1("mother","ata",R.drawable.family_mother));
        familyMembers.add(new data1("son","angsi",R.drawable.family_son));
        familyMembers.add(new data1("daughter","tune",R.drawable.family_daughter));
        familyMembers.add(new data1("older brother","taachi",R.drawable.family_older_brother));
        familyMembers.add(new data1("younger brother","chalitti",R.drawable.family_younger_brother));
        familyMembers.add(new data1("older sister","sistich",R.drawable.family_older_sister));
        familyMembers.add(new data1("younger sister","shiya",R.drawable.family_younger_sister));


        //familyMembersAdapter=new AdapterData1(familyMembers,R.color.familyMembers/*, IS_IN_ACTION_MODE*/);
       // familyMembersRecyclerView.setAdapter(familyMembersAdapter);
        HandleData1 family_members_Handler=new HandleData1(familyMembers,R.color.familyMembers,familyMembersRecyclerView,this);
    }
}