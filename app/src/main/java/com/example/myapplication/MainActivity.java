package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaDrm;
import android.media.UnsupportedSchemeException;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView id, sl;
    MediaDrm mediaDrm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.widewine_id);
        sl = findViewById(R.id.security_level);
        UUID wideVineUuid = new UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L);
        try {
            mediaDrm = new MediaDrm(wideVineUuid);
        } catch (UnsupportedSchemeException e) {
            e.printStackTrace();
        }
    }

    public void setSL3(View view) {
        mediaDrm.setPropertyString("securityLevel", "L3");
        Log.d(TAG, "setSL: set to L3");
        getSLID(view);
    }

    public void setSL1(View view) {
        mediaDrm.setPropertyString("securityLevel", "L1");
        Log.d(TAG, "setSL: set to L1");
        getSLID(view);
    }

    public void getSLID(View view) {
        byte[] wideVineId = mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID);
        String wvid = android.util.Base64.encodeToString(wideVineId, Base64.NO_WRAP);
        String level = mediaDrm.getPropertyString("securityLevel");
        Log.d(TAG, "getSLID: wvid:"+wvid);
        Log.d(TAG, "getSLID: sl: "+level);
        id.setText(wvid);
        sl.setText(level);
    }

}