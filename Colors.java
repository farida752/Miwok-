package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {
RecyclerView colorsRecyclerView;
RecyclerView.LayoutManager colorsLayoutManager;
AdapterData1 colorsAdapter;
ArrayList<data1> colors;
static boolean IS_IN_ACTION_MODE=false;
private ActionMode mActionMode;
ArrayList<Integer>selection=new ArrayList<>();
int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        colorsRecyclerView=findViewById(R.id.colorsRecyclerView);

        colorsLayoutManager=new LinearLayoutManager(this);
        colorsRecyclerView.setLayoutManager(colorsLayoutManager);

        colors=new ArrayList<>();
        colors.add(new data1("red","wetetti",R.drawable.color_red));
        colors.add(new data1("yellow","tayayyi",R.drawable.color_mustard_yellow));
        colors.add(new data1("green","chokokki",R.drawable.color_green));
        colors.add(new data1("brown","takaakki",R.drawable.color_brown));
        colors.add(new data1("black","a;lymca",R.drawable.color_black));
        colors.add(new data1("grey","co'ki",R.drawable.color_gray));
        colors.add(new data1("dusty yellow","teroori",R.drawable.color_dusty_yellow));
        colors.add(new data1("white","wroioi",R.drawable.color_white));

       // colorsAdapter=new AdapterData1(colors,R.color.colors/*,IS_IN_ACTION_MODE*/);
       // colorsRecyclerView.setAdapter(colorsAdapter);
        HandleData1 colors_Handler=new HandleData1(colors,R.color.colors,colorsRecyclerView,this);
//_________________________________________________________________________________________________________________________________
      /*  colorsAdapter.setOnItemClickListener(new AdapterData1.onItemClickListener() {
            @Override
            public boolean onLongClick(int position) {
               // Toast.makeText(Colors.this, "loooooong click", Toast.LENGTH_SHORT).show();
                if(mActionMode!=null){
                    return false;}
                mActionMode=startActionMode(mActionModeCallBack);
                IS_IN_ACTION_MODE=true;
                return true;
            }
            //________________________________________________________________________________________________________________________
            private ActionMode.Callback mActionModeCallBack =new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    actionMode.getMenuInflater().inflate(R.menu.context_menu,menu);
                    actionMode.setTitle("0 item selected");
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                   int id= menuItem.getItemId();
                   switch (id){
                       case R.id.item_delete:
                           //Toast.makeText(Colors.this, "we will delete tomorrow", Toast.LENGTH_SHORT).show();
                          // deleteItem()------deals with the selection list and count
                           for (int i :selection){
                               colors.remove(colors.get(i));
                               colorsAdapter.notifyItemRemoved(i);
                           }

                           return true;

                    case R.id.item_share:
                        //Toast.makeText(Colors.this, "we will insert tomorrow", Toast.LENGTH_SHORT).show();
                        //insertItem()------deals with the selection list and count
                        Intent intent=new Intent(Intent.ACTION_SEND);

                      /*  intent.putExtra(intent.EXTRA_SUBJECT,"Miwok Translator");
                        ArrayList<data1> sent_items=new ArrayList<>();

                        for (int i :selection){
                            sent_items.add(colors.get(i));
                            String message;
                            message="Miwok : "+sent_items.get(i).miwok_text+"   ";
                            message=message+"English :"+sent_items.get(i).english_text;
                            intent.putExtra(intent.EXTRA_TEXT,message);
                            intent.putExtra(intent.EXTRA_STREAM,sent_items.get(i).image);
                        }
                        if(intent.resolveActivity(getPackageManager())!=null){
                            startActivity(intent);
                        }

                        return true;

                     default:
                        return false;}
                }

                @Override
                public void onDestroyActionMode(ActionMode actionMode) {
                 mActionMode=null;
                 IS_IN_ACTION_MODE=false;
                 colorsAdapter.IS_IN_ACTION_MODE=false;
                 //-------------returns count to zero and selection to null
                    counter=0;
                    selection=null;
                }
            };
//______________________________________________________________________________________________________________________________________
            @Override
            public void onCheckBoxClick(int position, View v) {
                //Toast.makeText(Colors.this, "item:"+position+" is checked now !", Toast.LENGTH_SHORT).show();
                //prepare selection------global  int selection list access the positions selected and varriable count
                if (((CheckBox)v).isChecked()){
                    selection.add(position);
                    counter++;}
                else{
                    selection.remove(position);
                    counter--;

                    }

            }
        });*/


    }
}