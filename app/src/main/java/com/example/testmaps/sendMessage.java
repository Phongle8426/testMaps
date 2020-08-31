package com.example.testmaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class sendMessage extends AppCompatActivity {

   private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    Button senmess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        senmess = (Button)findViewById(R.id.btn_send);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

        senmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS("0363159770","Message này dã được gửi đi đến Aron");
            }
        });

    }
//    @Override
//    public void onRequestPermissionResult(int requestCode, String permissions[],int [] grantResults){
//            switch (requestCode) {
//                case MY_PERMISSIONS_REQUEST_SEND_SMS:
//                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(this, "Thanks for permitting", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(this, "Hic Hic :(((", Toast.LENGTH_SHORT).show();
//                    }
//            }
//    }

    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}