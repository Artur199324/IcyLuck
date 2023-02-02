package com.rkgames.basisga;

import static com.rkgames.basisga.ApplicationIcyLuck.aaa;
import static com.rkgames.basisga.ApplicationIcyLuck.daoTabIcyLuck;
import static com.rkgames.basisga.ApplicationIcyLuck.entityIcyLuck;
import static com.rkgames.basisga.ApplicationIcyLuck.lol;
import static com.rkgames.basisga.ApplicationIcyLuck.retu;
import static com.rkgames.basisga.startIcyLuck.StartIcyLuck.aConst;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.rkgames.basisga.room.EntityIcyLuck;


import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class IcyLuckWeActivity extends AppCompatActivity {

    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(webView);
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        sss();
        lk();
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setLayerType(WebView.LAYER_TYPE_HARDWARE, null);
        webView.setSaveEnabled(true);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        oi();
        webView.loadUrl(lol);
    }

    private void init() {
        webView = new WebView(this);
        Toast toast = Toast.makeText(getApplicationContext(),
                "Loading!!!", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void lk() {
        CookieManager.getInstance().setAcceptCookie(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        if (aaa){
            int[] arr = {2, 0, 1, 3};
            for (int el : arr) {
                System.out.println(el);
            }
            int[] arr2 = {1, 9, 9, 5};
            for (int i = 0; i < arr2.length; i++) {
                int el = arr2[i];
                System.out.println(el);
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void sss() {
        if (aaa){
            int[][] array = { {0, 1, 2, 3, 4 },
                    {1, 2, 3, 4, 5},
                    {2, 3, 4, 5, 6},
                    {3, 4, 5, 6, 7}};

            for(int i = 0; i < array.length; i++) {
                for(int j = 0; j < array[i].length; j++) {
                    System.out.print(array[i][j] + " ");
                }
                System.out.print('\n');
            }
        }
        webView.getSettings().setMixedContentMode(0);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setUserAgentString(webView.getSettings().getUserAgentString().replace("; wv", ""));
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            webView.getSettings().setSaveFormData(true);
        }
    }

    private void oi() {
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (view.getTitle().contains(aConst.getE10())) {
                    retu = false;
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    dl(url);
                }
                if (aaa){
                    int n = 5;
                    int i = 0;

                    do {
                        System.out.println("New line " + i);
                        i++;
                    } while (i < n);
                    int[] arr = {10, 15, 20, 25, 30};

                    for (int a : arr) {
                        System.out.println(a + 1);
                    }
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (aaa){
                    int count = 1;
                    while (true) {
                        System.out.println("dssd №" + count);
                        if (count > 3) {
                            break;
                        }
                        count++;
                    }
                }
                if (request.getUrl().toString().startsWith(aConst.getE1())) {

                    gg(1, request, view);

                } else if (request.getUrl().toString().startsWith(aConst.getE5())) {

                    gg(2, request, view);

                } else if (request.getUrl().toString().startsWith(aConst.getE7())) {
                    gg(3, request, view);
                } else {
                    request.getUrl();
                    if (request.getUrl().toString().startsWith(aConst.getE8()) || request.getUrl().toString().startsWith(aConst.getE9()))
                        return false;
                }
                try {
                    gg(4, request, view);
                    return true;
                } catch (Exception e) {
                    return true;
                }
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                if (mFilePathCallback != null) {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = filePathCallback;
                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("*/*");
                Intent[] intentArray = new Intent[0];
                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Select Option:");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                //noinspection deprecation
                startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
                if (aaa){
                    for (int i = 0; i < 10; i++){
                        if (i == 5)
                            continue;
                        System.out.println(i);
                    }
                    for (int i = 0; i < 10; i++){
                        if (i == 5)
                            break;
                        System.out.println(i);
                    }
                }
                return true;
            }
        });
    }

    public static final int INPUT_FILE_REQUEST_CODE = 1;
    public static final int FILECHOOSER_RESULTCODE = 1;
    public ValueCallback<Uri> mUploadMessage;
    public Uri mCapturedImageURI = null;
    public ValueCallback<Uri[]> mFilePathCallback;
    public String filePath;

    private void gg(int a, WebResourceRequest request, WebView view) {
        if (a == 1) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType(aConst.getE2());
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{request.getUrl().toString().replace(aConst.getE3(), "")});
            startActivity(Intent.createChooser(intent, aConst.getE4()));
        } else if (a == 2) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(request.getUrl().toString()));
            startActivity(Intent.createChooser(intent, aConst.getE6()));
        } else if (a == 3) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(request.getUrl().toString()));
            startActivity(intent);
        } else if (a == 4) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(request.getUrl().toString()));
            view.getContext().startActivity(intent);
        }

        if (aaa){
            int i;
            for (i = 0; i < 5; i++) {
                if (i >= 3) {
                    break;
                }
                System.out.println("Yuhu");
                if (i >= 1) {
                    continue;
                }
                System.out.println("Tata");
            }
        }
    }

    private void dl(String url) {
        daoTabIcyLuck.getEntityIcyLuck()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<EntityIcyLuck>() {
                    @Override
                    public void onSuccess(EntityIcyLuck entityIcyLuck) {
                    }

                    @SuppressLint("CheckResult")
                    @Override
                    public void onError(Throwable e) {

                        entityIcyLuck = new EntityIcyLuck(url);
                        Completable.fromAction(() -> daoTabIcyLuck.insertIcyLuck(entityIcyLuck))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(() -> Log.d("ree", "rre"),
                                        throwable -> Log.d("ere", "erer"));
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != INPUT_FILE_REQUEST_CODE || mFilePathCallback == null) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                if (filePath != null) {
                    results = new Uri[]{Uri.parse(filePath)};
                }
            } else {
                String dataString = data.getDataString();
                if (dataString != null) {
                    results = new Uri[]{Uri.parse(dataString)};
                }
            }
        }
        mFilePathCallback.onReceiveValue(results);
        mFilePathCallback = null;
        //noinspection ConstantConditions
        if (requestCode != FILECHOOSER_RESULTCODE || mUploadMessage == null) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        Uri result = null;
        try {
            if (resultCode != RESULT_OK) //noinspection ConstantConditions
                result = null;
            else {
                // retrieve from the private variable if the intent is null
                result = data == null ? mCapturedImageURI : data.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mUploadMessage.onReceiveValue(result);
        mUploadMessage = null;
    }

    private boolean ff = false;

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            if (ff) {
                daoTabIcyLuck.getEntityIcyLuck()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableSingleObserver<EntityIcyLuck>() {

                            @Override
                            public void onSuccess(EntityIcyLuck entityIcyLuck) {
                                webView.loadUrl(entityIcyLuck.saveEntityIcyLuck);
                            }

                            @Override
                            public void onError(Throwable e) {
                                webView.loadUrl(lol);
                            }
                        });
            }
            vb();

        }
    }

    private void vb() {
        ff = true;
        webView.goBack();
        new Handler(Looper.getMainLooper()).postDelayed(() -> ff = false, 2100);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try {
            webView.restoreState(savedInstanceState);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        try {
            webView.saveState(outState);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}