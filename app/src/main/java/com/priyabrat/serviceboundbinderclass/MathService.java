package com.priyabrat.serviceboundbinderclass;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by PriyabratP on 11-08-2017.
 */

public class MathService extends Service {

    LocalBinder localBinder = new LocalBinder();

    class LocalBinder extends Binder {

        MathService getService(){
            return MathService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public double getAddition(double num1, double num2){
        return num1+num2;
    }

    public double getSubtraction(double num1, double num2){
        return num1-num2;
    }
}
