package com.istudio.aidl_app_one;


import android.os.RemoteException;

import com.istudio.mylib.ISimpl;

public class SimpServiceImp extends ISimpl.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }

    @Override
    public int sub(int a, int b) throws RemoteException {
        return a-b;
    }
}
