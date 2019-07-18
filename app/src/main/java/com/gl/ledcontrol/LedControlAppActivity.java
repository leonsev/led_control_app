package com.gl.ledcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;


public class LedControlAppActivity extends AppCompatActivity {
    static final String LOG_TAG = "LedControlAppActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "-> onCreate");

        Button mainButton = findViewById(R.id.buttonStart);
        mainButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "-> onClick(startSrv)");
                startSrv();
            }
        });

        Switch switchLED1  = findViewById(R.id.switchLED1);
        switchLED1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "-> onClick(getButton)");
                if (mService != null) {
                    try {
                        int var = mService.setLedState(0, true);
                        Log.d(LOG_TAG, "-> onClick(getButton) -> getVal() -> " + var);
                    } catch (Exception ex) {
                        Log.d(LOG_TAG, "-> onClick(getButton) -> getVal() > exception");
                    }
                }
            }
        });
    }

    private void startSrv() {

        //Intent intent = new Intent(LedControlAppActivity.this, LedControlAppService.class);
        //intent.setAction(com.gl.ledcontrol.ILedControlApp.class.getName());

        Intent intent = new Intent("com.gl.ledcontrol.LedControlAppService.BIND");
        intent.setPackage("com.gl.ledcontrol");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }


    com.gl.ledcontrol.ILedControlApp mService = null;
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            Log.d(LOG_TAG, "-> onServiceConnected");
            mService = com.gl.ledcontrol.ILedControlApp.Stub.asInterface(service);
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.d(LOG_TAG, "-> onServiceDisconnected");
            mService = null;
        }
    };
}
