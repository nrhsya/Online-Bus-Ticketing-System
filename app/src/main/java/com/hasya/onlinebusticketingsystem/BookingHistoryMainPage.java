package com.hasya.onlinebusticketingsystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.Executor;

public class BookingHistoryMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history_main_page);

        //BOTTOM NAVIGATION BAR
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Payment selected
        bottomNavigationView.setSelectedItemId(R.id.bookingHistory);

        TextView msg_text = findViewById(R.id.fingerprint_description);
        Button btn_login = findViewById(R.id.btn_fingerprint);

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_SUCCESS:
                msg_text.setText("You can use the fingerprint sensor to view the History");
                msg_text.setTextColor(Color.parseColor("#FF000000"));
                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                msg_text.setText("The device don't have a fingerprint sensor");
                btn_login.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                msg_text.setText("The biometric sensor is currently unavailable");
                btn_login.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                msg_text.setText("Your device don't have fingerprint saved,please check your security settings");
                btn_login.setVisibility(View.GONE);
                break;

        }

        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(BookingHistoryMainPage.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                                "Booking History error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(BookingHistoryMainPage.this,BookingHistoryView.class));
                Toast.makeText(getApplicationContext(),
                        "Successfully View Your Booking History", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric verification for Booking History")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                biometricPrompt.authenticate(promptInfo);
            }
        });

    }
}