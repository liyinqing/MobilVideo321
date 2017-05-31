package atguigu.com.mobilvideo321.app;

import android.app.Application;
import android.content.Context;

import org.xutils.BuildConfig;
import org.xutils.x;



public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
    public static Context getContext(){
        return mContext;
    }
}
