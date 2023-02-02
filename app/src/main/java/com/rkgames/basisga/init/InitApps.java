package com.rkgames.basisga.init;

import static com.rkgames.basisga.ApplicationIcyLuck.appsBool;
import static com.rkgames.basisga.ApplicationIcyLuck.arrayList1;
import static com.rkgames.basisga.ApplicationIcyLuck.arrayList2;
import static com.rkgames.basisga.startIcyLuck.StartIcyLuck.aConst;

import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.rkgames.basisga.Const;
import com.rkgames.basisga.MainActivity;
import com.rkgames.basisga.VollIcyLuck;
import com.rkgames.basisga.startIcyLuck.DataJSo;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

public class InitApps {

    private final MainActivity mainActivity;
    private final DataJSo dataJSo;

    public InitApps(MainActivity mainActivity, DataJSo dataJSo) {
        this.mainActivity = mainActivity;
        this.dataJSo = dataJSo;
        init();
    }

    private void init(){

        AppsFlyerLib.getInstance().init(dataJSo.getIdAppIcLuk(), new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> map) {

                if (appsBool){
                    for (String s : aConst.getA18()){
                        try {
                            arrayList1.add(Objects.requireNonNull(map.get(s)).toString());
                        }catch (Exception e){
                            arrayList1.add(aConst.getA20());
                        }
                    }

                    for (String s: aConst.getA19()){
                        try {
                            arrayList2.add(URLEncoder.encode(Objects.requireNonNull(map.get(s)).toString(),aConst.getA22()));
                        }catch (Exception e){
                            arrayList2.add(aConst.getA20());
                        }
                    }

                    new VollIcyLuck(mainActivity,dataJSo);
                    appsBool = false;
                }
            }

            @Override
            public void onConversionDataFail(String s) {

                if (appsBool){
                    for (int i = 0; i< aConst.getA18().length; i++) {
                        arrayList1.add(aConst.getA20());
                    }

                    for (int i = 0; i< aConst.getA19().length; i++) {
                        arrayList2.add(aConst.getA20());
                    }
                    new VollIcyLuck(mainActivity,dataJSo);
                    appsBool = false;
                }
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {
                if (appsBool){
                    for (int i = 0; i< aConst.getA18().length; i++) {
                        arrayList1.add(aConst.getA20());
                    }

                    for (int i = 0; i< aConst.getA19().length; i++) {
                        arrayList2.add(aConst.getA20());
                    }
                    new VollIcyLuck(mainActivity,dataJSo);
                    appsBool = false;
                }
            }

            @Override
            public void onAttributionFailure(String s) {
                if (appsBool){
                    for (int i = 0; i< aConst.getA18().length; i++) {
                        arrayList1.add(aConst.getA20());
                    }

                    for (int i = 0; i< aConst.getA19().length; i++) {
                        arrayList2.add(aConst.getA20());
                    }
                    new VollIcyLuck(mainActivity,dataJSo);
                    appsBool = false;
                }
            }
        },mainActivity);
        AppsFlyerLib.getInstance().start(mainActivity);
    }
}
