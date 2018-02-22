package com.example.administrator.playaudiotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 按钮较多的话，让MainActivity实现View.OnClickListener接口更方便
         */
        Button play = findViewById(R.id.play);
        Button pause = findViewById(R.id.pause);
        Button stop = findViewById(R.id.stop);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        /*申请WRITE_EXTERNAL_STORAGE权限*/
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        else {
            initMediaPlayr(); //初始化MediaPlay
        }

        /**
         * 按钮较多的话，让MainActivity实现View.OnClickListener接口更方便
         */
        Button play_v = findViewById(R.id.play_v);
        Button pause_v = findViewById(R.id.pause_v);
        Button stop_v = findViewById(R.id.stop_v);
        videoView = findViewById(R.id.video_view);

        play_v.setOnClickListener(this);
        pause_v.setOnClickListener(this);
        stop_v.setOnClickListener(this);

    }

    private void initMediaPlayr() {

        //music.mp3在SD卡的根目录下（已验证）
        File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
        try {
            mediaPlayer.setDataSource(file.getPath()); //指定音频文件路径
            mediaPlayer.prepare(); //进入准备状态
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
        videoView.setVideoPath(file.getPath());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                //申请到了权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayr();
                }
                else {
                    Toast.makeText(this, "拒绝权限将无法使用本程序", Toast.LENGTH_SHORT).show();
                    finish(); //直接关掉程序
                }
                break;
            default:

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                break;
            case R.id.play_v:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.pause_v:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.stop_v:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
            default:
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release(); //释放
        }

        if (videoView != null) {
            videoView.suspend(); //释放
        }
    }
}
