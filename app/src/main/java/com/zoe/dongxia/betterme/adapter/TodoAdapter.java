package com.zoe.dongxia.betterme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zoe.dongxia.betterme.R;
import com.zoe.dongxia.betterme.model.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private Context mContext;
    private List<Todo> mTodoList;


    public TodoAdapter(List<Todo> todoList){
        mTodoList = todoList;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTodoName;

        ViewHolder(View itemView){
            super(itemView);
            mTodoName = itemView.findViewById(R.id.todo_name);
        }

        void setTodoName(String name){
            mTodoName.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.todo_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setTodoName(mTodoList.get(position).getName());
    }
}
