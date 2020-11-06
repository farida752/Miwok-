package com.example.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AdapterData2 extends RecyclerView.Adapter<AdapterData2.ViewHolderData2> {

    public interface onItemClickListener{
        boolean onLongClick(int position);
        void onCheckBoxClick(int position,View v);
    }

ArrayList<data2> data;
boolean IS_IN_ACTION_MODE;
onItemClickListener listener;
    public AdapterData2(ArrayList<data2> data,boolean IS_IN_ACTION_MODE) {
        this.data = data;
        this.IS_IN_ACTION_MODE=IS_IN_ACTION_MODE;
    }
//____________________________________________________________________________________________________________________________
public void setOnItemClickListener(onItemClickListener listener){
    this.listener=listener;
 }
    //____________________________________________________________________________________________________________________________
    @NonNull
    @Override
    public ViewHolderData2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_2,parent,false);
        ViewHolderData2 holder=new ViewHolderData2(v);
        return holder;
    }
    //____________________________________________________________________________________________________________________________
    @Override
    public void onBindViewHolder(@NonNull ViewHolderData2 holder, int position) {
        data2 currentItem =data.get(position);

        holder.miwokPhrase_textview.setText(currentItem.getMiwok_phrase());
        holder.englishPhrase_textview.setText(currentItem.getEnglish_phrase());

        if(!IS_IN_ACTION_MODE){
            holder.checkBox.setVisibility(View.GONE);
        }
        else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }
    }
    //____________________________________________________________________________________________________________________________
    @Override
    public int getItemCount() {
        return data.size();
    }
    //____________________________________________________________________________________________________________________________
    public class ViewHolderData2 extends RecyclerView.ViewHolder{

        TextView miwokPhrase_textview;
        TextView englishPhrase_textview;
        CheckBox checkBox;
        public ViewHolderData2(@NonNull View itemView) {
            super(itemView);
            miwokPhrase_textview=itemView.findViewById(R.id.layout2_textview1);
            englishPhrase_textview=itemView.findViewById(R.id.layout2_textview2);
            checkBox=itemView.findViewById(R.id.check_items_2);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    if (listener!=null){
                        int position =getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            IS_IN_ACTION_MODE=true;
                            notifyDataSetChanged();
                            listener.onLongClick(position);
                        }}
                    return true;
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null){
                        int position =getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onCheckBoxClick(position,checkBox);
                        }}
                }
            });
        }
    }
}
