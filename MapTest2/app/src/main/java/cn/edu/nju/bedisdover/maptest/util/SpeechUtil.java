package cn.edu.nju.bedisdover.maptest.util;

import android.content.Context;
import android.os.Bundle;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

import cn.edu.nju.bedisdover.maptest.ScenicActivity;
import cn.edu.nju.bedisdover.maptest.ScenicDemoActivity;

/**
 * Created by song on 16-10-4.
 * <p>
 * 合成语音工具类
 */
public class SpeechUtil {

    private static SpeechSynthesizer mTts;

    private static Context context;

    private SpeechUtil() {
    }

    /**
     * 开始语音朗读
     *
     * @param content 需要朗读的内容
     */
    public static void startSpeaking(Context context, String content) {
        SpeechUtil.context = context;
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        mTts = SpeechSynthesizer.createSynthesizer(context, null);
        //2.合成参数设置
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //3.开始合成
        mTts.startSpeaking(content, mSynListener);
    }

    /**
     * 暂停
     */
    public static void pauseSpeaking() {
        if (mTts != null) {
            mTts.pauseSpeaking();
        }
    }

    /**
     * 停止
     */
    public static void stopSpeaking() {
        if (mTts != null) {
            mTts.stopSpeaking();
        }
    }

    /**
     * 恢复
     */
    public static void resumeSpeaking() {
        if (mTts != null) {
            mTts.resumeSpeaking();
        }
    }


    //合成监听器
    private static SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
            if (context instanceof ScenicActivity) {
                ((ScenicActivity) context).complete();

                return;
            }

            if (context instanceof ScenicDemoActivity) {
                ((ScenicDemoActivity) context).complete();
            }
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，
        //endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        public void onSpeakBegin() {
        }

        //暂停播放
        public void onSpeakPaused() {
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
        }

        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        }
    };
}
