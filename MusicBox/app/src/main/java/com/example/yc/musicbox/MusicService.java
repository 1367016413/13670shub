package com.example.yc.musicbox;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.IOException;
import java.util.Random;

public class MusicService extends Service {
    private String[] musicDir = new String[]{"/sdcard/Music/狮子山下.mp3", "/sdcard/Music/狮子山下.mp3", "/sdcard/Music/Cyka Blyat.mp3", "/sdcard/Music/See It Again.mp3", "/sdcard/Music/Beggin'.mp3"};
    private int musicIndex = 1;
    public static MediaPlayer mp = new MediaPlayer();

    public MusicService() {
        try {
            mp.setDataSource("/sdcard/Music/狮子山下.mp3");
            mp.prepare();
            mp.setLooping(true);
            musicIndex = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private IBinder mBinder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // 必须实现的接口
        return mBinder;
    }

    public class MyBinder extends Binder {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    mp.start();
                    break;
                case 2:
                    mp.pause();
                    break;
                case 3:
                    mp.start();
                    break;
                case 4:
                    mp.stop();
                    try {
                        mp.prepare();
                        mp.seekTo(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    if (mp != null && musicIndex > 0) {
                        mp.stop();
                        try {
                            mp.reset();
                            mp.setDataSource(musicDir[musicIndex - 1]);
                            musicIndex--;
                            mp.prepare();
                            mp.seekTo(0);
                            mp.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 6:
                    if (mp != null && musicIndex < 4) {
                        mp.stop();
                        try {
                            mp.reset();
                            mp.setDataSource(musicDir[musicIndex + 1]);
                            musicIndex++;
                            mp.prepare();
                            mp.seekTo(0);
                            mp.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 8:
                    if (mp != null && musicIndex < 4) {
                        mp.stop();
                        Random random = new Random();
                        int num = random.nextInt(4);
                        try {
                            mp.reset();
                            mp.setDataSource(musicDir[musicIndex + num]);
                            mp.prepare();
                            mp.seekTo(0);
                            mp.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                    break;
            }
            return false;
        }
    }
}









