package com.torens.z.chat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("messages");
    EditText et;
    Button sent;
    static int max_l;
    ArrayList<String> messages = new ArrayList<>();
    RecyclerView msgRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sendButton
        sent=findViewById(R.id.button);
        et=findViewById(R.id.editText);
        msgRecycler=findViewById(R.id.rv);

        msgRecycler.setLayoutManager(new LinearLayoutManager(this));

        final DataAdapter dataAdapter = new DataAdapter(this, messages);

        msgRecycler.setAdapter(dataAdapter);

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mes = et.getText().toString();

                if (mes.equals("")){
                    Toast.makeText(getApplicationContext(),  "Введите сообщение!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mes.length() >= max_l){
                    Toast.makeText(getApplicationContext(),  "Слишком длиное сообщение!", Toast.LENGTH_SHORT).show();
                    return;
                }

                myRef.push().setValue(mes);
                et.setText("");

                ChildEventListener childEventListener = myRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        String msg = dataSnapshot.getValue(String.class);
                        messages.add(msg);
                        dataAdapter.notifyDataSetChanged();
                        msgRecycler.smoothScrollToPosition(messages.size());
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
