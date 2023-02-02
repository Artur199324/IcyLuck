package com.rkgames.basisga;

import static com.rkgames.basisga.ApplicationIcyLuck.GAID;
import static com.rkgames.basisga.ApplicationIcyLuck.adb;
import static com.rkgames.basisga.ApplicationIcyLuck.appId;
import static com.rkgames.basisga.ApplicationIcyLuck.arrayList1;
import static com.rkgames.basisga.ApplicationIcyLuck.arrayList2;
import static com.rkgames.basisga.ApplicationIcyLuck.bat;
import static com.rkgames.basisga.ApplicationIcyLuck.daoTabIcyLuck;
import static com.rkgames.basisga.ApplicationIcyLuck.deep;
import static com.rkgames.basisga.ApplicationIcyLuck.entityIcyLuckData;
import static com.rkgames.basisga.startIcyLuck.StartIcyLuck.aConst;

import android.annotation.SuppressLint;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onesignal.OneSignal;
import com.rkgames.basisga.room.EntityIcyLuckData;
import com.rkgames.basisga.startIcyLuck.DataJSo;

import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class VollIcyLuck {

    private final MainActivity mainActivity;
    private final DataJSo dataJSo;

    public VollIcyLuck(MainActivity mainActivity, DataJSo dataJSo) {
        this.mainActivity = mainActivity;
        this.dataJSo = dataJSo;
        bil();
    }

    private void bil() {
        String a = aConst.getA23() + dataJSo.getGaIdIcLuk() + GAID +
                dataJSo.getApssIdtIcLuk() + appId + dataJSo.getBattIcLuk() + bat + dataJSo.getAdbIcLuk() + adb
                + dataJSo.getDeppIcLuk() + deep + dataJSo.getAfstatusIcLuk() + arrayList1.get(0) +
                dataJSo.getAfChannelIcLuk() + arrayList1.get(1) + dataJSo.getMediaSourceIcLuk() + arrayList1.get(2) +
                dataJSo.getCampaignIcLuk() + arrayList2.get(0)
                + dataJSo.getAfadIcLuk() + arrayList2.get(1) +
                dataJSo.getCampaignidIcLuk() + arrayList2.get(2)
                + dataJSo.getAdsetidIcLuk() + arrayList2.get(3) +
                dataJSo.getAdidIcLuk() + arrayList2.get(4) +
                dataJSo.getAdsetIcLuk() + arrayList2.get(5);
        co(a);
    }

    @SuppressLint("CheckResult")
    private void co(String a) {

        SingleOnSubscribe<String> single = new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, a, response ->
                        emitter.onSuccess(response),
                        error -> mainActivity.dialog());
                DefaultRetryPolicy defaultRetryPolicy = new DefaultRetryPolicy(15311, 5, 1f);
                stringRequest.setRetryPolicy(defaultRetryPolicy);
                RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
                requestQueue.add(stringRequest);
            }
        };

        Single.create(single).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    mainActivity.dialog();
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {


                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int a = 0;
                            if (jsonObject.has("rrewsazzzz")) {
                                a = 1;
                            } else if (jsonObject.has("ggfsssszz")) {
                                a = 2;
                            }
                            switch (a) {
                                case 1:
                                    fff(jsonObject.getString("rrewsazzzz"));
                                    break;
                                case 2:
                                    fff(jsonObject.getString("ggfsssszz"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mainActivity.dialog();
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void fff(String s) {
        Log.d("weq", s);
        if (!s.equals("hhgffff")) {

            OneSignal.setExternalUserId(appId);
            OneSignal.sendTag("sub_app", tag());
        }
        entityIcyLuckData = new EntityIcyLuckData(s);
        Completable.fromAction(() -> daoTabIcyLuck.insertIcyLuckData(entityIcyLuckData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d("dss", "dsds"),
                        throwable -> Log.d("dss", "dsd"));
    }

    private String tag() {
        String[] y;
        String[] sub = null;
        String s;
        String tte;
        try {
            y = URLDecoder.decode(deep, aConst.getA22()).split("://");
        } catch (Exception e) {
            y = null;
        }

        try {
            s = Objects.requireNonNull(y)[1];
        } catch (Exception e) {
            s = null;
        }

        try {

            if (!Objects.equals(deep, aConst.getA20())) {

                sub = Objects.requireNonNull(s).split("_");
            }
            if (!Objects.equals(arrayList2.get(0), aConst.getA20())) {
                sub = arrayList2.get(0).split("_");
            }
        } catch (Exception e) {
            Log.e("e", e.getMessage());
        }

        if (sub != null) {
            tte = sub[1];
        } else {
            tte = aConst.getA21();
        }

        return tte;
    }
}
