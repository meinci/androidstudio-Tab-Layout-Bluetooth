package com.info.android_bt_hc05_ver1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    BluetoothSocket btSocket = null;
    TextView textView;
    SeekBar seekBarEff1;
    SeekBar seekBarEff2;
    SeekBar seekBarEff3;
    TextView textViewseekBar1;
    TextView textViewseekBar2;
    TextView textViewseekBar3;
    ImageView imageView_BT_connected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView_BT_connected = findViewById(R.id.imageView_BT_icon);
        textView = findViewById(R.id.Id_textView);
        seekBarEff1 = findViewById(R.id.Id_seekBar_Eff1);
        seekBarEff2 = findViewById(R.id.Id_seekBar_Eff2);
        seekBarEff3 = findViewById(R.id.Id_seekBar_Eff3);
        textViewseekBar1 = findViewById(R.id.Id_textView_seekBar1);
        textViewseekBar2 = findViewById(R.id.Id_textView_seekBar2);
        textViewseekBar3 = findViewById(R.id.Id_textView_seekBar3);
        BTconnect();

        seekBarEff1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewseekBar1.setText("" + i);
                textViewseekBar2.setText("0");
                seekBarEff2.setProgress(0);
                textViewseekBar3.setText("0");
                seekBarEff3.setProgress(0);
                    try {
                        OutputStream outputStream = btSocket.getOutputStream();
                        int Value = (i);
                        String Value_s = String.valueOf(Value);
                        Value_s = "A".concat(Value_s);
                        outputStream.write(Value_s.getBytes(StandardCharsets.UTF_8));
                        textView.setText("Connected");
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("***NOT CONNECTED***");
                        textView.setText("Not connected");
                        BTconnect();
                    }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBarEff2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewseekBar2.setText("" + i);
                textViewseekBar1.setText("0");
                seekBarEff1.setProgress(0);
                textViewseekBar3.setText("0");
                seekBarEff3.setProgress(0);
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    int Value = (i);
                    String Value_s = String.valueOf(Value);
                    Value_s = "B".concat(Value_s);
                    outputStream.write(Value_s.getBytes(StandardCharsets.UTF_8));
                    textView.setText("Connected");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("***NOT CONNECTED***");
                    textView.setText("Not connected");
                    BTconnect();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBarEff3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewseekBar3.setText("" + i);
                textViewseekBar1.setText("0");
                textViewseekBar2.setText("0");
                seekBarEff1.setProgress(0);
                seekBarEff2.setProgress(0);
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    int Value = (i);
                    String Value_s = String.valueOf(Value);
                    Value_s = "C".concat(Value_s);
                    outputStream.write(Value_s.getBytes(StandardCharsets.UTF_8));
                    textView.setText("Connected");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("***NOT CONNECTED***");
                    textView.setText("Not Connected");
                    BTconnect();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @SuppressLint("MissingPermission")
    public void BTconnect() {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice HC05A = btAdapter.getRemoteDevice("98:D3:32:70:E3:82");
        //BluetoothDevice HC05B = btAdapter.getRemoteDevice("98:D3:32:70:E0:FD");
        try {
            btSocket = HC05A.createRfcommSocketToServiceRecord(MY_UUID);
            btSocket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
