package cn.edu.nju.bedisdover.maptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import cn.edu.nju.bedisdover.maptest.model.Scenic;
import cn.edu.nju.bedisdover.maptest.util.ScenicUtil;
import cn.edu.nju.bedisdover.maptest.util.SpeechUtil;

/**
 * Created by song on 16-10-7.
 * <p>
 * 景点界面
 */
public class ScenicActivity extends AppCompatActivity {

    private Scenic scenic;

    private boolean initial;

    /**
     * 标记是否开始播放
     */
    private boolean start;

    /**
     * 标记是否暂停
     */
    private boolean pause;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        scenic = ScenicUtil.getScenicByName(name);

        init();

        if (!initial) {
            // 初始化语音配置对象
            // 必须放在主线程中
            SpeechUtility.createUtility(this, SpeechConstant.APPID + "=57f2565f");
            initial = true;
        }
    }

    private void init() {
        TextView name = (TextView) findViewById(R.id.activity_scenic_name);
        name.setText(scenic.getName());

        TextView description = (TextView) findViewById(R.id.activity_scenic_description);
        description.setText(scenic.getDescription());

        final ImageButton play = (ImageButton) findViewById(R.id.activity_scenic_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!start) {
                    SpeechUtil.startSpeaking(ScenicActivity.this, scenic.getDescription());
                    play.setImageDrawable(getResources().getDrawable(R.drawable.pause));

                    start = true;

                    return;
                }

                if (!pause) {
                    SpeechUtil.pauseSpeaking();
                    play.setImageDrawable(getResources().getDrawable(R.drawable.play));

                    pause = true;
                } else {
                    SpeechUtil.resumeSpeaking();
                    play.setImageDrawable(getResources().getDrawable(R.drawable.pause));

                    pause = false;
                }
            }
        });
    }

    /**
     * 返回主界面
     */
    public void back(View view) {
        Intent intent = new Intent(ScenicActivity.this, MainActivity.class);

        startActivity(intent);
    }

    /**
     * 语音结束后的回调
     */
    public void complete() {
        start = false;
    }
}
