package com.istudio.clientapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.istudio.clientapp.databinding.ActivityMainBinding;
import com.istudio.mylib.ISimpl;


public class MainActivity extends AppCompatActivity implements ServiceConnection {


    private ActivityMainBinding binding;


    private ISimpl service;

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        service = ISimpl.Stub.asInterface(iBinder);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn1.setOnClickListener(view -> {
            try {
                Log.d("POKEMON" , "res:" + service.add(10, 20));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

    }

    protected void onStart() {
        super.onStart();

        Intent intent = new Intent("com.istudio.aidl_app_one.MySimpService");
        intent.setPackage("com.istudio.aidl_app_one");
        bindService(intent, this, BIND_AUTO_CREATE);

    }
}
