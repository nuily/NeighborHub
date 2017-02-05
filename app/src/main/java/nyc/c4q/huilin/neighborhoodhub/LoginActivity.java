package nyc.c4q.huilin.neighborhoodhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import java.net.URI;

import nyc.c4q.huilin.neighborhoodhub.crier.CrierRecyclerFragment;
import nyc.c4q.huilin.neighborhoodhub.utils.Constants;

/**
 * Created by rook on 1/29/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    FrameLayout flFragmentHolder;
    LinearLayout llLoginMain;
    ImageView profileImage;
    TextView googleLogin;
    TextView facebookLogin;
    URI userProfilePic;
    GoogleSignInOptions gso;
    GoogleApiClient googleApiClient;
    private String displayName;
    private String gmail;
    private String googleId;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        flFragmentHolder = (FrameLayout) findViewById(R.id.fl_fragment_holder);
        llLoginMain = (LinearLayout) findViewById(R.id.ll_login_main);
        profileImage = (ImageView) findViewById(R.id.iv_login);
        googleLogin = (TextView) findViewById(R.id.tv_google_login);
        facebookLogin = (TextView) findViewById(R.id.tv_facebook_login);
        userProfilePic = null;

        gso = buildGoogleSignInOptions();
        googleApiClient = buildGoogleApiClient();

        firebaseAuth = FirebaseAuth.getInstance();
        authListener = getAuthStateListener();
    }

    @NonNull
    private FirebaseAuth.AuthStateListener getAuthStateListener() {
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // When successfully authenticated through Firebase, get the user account data
                    Log.d(Constants.LOGIN_ACTIVITY, "onAuthStateChanged: signed in: " + user.getUid());
                    Intent intent = new Intent(getBaseContext(), DisplayActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // What should happen when user signs out?
                    Log.d(Constants.LOGIN_ACTIVITY, "onAuthStateChanged: signed out");
                }
            }
        };
    }

    @NonNull
    private GoogleApiClient buildGoogleApiClient() {
        return new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @NonNull
    private GoogleSignInOptions buildGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(BuildConfig.O_AUTH_CLIENT_ID)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Picasso.with(this).
                load(R.drawable.default_profile).error(R.drawable.default_profile).into(profileImage);
        googleLogin.setOnClickListener(this);
        facebookLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_google_login:
                googleLoginProcess();
                break;
            case R.id.tv_facebook_login:
                facebookLoginProcess();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null) {
            firebaseAuth.removeAuthStateListener(authListener);
        }
    }

    private void facebookLoginProcess() {
        Toast.makeText(this, "Logging in using Facebook", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ll_login_main, CrierRecyclerFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    private void googleLoginProcess() {
        Toast.makeText(this, "Logging in using Google", Toast.LENGTH_SHORT).show();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, Constants.RC_GOOGLE_LOGIN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.RC_GOOGLE_LOGIN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(Constants.LOGIN_ACTIVITY, "handleSignInResult: " + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            firebaseAuthWithGoogle(acct);
        } else {
            Toast.makeText(this, "Google Login Failed", Toast.LENGTH_LONG).show();
            Log.d(Constants.LOGIN_ACTIVITY, "handleSignInResult: failed");
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(Constants.LOGIN_ACTIVITY, "firebaseAuthWithGoogle: " + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(Constants.LOGIN_ACTIVITY, "onComplete: " + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w(Constants.LOGIN_ACTIVITY, "onComplete: " + task.getException());
                            Toast.makeText(LoginActivity.this, "Firebase Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}