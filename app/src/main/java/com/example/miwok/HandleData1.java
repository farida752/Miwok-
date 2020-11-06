package com.example.miwok;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class HandleData1 {
    ArrayList<data1>data;
    int backgroundColor;

    RecyclerView handlerRecyclerView;
    AdapterData1 handlerAdapter;
    static boolean IS_IN_ACTION_MODE=false;
    private ActionMode mActionMode;
    ArrayList<data1>selection=new ArrayList<>();
    int counter=0;
    Activity mainActivity;

    public HandleData1(final ArrayList<data1> data, int backgroundColor, RecyclerView handlerRecyclerView, final Activity mainActivity) {
        this.data = data;
        this.backgroundColor = backgroundColor;
        this.handlerRecyclerView=handlerRecyclerView;
        this.mainActivity=mainActivity;

        handlerAdapter=new AdapterData1(data,backgroundColor);
        handlerRecyclerView.setAdapter(handlerAdapter);
        //_________________________________________________________________________________________________________________________________
        handlerAdapter.setOnItemClickListener(new AdapterData1.onItemClickListener() {
            @Override
            public boolean onLongClick(int position) {
                // Toast.makeText(Colors.this, "loooooong click", Toast.LENGTH_SHORT).show();
                if(mActionMode!=null){
                    return false;}
                mActionMode=mainActivity.startActionMode(mActionModeCallBack);
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

                            for (int i=0 ;i<counter;i++){
                                int current_index=data.indexOf(selection.get(i));
                                data.remove(selection.get(i));
                               handlerAdapter.notifyItemRemoved(current_index);

                            }
                            counter=0;
                            selection=new ArrayList<>();
                            IS_IN_ACTION_MODE=false;
                            handlerAdapter.IS_IN_ACTION_MODE=false;
                            handlerAdapter.notifyDataSetChanged();
                           mActionMode.finish();
                            return true;

                        case R.id.item_share:
                            Intent intent=new Intent(Intent.ACTION_SEND_MULTIPLE);

                            intent.setType("image/jpeg");
                            intent.putExtra(intent.EXTRA_SUBJECT,"Miwok Translator");

                            String message="";
                           // Uri [] imageUri=new Uri[selection.size()];
                            ArrayList<Uri>imageUri=new ArrayList<>();
                            for (int i =0;i<selection.size();i++){
                                //sent_items.add(data.get(i));
                                message=message+"Miwok : "+selection.get(i).miwok_text+"\n ";
                                message=message+"English :"+selection.get(i).english_text+"\n"+"\n";
                               // intent.putExtra(intent.EXTRA_STREAM,sent_items.get(i).image);
                                /* imageUri.add((new Uri.Builder())
                                        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                                        .authority(mainActivity.getResources().getResourcePackageName(selection.get(i).image))
                                        .appendPath(mainActivity.getResources().getResourceTypeName(selection.get(i).image))
                                        .appendPath(mainActivity.getResources().getResourceEntryName(selection.get(i).image))
                                        .build());*/
                                String imagePath="android:resource://"+mainActivity.getPackageName()+"/"+selection.get(i).image;
                                imageUri.add(Uri.parse(imagePath));
                              //  intent.putExtra(intent.EXTRA_STREAM,imageUri);
                            }
                            intent.putExtra(Intent.EXTRA_STREAM,imageUri);
                            intent.putExtra(intent.EXTRA_TEXT,message);
                            if(intent.resolveActivity(mainActivity.getPackageManager())!=null){
                               mainActivity. startActivity(intent.createChooser(intent,"Open with"));
                            }

                            IS_IN_ACTION_MODE=false;
                            handlerAdapter.IS_IN_ACTION_MODE=false;
                            handlerAdapter.notifyDataSetChanged();
                            mActionMode.finish();
                            return true;

                        default:
                            return false;}
                }

                @Override
                public void onDestroyActionMode(ActionMode actionMode) {
                    mActionMode=null;
                    IS_IN_ACTION_MODE=false;
                    handlerAdapter.IS_IN_ACTION_MODE=false;
                    handlerAdapter.notifyDataSetChanged();
                    //-------------returns count to zero and selection to null
                    counter=0;
                    selection=new ArrayList<>();
                   // mActionMode.setTitle(counter+"items selected");
                }
            };
            //______________________________________________________________________________________________________________________________________
            @Override
            public void onCheckBoxClick(int position, View v) {
                //Toast.makeText(Colors.this, "item:"+position+" is checked now !", Toast.LENGTH_SHORT).show();
                //prepare selection------global  int selection list access the positions selected and varriable count
                if (((CheckBox)v).isChecked()){
                    selection.add(data.get(position));
                    counter++;
                    mActionMode.setTitle(counter+" items selected");}
                else{
                    selection.remove(data.get(position));
                    counter--;
                    mActionMode.setTitle(counter+" items selected");
                }

            }
        });


    }


}
