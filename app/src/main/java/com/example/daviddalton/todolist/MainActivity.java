package com.example.daviddalton.todolist;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

        final ListView listView = findViewById(R.id.toDoList);
        listView.setAdapter(itemsAdapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                toDoItems.add(editTextItem.getText().toString());
                itemsAdapter.notifyDataSetChanged();
                editTextItem.setText("");
                Toast.makeText(getApplicationContext(),"Item added!", Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Remove Item")
                                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        final String item = (String) parent.getItemAtPosition(position);
                                        view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                toDoItems.remove(item);
                                                itemsAdapter.notifyDataSetChanged();
                                                view.setAlpha(1);
                                            }
                                        });
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //Toast.makeText(getApplicationContext(),"Cancelled ", Toast.LENGTH_LONG).show();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
            }
        });

    }





}
