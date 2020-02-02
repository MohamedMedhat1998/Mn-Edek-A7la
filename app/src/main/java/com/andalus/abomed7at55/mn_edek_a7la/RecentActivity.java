package com.andalus.abomed7at55.mn_edek_a7la;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.andalus.abomed7at55.mn_edek_a7la.utils.Measurements;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;


public class RecentActivity extends AppCompatActivity {

    private FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

    RecyclerView rvRecipesRecentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);


        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();

        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        mFirebaseRemoteConfig.fetch(0)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RecentActivity.this, "Fetch Succeeded",
                                    Toast.LENGTH_SHORT).show();
                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(RecentActivity.this, "Fetch Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        //TODO do something when the data is returned
                        doWhenReceived();
                    }
                });
    }

    private void doWhenReceived() {
        String s = mFirebaseRemoteConfig.getString("test_key");
        Log.d("REMOTE CONFIG VALUE", s);

        rvRecipesRecentActivity.setLayoutManager(new StaggeredGridLayoutManager(Measurements.numberOfGridLayoutColumns(this), LinearLayoutManager.VERTICAL));
    }

}
