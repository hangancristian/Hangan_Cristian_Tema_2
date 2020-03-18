package com.example.hangancristian_tema2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public com.example.hangancristian_tema2.MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase myDataset;

    EditText name;
    EditText mark;
    Button add;
    Button remove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.first_text);
        mark = findViewById(R.id.second_text);
        add = findViewById(R.id.btn1);
        remove = findViewById(R.id.btn2);
        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);

        // set the Layout Manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // set the adapter
        myDataset = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "users-database").allowMainThreadQueries().build();
        List<User> users = myDataset.userDao().getAll();

        mAdapter = new com.example.hangancristian_tema2.MyAdapter(users);
        recyclerView.setAdapter(mAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                User user = new User(name.getText().toString(), Integer.parseInt(mark.getText().toString()));
                myDataset.userDao().insertAll(user);
                mAdapter.setUsers(myDataset.userDao().getAll());
                mAdapter.notifyDataSetChanged();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int no = myDataset.userDao().delete(name.getText().toString());
                if (no != 0) {
                    mAdapter.setUsers(myDataset.userDao().getAll());
                    mAdapter.notifyDataSetChanged();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Nu exista userul cu acest nume!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


    }
}
