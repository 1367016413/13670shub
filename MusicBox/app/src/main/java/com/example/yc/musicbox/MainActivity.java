package com.example.yc.musicbox;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity {
    private MusicService musicService;
    private ImageView ImageView;
    private Button button1;
    private Button button2;
    private Button button3;
    private SeekBar seekBar;
    private TextView progressTime;
    private TextView totalTime;
    private TextView state;
    private ObjectAnimator objectAnimator;//图片的动画效果
    private SimpleDateFormat t = new SimpleDateFormat("mm:ss");//时间格式
    private Handler handler = new Handler();
    private IBinder mBinder;
    private ServiceConnection mConnection;
    //针对sd卡读取权限申请
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service_connection();
        verifyStoragePermissions(MainActivity.this);
        findView();
        initView();
        myClick();
        update();
    }

    //将activity与service绑定
    public void service_connection() {
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mBinder = service;
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                mConnection = null;
            }
        };
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }




    public static void verifyStoragePermissions(Activity activity) {
        // sd卡读取权限
        try {
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.READ_EXTERNAL_STORAGE");

            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.obtainMessage(123).sendToTarget();
                }
            }
        }).start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 123:
                        progressTime.setText(t.format(MusicService.mp.getCurrentPosition()));
                        seekBar.setProgress(MusicService.mp.getCurrentPosition());
                        break;
                    default:
                        break;
                }
            }
        };
    }


    public void findView() {
        ImageView = (ImageView) findViewById(R.id.img);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        progressTime = (TextView) findViewById(R.id.progressTime);
        totalTime = (TextView) findViewById(R.id.totalTime);
        state = (TextView) findViewById(R.id.state);
        objectAnimator = ObjectAnimator.ofFloat(ImageView, "rotation", 0f, 360f);
        objectAnimator.setDuration(10000);//设置动画时长,10000表示慢慢旋转
        objectAnimator.setInterpolator(new LinearInterpolator());//匀速
        objectAnimator.setRepeatCount(-1);//不停止
        seekBar.setEnabled(true);
        seekBar.setProgress(0);
        seekBar.setMax(250000);
    }

    public void initView() {
        button1.setTag(0);
        state.setVisibility(View.GONE);
    }

    public void myClick() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (Integer) button1.getTag();
                if (tag == 0) {
                    state.setVisibility(View.VISIBLE);
                    button1.setTag(1);
                    button1.setText("PAUSED");
                    state.setText("Playing");
                    objectAnimator.start();
                    try {
                        int code = 1;
                        Parcel data = Parcel.obtain();
                        Parcel reply = Parcel.obtain();
                        mBinder.transact(code, data, reply, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (tag == 1) {
                    button1.setTag(2);
                    button1.setText("PLAY");
                    objectAnimator.pause();
                    try {
                        int code = 2;
                        Parcel data = Parcel.obtain();
                        Parcel reply = Parcel.obtain();
                        mBinder.transact(code, data, reply, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    button1.setTag(1);
                    button1.setText("PAUSED");
                    objectAnimator.resume();
                    try {
                        int code = 3;
                        Parcel data = Parcel.obtain();
                        Parcel reply = Parcel.obtain();
                        mBinder.transact(code, data, reply, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setTag(0);
                button1.setText("PLAY");
                objectAnimator.end();
                state.setVisibility(View.VISIBLE);
                state.setText("Stopped");
                try {
                    int code = 4;
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    mBinder.transact(code, data, reply, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    MusicService.mp.seekTo(seekBar.getProgress());
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //同意权限
        } else {
            System.exit(0);
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);//后台运行
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
        mConnection = null;
        try {
            MainActivity.this.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void last(View view) {
        try {
            int code = 5;
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            mBinder.transact(code, data, reply, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void next(View view) {
        try {
            int code = 6;
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            mBinder.transact(code, data, reply, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void random(View view) {
        try {
            int code = 8;
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            mBinder.transact(code, data, reply, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
