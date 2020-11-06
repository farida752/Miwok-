package com.example.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



//___________________________________________________________________________________________________________________________________
public class AdapterData1 extends RecyclerView.Adapter<AdapterData1.ViewHolderData1> {

    public interface onItemClickListener{
        boolean onLongClick(int position);
        void onCheckBoxClick(int position,View v);
    }

    ArrayList<data1> data;
    int backgroundColor;
    int usedColor;
    boolean IS_IN_ACTION_MODE=false;
    onItemClickListener listener;

    public AdapterData1(ArrayList<data1> data, int backgroundColor/*boolean IS_IN_ACTION_MODE*/ ) {
        this.data = data;
        this. backgroundColor=backgroundColor;
        //this.IS_IN_ACTION_MODE=IS_IN_ACTION_MODE;
    }
    //___________________________________________________________________________________________________________________________________
    public void setOnItemClickListener(onItemClickListener listener){
        this.listener=listener;
    }
    //___________________________________________________________________________________________________________________________________

    @NonNull
    @Override
    public ViewHolderData1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_1,parent,false);
        ViewHolderData1 holder=new ViewHolderData1(v);
        usedColor=parent.getContext().getResources().getColor(backgroundColor);
        return holder;
    }
    //___________________________________________________________________________________________________________________________________

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData1 holder, int position) {
        data1 currentItem =data.get(position);

        holder.miwok_textview.setText(currentItem.getMiwok_text());
        holder.english_textview.setText(currentItem.getEnglish_text());
        holder.image.setImageResource(currentItem.getImage());
        holder.backgroundLayout.setBackgroundColor(usedColor);
        if(!IS_IN_ACTION_MODE){
            holder.checkBox.setVisibility(View.GONE);
        }
        else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }

    }
    //___________________________________________________________________________________________________________________________________
    @Override
    public int getItemCount() {
        return data.size();
    }
    //___________________________________________________________________________________________________________________________________

    public class ViewHolderData1 extends RecyclerView.ViewHolder{

        TextView miwok_textview;
        TextView english_textview;
        ImageView image;
        LinearLayout backgroundLayout;
        CheckBox checkBox;
        public ViewHolderData1(@NonNull View itemView) {
            super(itemView);
            miwok_textview=itemView.findViewById(R.id.textview1);
            english_textview=itemView.findViewById(R.id.textview2);
            image=itemView.findViewById(R.id.image);
            backgroundLayout=itemView.findViewById(R.id.background_linearlayout);
            checkBox=itemView.findViewById(R.id.check_items);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    if (listener!=null){
                        int position =getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        IS_IN_ACTION_MODE=true;
                        notifyDataSetChanged();
                        return listener.onLongClick(position);

                    }}
                    return false;
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
