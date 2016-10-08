package cn.edu.nju.bedisdover.maptest.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

import java.io.IOException;

import cn.edu.nju.bedisdover.maptest.MainActivity;
import cn.edu.nju.bedisdover.maptest.R;
import cn.edu.nju.bedisdover.maptest.util.AssetCopyer;

/**
 * Created by song on 16-10-8.
 * <p>
 * 加载动画界面
 */
public class LoadActivity extends Activity {
    private SurfaceView sv;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        try {
            new AssetCopyer(this).copy();
            initVideo();
        } catch (IOException e) {
            turnToMain();
        }
    }

    /**
     * 初始化视频播放
     */
    private void initVideo() {
        sv = (SurfaceView) findViewById(R.id.video_load);

        // 为SurfaceHolder添加回调
        sv.getHolder().addCallback(callback);

        // 4.0版本之下需要设置的属性
        // 设置Surface不维护自己的缓冲区，而是等待屏幕的渲染引擎将内容推送到界面
        sv.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            play(0);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            /*do nothing*/
        }

    };

    /**
     * 开始播放
     *
     * @param msec 播放初始位置
     */
    protected void play(final int msec) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            // 设置播放的视频源
            mediaPlayer.setDataSource(getExternalFilesDir(null).getAbsolutePath() + "/video.mp4");

            // 设置显示视频的SurfaceHolder
            mediaPlayer.setDisplay(sv.getHolder());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                    int width = mediaPlayer.getVideoWidth();
                    int height = mediaPlayer.getVideoHeight();
                    // 设置SurfaceView的大小并居中显示
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width,
                            height);
                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                    sv.setLayoutParams(layoutParams);

                    mediaPlayer.start();
                    // 按照初始位置播放
                    mediaPlayer.seekTo(msec);
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // 在播放完毕被回调
                    turnToMain();
                }
            });

            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // 发生错误重新播放
                    play(0);
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 转到主界面
     */
    private void turnToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
