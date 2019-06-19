package com.example.rokkhiguard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class GuardNumberInput extends AppCompatActivity {

    private Button verifyNumberButton;
    private EditText guard_phone_number;
    ProgressBar progressBar;
    String guard_number,number;
    //private static final String TAG = "GuardNumberInput";

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_number_input);

        db=FirebaseFirestore.getInstance();

        verifyNumberButton=findViewById(R.id.verify_phone_button);
        guard_phone_number=findViewById(R.id.guard_phone_number_editText);
        progressBar=findViewById(R.id.progressBar);

        verifyNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkGuardNumber();
            }
        });


    }

    private void checkGuardNumber() {

        progressBar.setVisibility(View.VISIBLE);
        guard_number=guard_phone_number.getText().toString();

        DocumentReference docRef = db.collection("allguards").document("+8801521431798");
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()){

                             number=documentSnapshot.getString("g_phone");

                             if (guard_number.equals(number)){
                                 progressBar.setVisibility(View.INVISIBLE);
                                 gotoMain();
                             }else {
                                 progressBar.setVisibility(View.INVISIBLE);
                                 showCustomDialogue();
                             }


                        }else {

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(GuardNumberInput.this,"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void gotoMain() {
        Intent intent=new Intent(GuardNumberInput.this,MainActivity.class);
        startActivity(intent);
    }


    private void showCustomDialogue() {
        LayoutInflater inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.custom_alert,null);

        Button cancel=view.findViewById(R.id.cancel_button);



        final AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setView(view)
                .create();

        alertDialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
