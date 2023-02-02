package com.rkgames.basisga;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;


import com.appsflyer.AppsFlyerLib;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import com.onesignal.OneSignal;
import com.rkgames.basisga.room.DaoTabIcyLuck;
import com.rkgames.basisga.room.DatabaseinstanceIcyLuck;
import com.rkgames.basisga.room.EntityIcyLuck;
import com.rkgames.basisga.room.EntityIcyLuckData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ApplicationIcyLuck extends Application {
    public static String GAID = "null";
    public static String appId = "null";
    public static String adb;
    public static String bat;
    public static EntityIcyLuckData entityIcyLuckData;
    public static EntityIcyLuck entityIcyLuck;
    public static DaoTabIcyLuck daoTabIcyLuck;
    public static String lol;
    public static boolean retu = true;
    public static String deep = "null";
    public static boolean appsBool = true;
    public static ArrayList<String> arrayList1;
    public static ArrayList<String> arrayList2;
    public static boolean aaa = false;

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        onSig("4fcc64e0-1648-4cc4-9f3d-79ae631c3402");

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        DatabaseinstanceIcyLuck databaseinstanceIcyLuck = DatabaseinstanceIcyLuck.getInstance(this);
        daoTabIcyLuck = databaseinstanceIcyLuck.daoTabIcyLuck();
        SingleOnSubscribe<String> source = null;
        try {
         source = e -> e.onSuccess(AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext()).getId());
        }catch (Exception e){
            e.printStackTrace();
        }

        //noinspection ResultOfMethodCallIgnored
        Single.create(source)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> GAID = "null")
                .subscribe((Consumer<? super String>) s -> {
                    GAID = s;
                });

        try {
            appId = AppsFlyerLib.getInstance().getAppsFlyerUID(this);
        }catch (Exception e){
            appId = "null";
        }
    }

    public void onSig(String s) {
        OneSignal.initWithContext(this);
        OneSignal.setAppId(s);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
    }


}
