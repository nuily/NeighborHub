package nyc.c4q.huilin.neighborhoodhub.utils;

import java.util.concurrent.TimeUnit;

import nyc.c4q.huilin.neighborhoodhub.BuildConfig;
import nyc.c4q.huilin.neighborhoodhub.LoginActivity;

/**
 * Created by huilin on 2/1/17.
 */

public final class Constants {
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final int RC_GOOGLE_LOGIN = 3553;
    public static final String LOGIN_ACTIVITY = LoginActivity.class.getSimpleName();
    public static final long UPDATE_INTERVAL = TimeUnit.MINUTES.toMinutes(15);
    public static final long FASTEST_INTERVAL = TimeUnit.MINUTES.toMinutes(1);

}
