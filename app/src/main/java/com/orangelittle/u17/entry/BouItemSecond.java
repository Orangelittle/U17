package com.orangelittle.u17.entry;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.orangelittle.u17.R;
import com.orangelittle.u17.activity.RankActivity;
import com.orangelittle.u17.activity.SpecialTopicActivity;
import com.orangelittle.u17.other_activity.Animation_Activity;
import com.orangelittle.u17.other_activity.Classify_Activity;

/**
 * Created by Administrator on 2016/9/30.
 */

public class BouItemSecond {
    Intent intent = null;
    Class<?> clazz = null;
    public void click(View v){
        switch (v.getId()) {
            case R.id.bou_item_secound_rank:
            clazz = RankActivity.class;
                break;
            case R.id.bou_item_secound_classify:
             clazz = Classify_Activity.class;
                break;
            case R.id.bou_item_secound_animation:
                clazz = Animation_Activity.class;
                break;
            case R.id.bou_item_secound_special:
                clazz = SpecialTopicActivity.class;
                break;

        }
        jumpOtherActivity(clazz,v.getContext());
    }
    public void jumpOtherActivity(Class clazz , Context context){
        intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
