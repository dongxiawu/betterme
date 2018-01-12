package com.zoe.dongxia.betterme.proxy;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zoe.dongxia.betterme.R;

public class AddTodoDialogProxy {
    private Context mContext;

    private Dialog mAddTodoDialog;

    private RadioGroup mToDoTypeGroup;

    private EditText mTodoName;

    public AddTodoDialogProxy(@NonNull Context context){
        mContext = context;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View title = initTitleView();
        View content = initContentView();
        mAddTodoDialog = builder.setCustomTitle(title).setView(content).create();
    }

    private View initTitleView(){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View title = inflater.inflate(R.layout.add_todo_dialog_title,null);

        View done = title.findViewById(R.id.done);
        done.setOnClickListener(mOnClickListener);
        View cancel = title.findViewById(R.id.cancel);
        cancel.setOnClickListener(mOnClickListener);
        return title;
    }

    private View initContentView(){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View content = inflater.inflate(R.layout.add_todo_dialog_content,null);
        mTodoName = content.findViewById(R.id.todo_name);
        mToDoTypeGroup = content.findViewById(R.id.todo_type);

        return content;
    }

    public Dialog getDialog(){
        return mAddTodoDialog;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.done:
                    if (mTodoName.getEditableText().toString().isEmpty()){
                        Toast.makeText(mContext,"待办名称不能为空",Toast.LENGTH_SHORT).show();
                    }else {

                        mAddTodoDialog.dismiss();
                    }
                    break;
                case R.id.cancel:
                    mAddTodoDialog.cancel();
                    break;
                    default:break;
            }
        }
    };




    public void addTodoDoneListener(TodoDoneListener listener){

    }

    public interface TodoDoneListener{

    }

}
