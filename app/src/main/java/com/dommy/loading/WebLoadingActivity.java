package com.dommy.loading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dommy.loading.listener.BtnBackListener;

/**
 * 网页加载进度条
 */
public class WebLoadingActivity extends AppCompatActivity {
    private TextView tvTiltle; // 标题
    private ImageButton btnBack; // 返回按钮
    private WebView webView;
    private ProgressBar proWebView; // 进度条

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_loading);

        initView();

        initWebview();

        webView.loadUrl("https://blog.csdn.net/ahuyangdong");
    }

    private void initView() {
        tvTiltle = (TextView) findViewById(R.id.txt_title);
        tvTiltle.setText("网页加载进度条");
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new BtnBackListener(this));

        proWebView = (ProgressBar) findViewById(R.id.pro_webview);
        webView = (WebView) findViewById(R.id.webview);
    }

    private void initWebview() {
        // 对webview进行相关设置
        WebSettings settings = webView.getSettings();
        // 让网页的内容成单列显示出来，可以自适应手机的屏幕大小
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);

        // 使新页面在app中打开
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
    }


    // 获取加载进度
    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                proWebView.setVisibility(View.GONE);
            } else {
                if (proWebView.getVisibility() == View.GONE) {
                    proWebView.setVisibility(View.VISIBLE);
                }
                proWebView.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    };

    // 在本界面加载新的url
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };
}
