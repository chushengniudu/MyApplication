package com.liuzheng.admin.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * VideoView  播放视频
 */
public class VideoViewActivity extends Activity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String strPath = "http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8";
        Uri uri = Uri.parse(strPath);
        videoView = (VideoView) findViewById(R.id.vidoView);
        videoView.setVideoURI(uri);   // mvdView是一个videoView控件
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }
}
