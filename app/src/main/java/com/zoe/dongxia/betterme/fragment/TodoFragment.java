package com.zoe.dongxia.betterme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zoe.dongxia.betterme.R;
import com.zoe.dongxia.betterme.adapter.TodoAdapter;
import com.zoe.dongxia.betterme.model.Todo;
import com.zoe.dongxia.betterme.proxy.AddTodoDialogProxy;

import java.util.ArrayList;
import java.util.List;


public class TodoFragment extends Fragment {
    private static final String TAG = "TodoFragment";

    private RecyclerView mRecyclerView;
    private TodoAdapter mTodoAdapter;
    private List<Todo> mTodoList;

    public static TodoFragment newInstance() {
        TodoFragment fragment = new TodoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_todo, container, false);
        initView(root);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.todo_toolbar_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                new AddTodoDialogProxy(getContext()).getDialog().show();
                break;
            case R.id.edit:

                break;
            case R.id.history:

                break;
            case R.id.hard:

                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(View root){
        mRecyclerView = root.findViewById(R.id.todo_list_view);
        mTodoAdapter = new TodoAdapter(mTodoList);
        mRecyclerView.setAdapter(mTodoAdapter);
    }

    private void initData(){
        mTodoList = new ArrayList<>();
        mTodoList.add(new Todo("123"));
        mTodoList.add(new Todo("456"));
    }
}
