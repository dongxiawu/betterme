package com.zoe.dongxia.betterme.proxy;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zoe.dongxia.betterme.R;
import com.zoe.dongxia.betterme.helper.TodoSQLiteHelper;

public class AddTodoDialogProxy {
    private Context mContext;

    private Dialog mAddTodoDialog;

    private EditText mTodoName;

    private EditText mTodoPeriod;

    private TodoSQLiteHelper mSQLiteHelper;

    public AddTodoDialogProxy(@NonNull Context context){
        mContext = context;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View title = initTitleView();
        View content = initContentView();
        mAddTodoDialog = builder.setCustomTitle(title).setView(content).create();
        mSQLiteHelper = new TodoSQLiteHelper(context,"Todo.db",null,1);
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
        mTodoPeriod = content.findViewById(R.id.todo_period);

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
                    if (!isLegal()){
                        Toast.makeText(mContext,"参数不合法",Toast.LENGTH_SHORT).show();
                    }else {
                        String name = mTodoName.getEditableText().toString();

                        int period = 0;
                        if (mTodoPeriod.getEditableText().toString().isEmpty()){
                            period = Integer.parseInt(mTodoPeriod.getHint().toString());
                        }else {
                            period = Integer.parseInt(mTodoPeriod.getEditableText().toString());
                        }

                        Toast.makeText(mContext,name + period,Toast.LENGTH_SHORT).show();
                        mAddTodoDialog.dismiss();

                        SQLiteDatabase database = mSQLiteHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("name",name);
                        database.insert("Todo",null,values);
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

    public boolean isLegal(){
        if (mTodoName.getEditableText().toString().isEmpty()){
            return false;
        }
        return true;
    }

}
