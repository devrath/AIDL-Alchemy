package com.istudio.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.istudio.server.IAIDLColorInterface;

public class MainActivity extends AppCompatActivity {

    IAIDLColorInterface iADILColorService;
    private static final String TAG ="MainActivity";

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // We take concrete implementation of binder and pass to interface and return interface.
            iADILColorService = IAIDLColorInterface.Stub.asInterface(iBinder);
            // By here the remote service is connected
            Log.d(TAG, "Remote config Service Connected!!");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * ***** Define the Intent
         * Action: Must be the same one defined in the AIDL server
         * Package: It must be the package name of server
         */

        Intent intent = new Intent("AIDLColorService");
        intent.setPackage("com.istudio.server");
        ComponentName componentName = new ComponentName("com.istudio.server", "com.istudio.server.AIDLColorService");
        intent.setComponent(componentName);

        // Bind the service with Intent and service-connection
        bindService(intent,mConnection, BIND_AUTO_CREATE);

        // Create an onclick listener to button
        Log.d(TAG, "bindservice called");
        Button b = findViewById(R.id.button);

        b.setOnClickListener(view -> {
            try {
                // Here the client will get the data from the server
                int color = iADILColorService.getColor();
                view.setBackgroundColor(color);
            } catch (RemoteException e) {
            }
        });


    }
}