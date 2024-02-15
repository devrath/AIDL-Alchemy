package com.istudio.server;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.Random;

public class AppServerService extends Service {

    private static final String TAG ="AppServerService" ;

    public AppServerService() { }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    private final ServerInterface.Stub binder = new ServerInterface.Stub() {
        @Override
        public int getInteger() throws RemoteException {
            int randomValue = new Random().nextInt();
            Log.d(TAG,String.valueOf(randomValue));
            return randomValue;
        }
    };
}