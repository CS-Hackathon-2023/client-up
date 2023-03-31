package com.example.medicalhackthonjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.myRequest:
                    Intent i = new Intent(getApplicationContext(),RequestsActivity.class);
                    startActivity(i);
                    return true;
                case R.id.settings:
                    Intent j = new Intent(getApplicationContext(),SettingsActivity.class);
                    startActivity(j);
                    return true;
            }
            return false;
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance(); // Create db link
        // Create a new request with all parameters
        Map<String, Object> request = new HashMap<>();
        request.put("ward", "madHouse"); // "const"
        request.put("requestId", "i need to check how to add id"); // "const"

        request.put("room", 13); // "const"
        request.put("status", "In progress"); // const
        request.put("userId", "id"); // "const"

        Button call_nurse_button = (Button) findViewById(R.id.call_nurse_button);

        call_nurse_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                request.put("requestDetail", "-"); // irrelevant for now
                request.put("requestType", "callNurse");
                addToDB(db, request);
            }
        });

        Button call_wheel_help_button = (Button) findViewById(R.id.call_wheel_help_button);
        call_wheel_help_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                request.put("requestDetail", "-"); // irrelevant for now
                request.put("requestType", "wheelHelp");
                addToDB(db, request);
            }
        });

        Button ask_medicine_button = (Button) findViewById(R.id.ask_medicine_button);
        ask_medicine_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                request.put("requestDetail", "-"); // irrelevant for now
                request.put("requestType", "askMedicine");
                addToDB(db, request);
            }
        });

        Button call_logistics = (Button) findViewById(R.id.call_logistics);
        call_logistics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                request.put("requestDetail", "-"); // irrelevant for now
                request.put("requestType", "callLogistics");
                addToDB(db, request);
            }
        });

        Button open_chat_button = (Button) findViewById(R.id.open_chat_button);
        open_chat_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                request.put("requestDetail", "-"); // irrelevant for now
                request.put("requestType", "openChat");
                addToDB(db, request);
            }
        });

        Button call_sos_button = (Button) findViewById(R.id.call_sos_button);
        call_sos_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                request.put("requestDetail", "-"); // irrelevant for now
                request.put("requestType", "callSos");
                addToDB(db, request);
            }
        });

    }

    void addToDB(FirebaseFirestore db, Map<String, Object> request) {
        // Add a new document with a generated ID
        db.collection("Requests")
                .add(request)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Toast.makeText(MainActivity.this, "DocumentSnapshot added with ID:" + documentReference.getId(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "הבקשה נשלחה לצוות", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "שגיאה. הבקשה לא נשלחה", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}