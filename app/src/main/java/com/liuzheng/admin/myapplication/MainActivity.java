package com.liuzheng.admin.myapplication;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 *
 */
public class MainActivity extends Activity {

    private MediaPlayer mPlayer = null;
    private SurfaceView sfv_show;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sfv_show = (SurfaceView) findViewById(R.id.sfv_show);
        //初始化SurfaceHolder类，SurfaceView的控制器
        surfaceHolder = sfv_show.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mPlayer = MediaPlayer.create(MainActivity.this, R.raw.vr4);
                //mMediaPlayer准备播放时调用
                mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        Log.d("MainActivity", "onPrepared()");
                        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        //设置显示视频显示在SurfaceView上
                        mp.setDisplay(surfaceHolder);
                        mp.start();
                    }
                });
                mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {
                        //缓存百分比
                        Log.d("MainActivity", "onBufferingUpdate()");
                    }
                });
                //mMediaPlayer快要播放结束时调用
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Log.d("MainActivity", "onCompletion()");
                        mp.release();
                    }
                });

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d("MainActivity", "surfaceChanged()");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("MainActivity", "surfaceDestroyed()");

                if (mPlayer.isPlaying()) {
                    mPlayer.stop();
                }
                mPlayer.release();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
    }

}
