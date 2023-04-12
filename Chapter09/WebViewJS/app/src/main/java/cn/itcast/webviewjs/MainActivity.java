package cn.itcast.webviewjs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView webview = findViewById(R.id.webView);
        Button btn = findViewById(R.id.btn_dialog);
        webview.loadUrl("file:///android_asset/alert.html"); //指定要加载的网页
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 设置webview控件支持JavaScript代码
                webview.getSettings().setJavaScriptEnabled(true);
                // 显示网页中通过JavaScript代码弹出的提示框
                webview.setWebChromeClient(new WebChromeClient());
                webview.loadUrl("file:///android_asset/alert.html");
            };
        });
    }
}

