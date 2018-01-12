package com.zoe.dongxia.betterme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zoe.dongxia.betterme.R;
import com.zoe.dongxia.betterme.proxy.AddTodoDialogProxy;


public class TodoFragment extends Fragment {
    private static final String TAG = "TodoFragment";
    
    public static TodoFragment newInstance() {
        TodoFragment fragment = new TodoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false);
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
}
