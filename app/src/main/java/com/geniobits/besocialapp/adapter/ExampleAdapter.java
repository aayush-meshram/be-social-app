package com.geniobits.besocialapp.adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.geniobits.besocialapp.R;
import com.geniobits.besocialapp.customVariable.PostInfo;

import java.util.ArrayList;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<PostInfo> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
        }
    }

    public ExampleAdapter(ArrayList<PostInfo> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_info, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        PostInfo currentItem = mExampleList.get(position);

        holder.mTextView1.setText(currentItem.getTitle());
        Log.i(TAG, "onBindViewHolder: "+currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}