package com.kha333n.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ListAdapter extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_adapter);

        listView = findViewById(R.id.listView);

        DatabaseReference dbRefOfItemsList =
                FirebaseDatabase.getInstance().getReferenceFromUrl("https://kha333n-firebase-test-default-rtdb.firebaseio.com/List");


        Query query = dbRefOfItemsList;

        FirebaseListOptions<String> firebaseListOptions = new FirebaseListOptions.Builder<String>().setQuery(query, String.class)
                .setLayout(android.R.layout.simple_list_item_1).build();

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(firebaseListOptions) {
            @Override
            protected void populateView(@NonNull View view, @NonNull String item, int index) {
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setText(item);
            }
        };

        listView.setAdapter(firebaseListAdapter);
    }
}