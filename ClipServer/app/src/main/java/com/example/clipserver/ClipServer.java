package com.example.clipserver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;

import com.example.Interface.AudioService;

public class ClipServer extends Service {

    private static final int NOTIFICATION_ID = 1;
    public MediaPlayer player;
    private Notification notification;
    private static String CHANNEL_ID = "Music player style";

    private final AudioService.Stub mBinder = new AudioService.Stub() {

        public void playAudio1() {
            play1();
        }

        public void playAudio2() {
            play2();
        }

        public void playAudio3() {
            play3();
        }

        public void playAudio4() {
            play4();
        }

        public void playAudio5() {
            play5();
        }

        public void pauseAudio() {
            pause();
        }

        public void resumeAudio() {
            resume();
        }

        public void stopAudio() {
            stop();
        }
    };

    @Override
    public void onCreate(){
        super.onCreate();

        this.createNotificationChannel();
        // Create a notification area notification so the user
        // can get back to the AudioClient
        final Intent notificationIntent = new Intent();
        notificationIntent.setComponent(new ComponentName("com.example.audioclient", "com.example.audioclient.MainActivity"));
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true).setContentTitle("Music Playing")
                .setContentText("Click to Access Audio Client")
                .setTicker("Music is playing!")
                .setFullScreenIntent(pendingIntent, false)
                .build();

        // Put this Service in a foreground state, so it won't
        // readily be killed by the system
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public void onDestroy(){
        if(player != null){
            player.stop();
            player.release();
        }
        stopSelf();
    }

    // UB 11-12-2018:  Now Oreo wants communication channels...
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Music player notification";
            String description = "The channel for music player notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void play1() {
        player = MediaPlayer.create(this, R.raw.office);
        player.start();

        if(player != null) {
            player.setLooping(false);

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                }
            });
        }
    }

    private void play2() {
        player = MediaPlayer.create(this, R.raw.anewbeginning);
        player.start();

        if(player != null) {
            player.setLooping(false);

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                }
            });
        }
    }

    private void play3() {
        player = MediaPlayer.create(this, R.raw.jazzyfrenchy);
        player.start();

        if(player != null) {
            player.setLooping(false);

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                }
            });
        }
    }

    private void play4() {
        player = MediaPlayer.create(this, R.raw.littleidea);
        player.start();

        if(player != null) {
            player.setLooping(false);

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                }
            });
        }
    }

    private void play5() {
        player = MediaPlayer.create(this, R.raw.ukulele);
        player.start();

        if(player != null) {
            player.setLooping(false);

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                }
            });
        }
    }



    private void pause(){
        if(player.isPlaying())
            player.pause();
        else
            Toast.makeText(this, "No song currently playing", Toast.LENGTH_SHORT).show();
    }

    private void resume(){
        if(player.isPlaying())
            Toast.makeText(this, "Can't resume a song that's already playing", Toast.LENGTH_SHORT).show();
        else
            player.start();
    }

    private void stop(){
        player.stop();
        player.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {

        // Don't automatically restart this Service if it is killed
        return START_NOT_STICKY;
    }

}
