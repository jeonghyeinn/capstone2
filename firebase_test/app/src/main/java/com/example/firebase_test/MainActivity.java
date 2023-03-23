package com.example.firebase_test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button sendbt;
    private Button bt2;
    public String msg;
    private EditText editText;
    private TextView textView;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendbt = (Button) findViewById(R.id.button);
//        bt2 = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.edittext);
//        listView = (ListView) findViewById(R.id.listview);


        textView = (TextView) findViewById(R.id.textview);

        databaseReference.child("count").child("사진 사람 수").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                textView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("@@@", "Failed to read value.", error.toException());
            }
        });

//        initDatabase();

//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
//        listView.setAdapter(adapter);

//        mReference = mDatabase.getReference("message");
//        mReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                adapter.clear();
//                for(DataSnapshot messageData : snapshot.getChildren()){
//                    String msg2 = messageData.getValue().toString();
//                    Array.add(msg2);
//                    adapter.add(msg2);
//                }
//                adapter.notifyDataSetChanged();
//                listView.setSelection(adapter.getCount()-1);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        sendbt.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                databaseReference.child("message").push().setValue("2");
//            }
//        });

        sendbt.setOnClickListener((v -> {
            msg = editText.getText().toString();
            databaseReference.child("message").push().setValue(msg);
        }));

//        bt2.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                databaseReference.child("과일").child("0").setValue("망고");
//            }
//        });
    }

//    private void initDatabase() {
//
//        mDatabase = FirebaseDatabase.getInstance();
//
//        mReference = mDatabase.getReference("log");
//        mReference.child("log").setValue("check");
//
//        mChild = new ChildEventListener() {
//
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        mReference.addChildEventListener(mChild);
//    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }

}