package com.example.audioclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Interface.AudioService;

public class MainActivity extends AppCompatActivity {

    private Button startServiceButton, stopServiceButton;
    private Button play1, play2, play3, play4, play5, pauseButton, resumeButton, stopButton;
    private boolean isBound = false;

    private AudioService audioInterface;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder iservice) {
            audioInterface = AudioService.Stub.asInterface(iservice);
            isBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            audioInterface = null;
            isBound = false;
        }
    };//end serviceConnection


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = (Button) findViewById(R.id.startService);
        stopServiceButton = (Button) findViewById(R.id.stopService);
        play1 = (Button) findViewById(R.id.play1);
        play2 = (Button) findViewById(R.id.play2);
        play3 = (Button) findViewById(R.id.play3);
        play4 = (Button) findViewById(R.id.play4);
        play5 = (Button) findViewById(R.id.play5);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        resumeButton = (Button) findViewById(R.id.resumeButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        stopServiceButton.setEnabled(false);
        pauseButton.setEnabled(false);
        resumeButton.setEnabled(false);
        stopButton.setEnabled(false);
        play1.setEnabled(false);
        play2.setEnabled(false);
        play3.setEnabled(false);
        play4.setEnabled(false);
        play5.setEnabled(false);
    }


    //unbind to the service when closing the program
    @Override
    public void onDestroy(){
        super.onDestroy();
        unbindService(this.serviceConnection);
    }//end onDestroy()


    public void startService(View view) {

        if (!isBound) {
            play1.setEnabled(true);
            play2.setEnabled(true);
            play3.setEnabled(true);
            play4.setEnabled(true);
            play5.setEnabled(true);
            startServiceButton.setEnabled(false);
            stopServiceButton.setEnabled(true);

            boolean b = false;
            Intent i = new Intent(AudioService.class.getName());

            // to bind to a service
            // Must make intent explicit or lower target API level to 19.
            ResolveInfo info = getPackageManager().resolveService(i, 0);
            i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));

            b = bindService(i, this.serviceConnection, Context.BIND_AUTO_CREATE);
            if (b) {
                Toast.makeText(this, "Service Started and bound", Toast.LENGTH_SHORT).show();
                isBound = true;
            } else {
                Toast.makeText(this, "Service Not Started", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void stopService(View view){
        play1.setEnabled(false);
        play2.setEnabled(false);
        play3.setEnabled(false);
        play4.setEnabled(false);
        play5.setEnabled(false);
        pauseButton.setEnabled(false);
        resumeButton.setEnabled(false);
        stopButton.setEnabled(false);
        stopServiceButton.setEnabled(false);
        startServiceButton.setEnabled(true);
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(AudioService.class.getName());
        ResolveInfo info = getPackageManager().resolveService(i, 0);
        i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));
        stopService(i);
        isBound = false;
    }

    public void playAudio1(View view) {
        Toast.makeText(this, "Playing Audio: 1", Toast.LENGTH_SHORT).show();
        pauseButton.setEnabled(true);
        play1.setEnabled(false);
        play2.setEnabled(false);
        play3.setEnabled(false);
        play4.setEnabled(false);
        play5.setEnabled(false);

        try{
            audioInterface.playAudio1();
        }
        catch(RemoteException e){
            Log.e("MainActivity", e.toString());
        }
    }

    public void playAudio2(View view) {
        Toast.makeText(this, "Playing Audio: 2", Toast.LENGTH_SHORT).show();
        pauseButton.setEnabled(true);
        play1.setEnabled(false);
        play2.setEnabled(false);
        play3.setEnabled(false);
        play4.setEnabled(false);
        play5.setEnabled(false);

        try{
            audioInterface.playAudio2();
        }
        catch(RemoteException e){
            Log.e("MainActivity", e.toString());
        }
    }

    public void playAudio3(View view) {
        Toast.makeText(this, "Playing Audio: 3", Toast.LENGTH_SHORT).show();
        pauseButton.setEnabled(true);
        play1.setEnabled(false);
        play2.setEnabled(false);
        play3.setEnabled(false);
        play4.setEnabled(false);
        play5.setEnabled(false);

        try{
            audioInterface.playAudio3();
        }
        catch(RemoteException e){
            Log.e("MainActivity", e.toString());
        }
    }

    public void playAudio4(View view) {
        Toast.makeText(this, "Playing Audio: 4", Toast.LENGTH_SHORT).show();
        pauseButton.setEnabled(true);
        play1.setEnabled(false);
        play2.setEnabled(false);
        play3.setEnabled(false);
        play4.setEnabled(false);
        play5.setEnabled(false);

        try{
            audioInterface.playAudio4();
        }
        catch(RemoteException e){
            Log.e("MainActivity", e.toString());
        }
    }

    public void playAudio5(View view) {
        Toast.makeText(this, "Playing Audio: 5", Toast.LENGTH_SHORT).show();
        pauseButton.setEnabled(true);
        play1.setEnabled(false);
        play2.setEnabled(false);
        play3.setEnabled(false);
        play4.setEnabled(false);
        play5.setEnabled(false);

        try{
            audioInterface.playAudio5();
        }
        catch(RemoteException e){
            Log.e("MainActivity", e.toString());
        }
    }



    public void pauseAudio(View view) {
        Toast.makeText(this, "Audio Paused", Toast.LENGTH_SHORT).show();
        resumeButton.setEnabled(true);

            try{
                audioInterface.pauseAudio();
            }
            catch(RemoteException e){
                Log.e("MainActivity", e.toString());
            }
    }

    public void resumeAudio(View view) {
        Toast.makeText(this, "Resume Audio", Toast.LENGTH_SHORT).show();
        stopButton.setEnabled(true);

            try{
                audioInterface.resumeAudio();
            }
            catch(RemoteException e) {
                Log.e("MainActivity", e.toString());
            }
    }

    public void stopAudio(View view) {
        Toast.makeText(this, "Audio Stopped", Toast.LENGTH_SHORT).show();
        pauseButton.setEnabled(false);
        resumeButton.setEnabled(false);
        stopButton.setEnabled(false);
        play1.setEnabled(true);
        play2.setEnabled(true);
        play3.setEnabled(true);
        play4.setEnabled(true);
        play5.setEnabled(true);


            try{
                audioInterface.stopAudio();
            }
            catch(RemoteException e){
                Log.e("MainActivity", e.toString());
            }
    }

}
