package com.example.android.alifnoorachmadmuttaqin_1202154126_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    TextView shapecolor;
    int colorid;
    AlertDialog.Builder alert;
    SharedPreferences.Editor sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");
        alert = new AlertDialog.Builder(this);
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        sp = sharedP.edit();
        colorid = sharedP.getInt("Colourground", R.color.white);
        shapecolor = findViewById(R.id.shapecolor);
        shapecolor.setText(getShapeColor(colorid));
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Setting.this, ListToDo.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.onBackPressed();
        }
        return true;
    }
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Merah";
        }else if (i==R.color.green){
            return "Hijau";
        }else if (i==R.color.blue){
            return "Biru";
        }else if (i==R.color.purple){
            return "Ungu";
        }else if (i==R.color.deep_orange){
            return "Orange";
        }else{
            return "Default";
        }
    }
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else if (i==R.color.purple){
            return R.id.purple;
        }else if (i==R.color.deep_orange){
            return R.id.orange;
        }else{
            return R.id.white;
        }
    }
    public void choosecolor(View view) {
        alert.setTitle("Shape Color");
        View view1 = getLayoutInflater().inflate(R.layout.colorsettings, null);
        alert.setView(view1);
        final RadioGroup radG = view1.findViewById(R.id.radiocolor);
        radG.check(getColorid(colorid));
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        colorid = R.color.red;
                        break;
                    case R.id.green:
                        colorid = R.color.green;
                        break;
                    case R.id.blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.purple:
                        colorid = R.color.purple;
                        break;
                    case R.id.orange:
                        colorid = R.color.deep_orange;
                        break;
                    case R.id.white:
                        colorid = R.color.white;
                        break;
                }
                shapecolor.setText(getShapeColor(colorid));
                sp.putInt("Colourground", colorid);
                sp.commit();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.create().show();
    }
}