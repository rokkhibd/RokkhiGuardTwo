package com.example.rokkhiguard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Context context;
    RelativeLayout mrootview;
    private static final String TAG = "MainActivity";
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ImageButton settings;
    ImageView edit;
    CircleImageView propic, visitor, invite, notice, parcel, family, vehicle, service, bill, complain;
    TextView username, userlogintext;
    SharedPreferences sharedPref;
    String userid, utoken = "";
    SharedPreferences.Editor editor;
    FrameLayout frameLayout;
    AppBarLayout appBarLayout;
    RelativeLayout relpro;
    ConstraintLayout constraintLayout;
    // Normalfunc normalfunc;
    private static final int RC_SIGN_IN = 12773;
    AuthUI.IdpConfig phoneConfigWithDefaultNumber;
    String from = "";
    String officemail;
    //FirebaseFunctions firebaseFunctions;


    //for spalsh job
    ImageView logo;
    ProgressBar pro;
    RelativeLayout splash, mainview;


    //for request
    Button send, logout;
    EditText rname;
    String flatid = "", buildid = "", commid = "", famid = "", flatno = "";
    Date joindate = new Date();
    boolean status;

    Button cworkplace;

    RelativeLayout main,splash1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainPage.this;
        firebaseFirestore = FirebaseFirestore.getInstance();


        mAuth = FirebaseAuth.getInstance();
        cworkplace=findViewById(R.id.current_workplace_btn);
        main= findViewById(R.id.main);
        splash1= findViewById(R.id.splash);


        //signOut();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser == null) {
                    gologout();


                } else {

                    firebaseFirestore.collection("Allguard").document(firebaseUser.getPhoneNumber()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot documentSnapshot = task.getResult();
                                if (documentSnapshot != null && documentSnapshot.exists()) {
                                    String gphone = documentSnapshot.getString("g_phone");
                                    Toast.makeText(context, "Kaj hoise", Toast.LENGTH_SHORT).show();
///loaduserdata();

                                }
                                else if(!documentSnapshot.exists()){
                                    //show dialog
                                    splash1.setVisibility(View.VISIBLE);
                                    main.setVisibility(View.GONE);

                                }
                            }
                        }
                    });


                }
            }
        };

        cworkplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
            }
        });

    }


//    private void loaduserdata() {
//        final String ulogin = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
//
//        userlogintext.setText(ulogin);
//
//        //TODO
//
//
//        new CountDownTimer(700, 100) {
//
//            public void onTick(long millisUntilFinished) {
//            }
//
//            public void onFinish() {
//                pro.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
//            }
//        }.start();
//
//
//        firebaseFirestore.collection(getString(R.string.col_users)).document(userid)
//                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//
//                    splash.setVisibility(View.GONE);
//                    mainview.setVisibility(View.VISIBLE);
//
//                    Log.d(TAG, "onComplete:  srr1");
//                    DocumentSnapshot documentSnapshot = task.getResult();
//                    if (documentSnapshot != null && documentSnapshot.exists()) {
//
//                        Log.d(TAG, "onComplete:  srrw");
//                        Users users = documentSnapshot.toObject(Users.class);
//                        username.setText(users.getName());
//                        UniversalImageLoader.setImage(users.getPic(), propic, null, "");
//
//
//                        final String usertoken = users.getToken();
//
//                        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener<InstanceIdResult>() {
//                            @Override
//                            public void onSuccess(InstanceIdResult instanceIdResult) {
//                                utoken = instanceIdResult.getToken();
//                                editor.putString("token", utoken);
//                                editor.apply();
//                                Log.d(TAG, "onComplete:  yyy8");
//
//                                // Log.d(TAG, "onSuccess: tokenxx "+ useertoken +"xx"+ utoken);
//
//
//                                if (usertoken != null && !utoken.equals(usertoken)) {
//
//                                    normalfunc.updateToken(utoken, userid);
//
//                                }
//
//
//                            }
//                        });
//
//                    } else if (!documentSnapshot.exists()) {
//                        Log.d(TAG, "onComplete:  srr2");
//
//                        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener<InstanceIdResult>() {
//                            @Override
//                            public void onSuccess(InstanceIdResult instanceIdResult) {
//                                utoken = instanceIdResult.getToken();
//                                editor.putString("token", utoken);
//                                editor.apply();
//                                Log.d(TAG, "onComplete:  yyy8");
//                                //normalfunc.test();
//
//                                normalfunc.addUser(utoken, ulogin, userid);
//
//
//                            }
//                        });
//                        //d
//                    }
//                } else Log.d(TAG, "onComplete:  srr4");
//            }
//        });
//
//    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void gologout() {
        List<String> whitelistedCountries = new ArrayList<String>();
        whitelistedCountries.add("bd");
        phoneConfigWithDefaultNumber = new AuthUI.IdpConfig.PhoneBuilder()
                .setDefaultCountryIso("bd")
                .setWhitelistedCountries(whitelistedCountries)
                .build();

        signInPhone(mrootview);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            handleSignInResponse(resultCode, data);
        }
    }

    private void handleSignInResponse(int resultCode, Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (resultCode == RESULT_OK) {
            Log.d(TAG, "handleSignInResponse: checkhere ");
            Log.d(TAG, "handleSignInResponse: jjj " + FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
            //loaduserdata();

        } else {
            if (response == null) {
                showSnackbar(R.string.sign_in_cancelled);
                return;
            }
            if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                showSnackbar(R.string.no_internet_connection);
                return;
            }
            if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                showSnackbar(R.string.unknown_error);
                return;
            }
        }

    }


    public void signInPhone(View view) {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList(phoneConfigWithDefaultNumber))
                        .build(),
                RC_SIGN_IN);

    }

    private void showSnackbar(int errorMessageRes) {
        Snackbar.make(mrootview, errorMessageRes, Snackbar.LENGTH_LONG).show();
    }


}
