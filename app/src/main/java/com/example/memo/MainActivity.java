package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the reference of RelativeLayout
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        // set the layout params for ImageView
        RelativeLayout.LayoutParams btnParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        // create a Button
        final Button myButton = new Button(this);  // create a new Button
        myButton.setId(id);
        id++;
        myButton.setText("Add"); // set Text in the Button
        myButton.setLayoutParams(btnParam); // set defined layout params to Button
        myButton.setTextColor(Color.WHITE); // set white color for the text of Button
        relativeLayout.addView(myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display a toast on Button click
                Toast.makeText(getApplicationContext(), "Button "+myButton.getId()+ " Clicked", Toast.LENGTH_LONG).show();
                addButton();
            }
        });
    }

    public void addButton(){
        // set the layout params for Button
        RelativeLayout.LayoutParams buttonParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        Button myButton = new Button(this);  // create a new Button
        myButton.setText("Add"); // set Text in the Button
        myButton.setLayoutParams(buttonParam); // set defined layout params to Button
        myButton.setTextColor(Color.WHITE); // set white color for the text of Button
        buttonParam.addRule(RelativeLayout.BELOW, id-1); // set Button to the below of ImageView
        myButton.setId(id);
        id++;
        relativeLayout.addView(myButton); // add Button in RelativeLayout
        // perform setOnClickListener event
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display a toast on Button click
                Toast.makeText(getApplicationContext(), "Button "+id+ " Clicked", Toast.LENGTH_LONG).show();
                addButton();
            }
        });
    }
}


