package com.rkgames.basisga.init;

import static com.rkgames.basisga.ApplicationIcyLuck.deep;

import androidx.annotation.Nullable;

import com.facebook.FacebookSdk;
import com.facebook.applinks.AppLinkData;
import com.rkgames.basisga.MainActivity;
import com.rkgames.basisga.startIcyLuck.DataJSo;

import java.net.URLEncoder;
import java.util.Objects;

public class InitFb {
    private final MainActivity mainActivity;
    private final DataJSo dataJSo;

    public InitFb(MainActivity mainActivity, DataJSo dataJSo) {
        this.mainActivity = mainActivity;
        this.dataJSo = dataJSo;
        init();
    }

    private void init(){
        FacebookSdk.setApplicationId(dataJSo.getIdFbIcLuk());
        FacebookSdk.setClientToken(dataJSo.getTokFbIcLuk());
        FacebookSdk.sdkInitialize(mainActivity);
        FacebookSdk.setAdvertiserIDCollectionEnabled(true);
        FacebookSdk.setAutoInitEnabled(true);
        FacebookSdk.fullyInitialize();
        getDeep();
    }

    private void getDeep(){
        AppLinkData.fetchDeferredAppLinkData(mainActivity, new AppLinkData.CompletionHandler() {
            @Override
            public void onDeferredAppLinkDataFetched(@Nullable AppLinkData appLinkData) {
                try {
                    if (appLinkData == null){
                        appLinkData = AppLinkData.createFromActivity(mainActivity);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    if (appLinkData != null){
                        deep = URLEncoder.encode(Objects.requireNonNull(appLinkData.getTargetUri()).toString(),"utf-8");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
