package com.example.daviddalton.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


//Think about using Room for a local database
public class MainActivity extends AppCompatActivity {

    EditText editTextItem;
    ArrayList<String> toDoItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextItem = (EditText) findViewById(R.id.editText);

        final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toDoItems);

        ListView listView = findViewById(R.id.toDoList);
        listView.setAdapter(itemsAdapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                toDoItems.add(editTextItem.getText().toString());
                itemsAdapter.notifyDataSetChanged();
            }
        });

    }





}
