package com.example.rokkhiguard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.opencensus.tags.Tag;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthlistener;
    FirebaseUser firebaseUser;
    public static final int RC_SIGN_II=1;
    ImageView logOutImage;
    String guard_logIn;
    FirebaseFirestore db;

    List<AuthUI.IdpConfig> provider;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        provider=Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build()
        );

        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();


        showSignInOption();

    }

    private void showSignInOption() {

        startActivityIfNeeded(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(provider)
                .setIsSmartLockEnabled(false)
                .build(),RC_SIGN_II
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RC_SIGN_II){
            IdpResponse response=IdpResponse.fromResultIntent(data);

            if (resultCode==RESULT_OK){
                final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                guard_logIn=user.getPhoneNumber();

                saveDataDB();
                //Log.d("Number",user.getPhoneNumber());
                Toast.makeText(MainActivity.this,"Phone"+user.getPhoneNumber(),Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(MainActivity.this,"Phone"+response.getError().getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveDataDB() {
        Map<String, Object> guard_info = new HashMap<>();
        guard_info.put("g_phone",guard_logIn);
        db.collection("Allguards").document(guard_logIn).set(guard_info)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,"Data Uploaded",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,"Error:"+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
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
