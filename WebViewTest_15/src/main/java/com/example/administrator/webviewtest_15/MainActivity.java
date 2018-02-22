package com.example.administrator.webviewtest_15;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WebView webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true); //让WebView支持Js

        //非常重要，传入一个WebViewClient实例。
        //当需要从一个网页跳转到另一个网页，希望目标网页仍然在当前WebView中显示
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");
    }
}
