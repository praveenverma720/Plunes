package com.praveen.plunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PRAVEEN on 31/08/2017.
 */

public class DashboardActivity extends AppCompatActivity {
    ListView listView;
    EditText editText;
    TextView achivement;
    ArrayList<String> strings;
    ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        achivement = (TextView) findViewById(R.id.achivement);
        listView = (ListView) findViewById(R.id.achivementlist);
        editText = (EditText) findViewById(R.id.et1 );
        strings = new ArrayList<>();
        adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);
        ImageButton button = (ImageButton) findViewById(R.id.imbtn);
        button .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here we get the text from the EditText component
                String text = editText.getText().toString();

                // Now add it to the list
                strings.add(text);

                // And finally, update the list
                adapter.notifyDataSetChanged();
            }
        });
        achivement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                startActivity(intent);
            }
        });

    }
}