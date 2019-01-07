package com.cjwsc.idcm.Utils.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.cjwsc.idcm.R;

/**
 * Created by yuzhongrong on 2018/6/14.
 */

public class SoundPlayUtils {

    // SoundPool对象
    public static SoundPool mSoundPlayer = new SoundPool(10,
            AudioManager.STREAM_SYSTEM, 5);
    public static SoundPlayUtils soundPlayUtils;
    // 上下文
    static Context mContext;

    /**
     * 初始化使用init放到application中 不能动态getinstance 因为raw加载第一次是不会发出声音的
     *
     * @param context
     */
    public static SoundPlayUtils init(Context context) {
        if (soundPlayUtils == null) {
            soundPlayUtils = new SoundPlayUtils();

        }
        mContext = context;
        // 初始化声音

        //模拟数据
        mSoundPlayer.load(mContext, R.raw.general, 1);// 1
        mSoundPlayer.load(mContext,R.raw.talkroom_begin,2);//2
        mSoundPlayer.load(mContext,R.raw.playend,3);//2

        return soundPlayUtils;
    }


    private  void load(int soundID){

        if(mSoundPlayer!=null) mSoundPlayer.load(mContext, soundID, 1);




    }


    /**
     * 播放声音
     *
     * @param soundID
     */
    public static void play(int soundID) {
        mSoundPlayer.play(soundID, 1, 1, 0, 0, 1);
    }
}
