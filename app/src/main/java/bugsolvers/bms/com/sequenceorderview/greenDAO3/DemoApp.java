package bugsolvers.bms.com.sequenceorderview.greenDAO3;

import android.app.Application;

import bugsolvers.bms.com.sequenceorderview.daoExample.DaoMaster;
import bugsolvers.bms.com.sequenceorderview.daoExample.DaoSession;

public class DemoApp extends Application
{

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
      /*  mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();

        // USER CREATION FOR DEMO PURPOSE
        if(mDaoSession.getUserDao().loadAll().size() == 0){
            mDaoSession.getUserDao().insert(new User(1L, "Raju ","", ""));
        }*/

        mDaoSession =
                new DaoMaster(new DbOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();

        // USER CREATION FOR DEMO PURPOSE
        if(mDaoSession.getUserDao().loadAll().size() == 0){
            mDaoSession.getUserDao().insert(new User(1L, "Raju Sah","", ""));
        }
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}