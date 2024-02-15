package com.istudio.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.istudio.client.databinding.ActivityMainBinding;
import com.istudio.server.ServerInterface;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG ="MainActivity";
    ServerInterface serviceInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Bind the service
        initiateBindingOfService();
        // Set click listeners
        setClickListeners();
    }


    /**
     * <**************> Service Connection </**************>
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // We take concrete implementation of binder and pass to interface and return interface.
            serviceInterface = ServerInterface.Stub.asInterface(iBinder);
            // By here the remote service is connected
            Log.d(TAG, "Remote service is connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "Remote service is dis-connected");
        }
    };

    /**
     * <**************> Initiate binding of service </**************>
     */
    private void initiateBindingOfService() {
        try {
            // Prepare the intent to be fired to the server to invoke the server functionality
            Intent intent = prepareIntent();
            // Prepare the service connection that is needed to connect client to server
            ServiceConnection serviceConnection = mConnection;
            // Bind the service with Intent and service-connection
            bindService(intent,serviceConnection, BIND_AUTO_CREATE);
            Log.d(TAG, "bind service called");
        }catch (Exception ex){
            Log.d(TAG, ex.getMessage());
        }
    }



    /**
     * <**************> Define the Intent </**************>
     * Action: Must be the same one defined in the AIDL server
     * Package: It must be the package name of server
     */
    private Intent prepareIntent() {
        String intentName = "AppServerServiceIntent";
        String serverPackageName = "com.istudio.server";
        String serviceClassName = "com.istudio.server.AppServerService";

        Intent intent = new Intent(intentName);
        intent.setPackage(serverPackageName);
        ComponentName componentName = new ComponentName(serverPackageName, serviceClassName);
        intent.setComponent(componentName);
        return intent;
    }


    /**
     * <**************> Set On-Click Listeners </**************>
     */
    private void setClickListeners() {
        binding.getIntegerButtonId.setOnClickListener(view -> {
            try {
                // Here the client will get the data from the server
                String result = String.valueOf(serviceInterface.getIntegerData());
                Log.d(TAG,result);
                binding.getIntegerButtonId.setText(result);
            } catch (RemoteException e) {
                Log.d(TAG,e.getMessage());
            }
        });

        binding.getLongButtonId.setOnClickListener(view -> {
            try {
                // Here the client will get the data from the server
                String result = String.valueOf(serviceInterface.getLongData());
                Log.d(TAG,result);
                binding.getLongButtonId.setText(result);
            } catch (RemoteException e) {
                Log.d(TAG,e.getMessage());
            }
        });

        binding.getCharButtonId.setOnClickListener(view -> {
            try {
                // Here the client will get the data from the server
                String result = String.valueOf(serviceInterface.getCharacterData());
                Log.d(TAG,result);
                binding.getCharButtonId.setText(result);
            } catch (RemoteException e) {
                Log.d(TAG,e.getMessage());
            }
        });

        binding.getFloatButtonId.setOnClickListener(view -> {
            try {
                // Here the client will get the data from the server
                String result = String.valueOf(serviceInterface.getFloatData());
                Log.d(TAG,result);
                binding.getFloatButtonId.setText(result);
            } catch (RemoteException e) {
                Log.d(TAG,e.getMessage());
            }
        });

        binding.getDoubleButtonId.setOnClickListener(view -> {
            try {
                // Here the client will get the data from the server
                String result = String.valueOf(serviceInterface.getFloatData());
                Log.d(TAG,result);
                binding.getDoubleButtonId.setText(result);
            } catch (RemoteException e) {
                Log.d(TAG,e.getMessage());
            }
        });

        binding.getStringButtonId.setOnClickListener(view -> {
            try {
                // Here the client will get the data from the server
                String result = String.valueOf(serviceInterface.getStringData());
                Log.d(TAG,result);
                binding.getStringButtonId.setText(result);
            } catch (RemoteException e) {
                Log.d(TAG,e.getMessage());
            }
        });
    }
}