package com.istudio.server;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.security.SecureRandom;
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
        public int getIntegerData() {
            int randomValue = new Random().nextInt();
            Log.d(TAG,String.valueOf(randomValue));
            return randomValue;
        }

        @Override
        public long getLongData() {
            long randomValue = new Random().nextLong();
            Log.d(TAG,String.valueOf(randomValue));
            return randomValue;
        }

        @Override
        public char getCharacterData() {
            Random random = new Random();
            // Generating a random char between 'a' and 'z' (inclusive)
            char randomChar = (char) (random.nextInt(26) + 'a');
            return randomChar;
        }

        @Override
        public float getFloatData() {
            float randomValue = new Random().nextFloat();
            Log.d(TAG,String.valueOf(randomValue));
            return randomValue;
        }

        @Override
        public double getDoubleData() {
            double randomValue = new Random().nextDouble();
            Log.d(TAG,String.valueOf(randomValue));
            return randomValue;
        }

        @Override
        public String getStringData() {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            SecureRandom random = new SecureRandom();
            int length = 10;
            StringBuilder sb = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(characters.length());
                char randomChar = characters.charAt(randomIndex);
                sb.append(randomChar);
            }

            return sb.toString();
        }
    };
}