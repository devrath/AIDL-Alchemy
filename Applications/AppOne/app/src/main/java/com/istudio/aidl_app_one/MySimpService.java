package com.istudio.aidl_app_one;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MySimpService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new SimpServiceImp();
    }

}

