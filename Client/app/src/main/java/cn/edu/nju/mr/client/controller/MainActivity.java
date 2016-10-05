package cn.edu.nju.mr.client.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.edu.nju.mr.client.R;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import cn.edu.nju.mr.client.util.SpeechUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化语音配置对象
        // 必须放在主线程中
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=57f2565f");
    }

    public void testSpeech(View view) {
        SpeechUtil.startSpeaking(this, "语音测试");
    }
}
