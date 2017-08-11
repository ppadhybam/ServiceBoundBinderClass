package com.priyabrat.serviceboundbinderclass;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTextNum1)
    EditText editTextNum1;

    @BindView(R.id.editTextNum2)
    EditText editTextNum2;

    @BindView(R.id.textView)
    TextView textView;

    boolean isBound = false;
    MathService mathService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(getApplicationContext(),MathService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(serviceConnection);
        }
    }

    @OnClick(R.id.button)
    public void findAddition(){
        if(!isBound){
            Toast.makeText(mathService, "Activity Not Connected to Service", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            double num1 = Double.parseDouble(editTextNum1.getText().toString());
            double num2 = Double.parseDouble(editTextNum2.getText().toString());
            double result = mathService.getAddition(num1,num2);
            textView.setText(String.valueOf(result));
        }catch (Exception e){
            textView.setText(e.toString());
        }
    }

    @OnClick(R.id.button2)
    public void findSubtraction(){
        if(!isBound){
            Toast.makeText(mathService, "Activity Not Connected to Service", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            double num1 = Double.parseDouble(editTextNum1.getText().toString());
            double num2 = Double.parseDouble(editTextNum2.getText().toString());
            double result = mathService.getSubtraction(num1,num2);
            textView.setText(String.valueOf(result));
        }catch (Exception e){
            textView.setText(e.toString());
        }
    }


    // ServiceConnection
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MathService.LocalBinder localBinder = (MathService.LocalBinder) iBinder;
            mathService = localBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
            mathService =null;
        }
    };
}
