package com.example.login.Menu.Grade;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;

import java.awt.font.TextAttribute;
import android.view.WindowManager;
import android.view.Display;
import android.graphics.Point;
public class Activity_Grade extends AppCompatActivity {
    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;
    LinearLayout table;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        table = (LinearLayout) findViewById(R.id.table);
        tableLayout = new TableLayout(this);
        displayTable();

    }

    private void getScreenDimension() {
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SCREEN_WIDTH = size.x;
        SCREEN_HEIGHT = size.y;
    }

    public void displayTable() {
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));
        for (int i = 0 ; i < 3 ; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            TextView view = new TextView(this);
            TextView view1 = new TextView(this);
            TextView view2 = new TextView(this);
            TextView view3 = new TextView(this);
            view.setText("A :"+ i);
            view1.setText("B :"+ i);
            view2.setText("C :"+ i);
            view3.setText("D"+i);
            view.setBackgroundResource(R.drawable.border_table);
            view1.setBackgroundResource(R.drawable.border_table);
            view2.setBackgroundResource(R.drawable.border_table);
            view3.setBackgroundResource(R.drawable.border_table);
            view.setWidth(270);
            view1.setWidth(270);
            view2.setWidth(270);
            view3.setWidth(270);
            //view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            //view1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));

            tableRow.addView(view);
            tableRow.addView(view1);
            tableRow.addView(view2);
            tableRow.addView(view3);
            tableLayout.addView(tableRow);
        }
        table.addView(tableLayout);

    }
}
