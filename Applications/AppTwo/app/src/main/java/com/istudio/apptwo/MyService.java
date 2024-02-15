package com.istudio.apptwo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.istudio.apptwo.MyInterface;

public class MyService extends Service {

    private final MyInterface.Stub mBinder = new MyInterface.Stub() {
        @Override
        public void sendData(String data) throws RemoteException {
            // Handle incoming data from the client
            // Process the data as needed
        }

        @Override
        public String receiveData() throws RemoteException {
            // Return data to the client
            return "Hello from the service!";
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
