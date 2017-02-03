package nyc.c4q.huilin.neighborhoodhub;

import android.content.Context;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by huilin on 2/3/17.
 */

public class GoogleServices {

    private static class GoogleSignInOptHolder {
        private static final GoogleSignInOptions INSTANCE = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(BuildConfig.O_AUTH_CLIENT_ID)
                .build();
    }

    public static GoogleSignInOptions getGsoInstance() {
        return GoogleSignInOptHolder.INSTANCE;
    }

    private static class GoogleApiClientHolder {

        private static GoogleApiClient loginClient;
        private static GoogleApiClient locationClient;

    }

    public static void setLoginClient(Context applicationContext) throws Exception {
        if (GoogleApiClientHolder.loginClient != null) {
            throw new Exception("Login Client has already been created");
        } else {

            GoogleApiClientHolder.loginClient = new GoogleApiClient.Builder(applicationContext)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, getGsoInstance())
                    .build();
        }
    }

    public static GoogleApiClient getLoginClient() throws Exception {
        if (GoogleApiClientHolder.loginClient == null) {
            throw new Exception("Invoke setLoginClient first");
        } else {
            return GoogleApiClientHolder.loginClient;
        }
    }

    public static void setLocationClient(Context applicationContext, GoogleApiClient.ConnectionCallbacks connectionCallbacks,
                                         GoogleApiClient.OnConnectionFailedListener connectionFailedListener) throws Exception {
        if (GoogleApiClientHolder.locationClient != null) {
            throw new Exception("Location Client has already been created");
        } else {

            GoogleApiClientHolder.locationClient = new GoogleApiClient.Builder(applicationContext)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, getGsoInstance())
                    .addConnectionCallbacks(connectionCallbacks)
                    .addOnConnectionFailedListener(connectionFailedListener)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    public static GoogleApiClient getLocationClient() throws Exception {
        if (GoogleApiClientHolder.locationClient == null) {
            throw new Exception("Invoke setlocationClient first");
        } else {
            return GoogleApiClientHolder.locationClient;
        }
    }
}
