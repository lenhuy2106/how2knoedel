package de.nle.how2knoedel;

import android.app.Application;
import android.content.Context;

/**
 * Created by T500 on 17.11.2014.
 */
public class KnoedelContext extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }
}
