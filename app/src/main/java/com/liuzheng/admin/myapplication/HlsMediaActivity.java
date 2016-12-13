package com.liuzheng.admin.myapplication;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * MediaPlayer播放Hls流
 */
public class HlsMediaActivity extends Activity implements SurfaceHolder.Callback, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hls_media);
        mSurfaceView = (SurfaceView) findViewById(R.id.mv_play);
        //SurfaceHolder是SurfaceView的控制接口
        mSurfaceHolder = mSurfaceView.getHolder();
        //因为这个类实现了SurfaceHolder.Callback接口，所以回调参数直接this
        mSurfaceHolder.addCallback(this);
    }

    // 播放完成后的操作
    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();//释放内存
        Log.d("HlsMediaActivity", "onCompletion()");
    }

    //当媒体文件准备播放时调用。
    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d("HlsMediaActivity", "onPrepared()");
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //设置显示视频显示在SurfaceView上
        mp.setDisplay(mSurfaceHolder);
        mp.start();
    }

    //在第一次创建面后，该立即调用。
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("HlsMediaActivity", "surfaceCreated()");
        //初始化
        mMediaPlayer = new MediaPlayer();
        String hls = "http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8";
        try {
            //设置数据源
            mMediaPlayer.setDataSource(hls);
            //准备播放器异步播放。
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //注册要在媒体源就绪时调用的回,调用于播放.
        mMediaPlayer.setOnPreparedListener(this);
        //缓存百分比
        mMediaPlayer.setOnBufferingUpdateListener(this);
        //mMediaPlayer快要播放结束时调用
        mMediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("HlsMediaActivity", "surfaceChanged()");
        //这在对表面进行任何结构改变（格式或大小）之后立即被调用。 您应该在此时更新表面中的图像。 此方法至少在{@link #surfaceCreated}后调用一次。
    }

    //mMediaPlayer被销毁之前立即调
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("HlsMediaActivity", "surfaceDestroyed()");
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        mMediaPlayer.release();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.d("HlsMediaActivity", "onBufferingUpdate()");
        //获取缓存百分比
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //因为在mMediaPlayer被销毁之前立即调了surfaceDestroyed
        //所以在onDestroy不需要进行释放内存
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }
}
