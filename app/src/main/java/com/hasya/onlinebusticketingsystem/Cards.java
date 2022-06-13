package com.hasya.onlinebusticketingsystem;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;

public class Cards extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;
    ImageView imageViewCardAuthorization;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        //biometric (fingerprint authentication)
        imageViewCardAuthorization = findViewById(R.id.imageViewFingerprint);

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "No fingerprint sensor detected on this device", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "Sensor is unavailable", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, REQUEST_CODE);
                break;
        }

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(Cards.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                                "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //can put startActivity sini if nk go to other page
                //startActivity(new Intent(MainActivity.this,HomeActivity.class));
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        imageViewCardAuthorization.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });

        //BOTTOM NAVIGATION BAR
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Payment selected
        bottomNavigationView.setSelectedItemId(R.id.payment);

        // Perform item selected listener
        /*bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.booking:
                        startActivity(new Intent(getApplicationContext(),BookingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        }); */

        //when the Back button is clicked
        ImageView back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cards.this,AddPaymentMethod.class));
                //Toast.makeText(PaymentMainpage.this, "This ambulance is ACTIVE!", Toast.LENGTH_SHORT).show();
            }
        });

        //when the Save button is clicked
        Button save = findViewById(R.id.saveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Cards.this,AddPaymentMethod.class));
                Toast.makeText(Cards.this, "Your card credentials is successfully saved", Toast.LENGTH_SHORT).show();
            }
        });

        EditText cdNum = findViewById(R.id.editCardNum);
        EditText expDate = findViewById(R.id.editExpiryDate);
        EditText cvvNum = findViewById(R.id.editCvv);
        DataCard dataCD = new DataCard();

        //to retrieve data from the database
        Button viewBtn = findViewById(R.id.viewBtn);
        viewBtn.setOnClickListener(v->{
            Intent intent = new Intent(Cards.this, ExistingCards.class);
            startActivity(intent);
        });

        //send data into database when save button is clicked
        save.setOnClickListener(v->{
            Card cd = new Card(cdNum.getText().toString(),expDate.getText().toString(),cvvNum.getText().toString());
            dataCD.add(cd).addOnSuccessListener(suc->{
                //if success
                Toast.makeText(this, "Data is successfully inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                //if failure
                Toast.makeText(this, "Data insertion FAILED"+er.getMessage(), Toast.LENGTH_SHORT).show();
            });

            //to update existing data in the database
//            DataCard dataCD = new DataCard();
//            HashMap<String,Object> hashMap = new HashMap<>();
//            hashMap.put("cardNum",cdNum.getText().toString());
//            hashMap.put("expiryDate",expDate.getText().toString());
//            hashMap.put("cvv",cvvNum.getText().toString());
//
//            Card cd = new Card(cdNum.getText().toString(),expDate.getText().toString(),cvvNum.getText().toString());
//            dataCD.update("-N4Ls3fEtf2Gx6rPwJlI",hashMap).addOnSuccessListener(suc->{
//                //if success
//                Toast.makeText(this, "Data is successfully UPDATED", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//                //if failure
//                Toast.makeText(this, "Data update FAILED"+er.getMessage(), Toast.LENGTH_SHORT).show();
//            });

            //to delete existing data from the database
//            Card cd = new Card(cdNum.getText().toString(),expDate.getText().toString(),cvvNum.getText().toString());
//            dataCD.remove("-N4Ls3fEtf2Gx6rPwJlI").addOnSuccessListener(suc->{
//                //if success
//                Toast.makeText(this, "Data is successfully DELETED", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//                //if failure
//                Toast.makeText(this, "Data deletion FAILED"+er.getMessage(), Toast.LENGTH_SHORT).show();
//            });


        });

//        //to delete existing data from DB
//        DataCard dataCD = new DataCard();
//        Button delete = findViewById(R.id.deleteBtn);
//        delete.setOnClickListener(v->{
//            Card cd = new Card(cdNum.getText().toString(),expDate.getText().toString(),cvvNum.getText().toString());
//            dataCD.remove("-N4Ls3fEtf2Gx6rPwJlI").addOnSuccessListener(suc->{
//                //if success
//                Toast.makeText(this, "Data is successfully DELETED", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//                //if failure
//                Toast.makeText(this, "Data deletion FAILED"+er.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//        });
//
//        //to update existing data in the DB
//        Button update = findViewById(R.id.updateBtn);
//        update.setOnClickListener(v->{
//            HashMap<String,Object> hashMap = new HashMap<>();
//            hashMap.put("cardNum",cdNum.getText().toString());
//            hashMap.put("expiryDate",expDate.getText().toString());
//            hashMap.put("cvv",cvvNum.getText().toString());
//
//            Card cd = new Card(cdNum.getText().toString(),expDate.getText().toString(),cvvNum.getText().toString());
//            dataCD.update("-N4Ls3fEtf2Gx6rPwJlI",hashMap).addOnSuccessListener(suc->{
//                //if success
//                Toast.makeText(this, "Data is successfully UPDATED", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//                //if failure
//                Toast.makeText(this, "Data update FAILED"+er.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//        });


    }
}