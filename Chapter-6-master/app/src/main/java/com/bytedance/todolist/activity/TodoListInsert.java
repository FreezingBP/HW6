package com.bytedance.todolist.activity;

import android.os.Bundle;

import com.bytedance.todolist.database.TodoListDao;
import com.bytedance.todolist.database.TodoListDatabase;
import com.bytedance.todolist.database.TodoListEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


import com.bytedance.todolist.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.List;

import com.bytedance.todolist.R;

public class TodoListInsert extends AppCompatActivity{
    private EditText input;
    private Button button;
    private TodoListAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_insert_layout);
        button = (Button) findViewById(R.id.confirm_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                input = (EditText) findViewById(R.id.text);
                new Thread() {
                    @Override
                    public void run() {
                        TodoListDao dao = TodoListDatabase.inst(TodoListInsert.this).todoListDao();
                        //dao.deleteAll();
                        dao.addTodo(new TodoListEntity(input.getText().toString(), new Date(System.currentTimeMillis())));
                    }
                }.start();
            }
        });

    }
}
