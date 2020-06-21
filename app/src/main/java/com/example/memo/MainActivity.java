package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ItemClickListener {

    RelativeLayout relativeLayout;
    EditText editText1;
    int id = 1;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the reference of RelativeLayout
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerAdapter(this, animalNames);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        //addButton();
    }

    public void addButton(){
        // set the layout params for Button
                RelativeLayout.LayoutParams buttonParam = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                Button myButton = new Button(this);  // create a new Button
                myButton.setPadding(0,50,0,0);
                myButton.setId(id);
                id++;
                Drawable img = getResources().getDrawable(R.drawable.add_button);
                myButton.setCompoundDrawablesWithIntrinsicBounds(null, img,  null, null);
                //myButton.setText("Add"); // set Text in the Button
                myButton.setLayoutParams(buttonParam); // set defined layout params to Button
                buttonParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
                if(id>1) {
                    buttonParam.addRule(RelativeLayout.BELOW, id - 2); // set Button to the below of ImageView
                }
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

    public void Save(String fileName) {
        try {
            OutputStreamWriter saveData =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            saveData.write(editText1.getText().toString());
            saveData.close();
            Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
        }
        catch (Throwable t) {
            Toast.makeText(this, "Could not save file, Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean CheckFile(String fileName){
        File file = getBaseContext().getFileStreamPath(fileName);
        return file.exists();
    }

    public String Load(String fileName) {
        String fileContent = "";
        if (CheckFile(fileName)) {
            try {
                InputStream loadData = openFileInput(fileName);
                if ( loadData != null) {
                    InputStreamReader readData = new InputStreamReader( loadData );
                    BufferedReader buffer = new BufferedReader(readData);
                    String tempStr;
                    StringBuilder string = new StringBuilder();
                    while ((tempStr = buffer.readLine()) != null) {
                        string.append(tempStr + "\n");
                    }
                    loadData .close();
                    fileContent = string.toString();
                }
            }
            catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                Toast.makeText(this, "Could not load file, Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return fileContent;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}


