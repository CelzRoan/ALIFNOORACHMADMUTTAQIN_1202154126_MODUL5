package com.example.android.alifnoorachmadmuttaqin_1202154126_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

public class ListToDo extends AppCompatActivity {
    Database db;
    RecyclerView rv;
    Adapter adapter;
    ArrayList<AddData> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_do);
        setTitle("To Do List");
        rv = findViewById(R.id.recview);
        datalist = new ArrayList<>();
        db = new Database(this);
        db.readdata(datalist);
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);
        adapter = new Adapter(this,datalist, color);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        swipeToDelete();
    }
    public void swipeToDelete(){
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adapter.getData(position);
                if(direction==ItemTouchHelper.LEFT){
                    if(db.removedata(current.getTodo())){
                        adapter.deleteData(position);
                        Snackbar.make(findViewById(R.id.coor), "Data Deleted", 1000).show();
                    }
                }
            }
        };
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rv);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_settings){
            Intent intent = new Intent(ListToDo.this, Setting.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
    public void add(View view) {
        Intent intent = new Intent(ListToDo.this, AddToDo.class);
        startActivity(intent);
    }
}
