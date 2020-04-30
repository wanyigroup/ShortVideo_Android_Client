package com.wanyi.uiframe.fragment.container.web;

import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.usercenter.api.UserCenterTokenFactory;


import java.io.IOException;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseWebFragment extends BaseFragment {



    @BindView(R.id.web_view)
    protected WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_web;
    }

    @Override
    protected void initViews() {
        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        webView.setWebViewClient(new WebViewClient() {

//            @SuppressWarnings("deprecation")
//            public WebResourceResponse shouldInterceptRequest(WebView view, String url){
//                try {
//                    //final String acToken = pref.getString("token", "DEFAULT");
//
//                    OkHttpClient okHttpClient = new OkHttpClient();
//                    Request request = new Request.Builder().url(url).addHeader("token" ,  UserCenterTokenFactory.getAuthToken())
//                            .build();
//
//                    Response response = okHttpClient.newCall(request).execute();
//                   // Log.e(getClass().getName(), response.body().contentType().type());
//                    return new WebResourceResponse("text/html", // You can set something other as default content-type
//                            response.header("content-encoding", "utf-8"),  // Again, you can set another encoding as default
//                            response.body().byteStream());
//                }  catch (IOException e) {
//                    Log.e(getClass().getName(),e.toString());
//                    e.printStackTrace();
//                    return null;
//                }
//            }

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                url =  url + "?token="+ UserCenterTokenFactory.getAuthToken();
                Log.e(getClass().getName(),"url:" + url);
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.java_obj.getSource('<head>'+" +
                        "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                super.onPageFinished(view, url);
            }
        });


    }
}
