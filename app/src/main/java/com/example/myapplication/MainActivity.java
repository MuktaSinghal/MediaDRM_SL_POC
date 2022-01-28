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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (TextView) findViewById(R.id.widewine_id);
        sl = (TextView) findViewById(R.id.security_level);
    }

    public void setSL3(View view) throws UnsupportedSchemeException {
        UUID wideVineUuid = new UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L);
        MediaDrm mediaDrm = null;
        mediaDrm = new MediaDrm(wideVineUuid);
        mediaDrm.setPropertyString("securityLevel", "L3");
        Log.d(TAG, "setSL: set to L3");
    }

    public void setSL1(View view) throws UnsupportedSchemeException {
        UUID wideVineUuid = new UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L);
        MediaDrm mediaDrm = null;
        mediaDrm = new MediaDrm(wideVineUuid);
        mediaDrm.setPropertyString("securityLevel", "L1");
        Log.d(TAG, "setSL: set to L1");
    }

    public void getSLID(View view) throws UnsupportedSchemeException {
        UUID wideVineUuid = new UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L);
        MediaDrm mediaDrm = new MediaDrm(wideVineUuid);
        byte[] wideVineId = mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID);
        String wvid = android.util.Base64.encodeToString(wideVineId, Base64.NO_WRAP);
        String level = mediaDrm.getPropertyString("securityLevel");
        Log.d(TAG, "getSLID: wvid:"+wvid);
        Log.d(TAG, "getSLID: sl: "+level);
        id.setText(wvid);
        sl.setText(level);
    }
}