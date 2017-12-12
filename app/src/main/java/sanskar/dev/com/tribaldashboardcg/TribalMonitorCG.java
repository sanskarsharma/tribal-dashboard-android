package sanskar.dev.com.tribaldashboardcg;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sanskar on 10-Dec-17.
 */

public class TribalMonitorCG extends Application {
    public static TribalMonitorCG instance;


    public TribalMonitorCG(){
        instance = this;
    }

    public static TribalMonitorCG getInstance() {

        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
