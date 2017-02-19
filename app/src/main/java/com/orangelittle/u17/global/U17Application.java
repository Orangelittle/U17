package com.orangelittle.u17.global;

import android.app.Application;
import android.content.Context;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.orangelittle.u17.util.StringUtil;
import com.orangelittle.u17.util.ValueUtil;

import io.realm.Realm;

public class U17Application extends Application {
    public static Context context;
    public static String uuid;
    public static String UUID = "uuid";
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Realm.init(this);
        context = getApplicationContext();

        if (StringUtil.isNull(uuid)) {
            uuid = ValueUtil.getValue(U17Application.UUID);
        }
//        StrictMode.setThreadPolicy(new StrictMode
//                .ThreadPolicy
//                .Builder()
//                .penaltyDeath()
//                .build());
//        StrictMode.setVmPolicy(new StrictMode
//                .VmPolicy
//                .Builder()
//                .penaltyDeath()
//                .build());
    }

    public static Context getContext() {
        return context;
    }
}
