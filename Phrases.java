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

import java.util.ArrayList;

public class Phrases extends AppCompatActivity {

    RecyclerView phrasesRecyclerView;
    RecyclerView.LayoutManager phrasesLayoutManager;
    AdapterData2 phrasesAdapter;
    ArrayList<data2> phrases;
    static boolean IS_IN_ACTION_MODE=false;
    private ActionMode mActionMode;
    ArrayList<data2>selection=new ArrayList<>();
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);


        phrasesRecyclerView=findViewById(R.id.phrasesRecyclerView);

        phrasesLayoutManager=new LinearLayoutManager(this);
        phrasesRecyclerView.setLayoutManager(phrasesLayoutManager);

        phrases=new ArrayList<>();
        phrases.add(new data2("minto wuksus","Where are you going?"));
        phrases.add(new data2("tunna oyaase'na","What is your name?"));
        phrases.add(new data2("oyaaset......","My name is ......"));
        phrases.add(new data2("michaksas?","How are you feeling?"));
        phrases.add(new data2("kuchi achit","I'm feeling good."));
        phrases.add(new data2("aanas'aa?","Are you coming?"));
        phrases.add(new data2("haa'aanam","Yes,I'm coming"));


        phrasesAdapter=new AdapterData2(phrases,IS_IN_ACTION_MODE);
        phrasesRecyclerView.setAdapter(phrasesAdapter);

         phrasesAdapter.setOnItemClickListener(new AdapterData2.onItemClickListener() {
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
                           for (int i=0 ;i<counter;i++){
                               int current_index=phrases.indexOf(selection.get(i));
                               phrases.remove(selection.get(i));
                               phrasesAdapter.notifyItemRemoved(current_index);

                           }
                           counter=0;
                           selection=new ArrayList<>();
                           IS_IN_ACTION_MODE=false;
                           phrasesAdapter.IS_IN_ACTION_MODE=false;
                           phrasesAdapter.notifyDataSetChanged();
                           mActionMode.finish();
                           return true;

                    case R.id.item_share:
                        //Toast.makeText(Colors.this, "we will insert tomorrow", Toast.LENGTH_SHORT).show();
                        //insertItem()------deals with the selection list and count
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.setType("*/*");
                        intent.putExtra(intent.EXTRA_SUBJECT,"Miwok Translator");


                        String message="";
                        for (int i =0;i<selection.size();i++){
                            message=message+"Miwok : "+selection.get(i).miwok_phrase+"\n ";
                            message=message+"English :"+selection.get(i).english_phrase+"\n"+"\n";
                        }
                        intent.putExtra(intent.EXTRA_TEXT,message);
                        if(intent.resolveActivity(getPackageManager())!=null){
                            startActivity(intent);
                        }
                        IS_IN_ACTION_MODE=false;
                        phrasesAdapter.IS_IN_ACTION_MODE=false;
                        phrasesAdapter.notifyDataSetChanged();
                        mActionMode.finish();
                        return true;

                     default:
                        return false;}
                }

                @Override
                public void onDestroyActionMode(ActionMode actionMode) {
                 mActionMode=null;
                 IS_IN_ACTION_MODE=false;
                 phrasesAdapter.IS_IN_ACTION_MODE=false;
                 phrasesAdapter.notifyDataSetChanged();
                 //-------------returns count to zero and selection to null
                    counter=0;
                    selection=new ArrayList<>();
                  //  mActionMode.setTitle(counter+" items selected");
                }
            };
//______________________________________________________________________________________________________________________________________
            @Override
            public void onCheckBoxClick(int position, View v) {
                //Toast.makeText(Colors.this, "item:"+position+" is checked now !", Toast.LENGTH_SHORT).show();
                //prepare selection------global  int selection list access the positions selected and varriable count
                if (((CheckBox)v).isChecked()){
                    selection.add(phrases.get(position));
                    counter++;
                    mActionMode.setTitle(counter+" items selected");}
                else{
                    selection.remove(phrases.get(position));
                    counter--;
                    mActionMode.setTitle(counter+" items selected");
                    }

            }
        });

    }
}