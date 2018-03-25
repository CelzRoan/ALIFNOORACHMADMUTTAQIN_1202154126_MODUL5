package com.example.android.alifnoorachmadmuttaqin_1202154126_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDo extends AppCompatActivity {
    EditText ToDo, Description, Priority;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        setTitle("Add To Do");
        ToDo = findViewById(R.id.editTodo);
        Description = findViewById(R.id.editDesc);
        Priority = findViewById(R.id.editPriority);
        db = new Database(this);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddToDo.this, ListToDo.class);
        startActivity(intent);
        this.finish();
    }
    public void tambah(View view) {
        if (db.inputdata(new AddData(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            Toast.makeText(this, "To Do List added!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddToDo.this, ListToDo.class));
            this.finish();
        }else {
            Toast.makeText(this, "Cannot add the list", Toast.LENGTH_SHORT).show();
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }
}