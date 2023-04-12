package cn.itcast.webview;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取布局管理器中添加的WebView控件
        WebView webview=findViewById(R.id.webView);
        webview.loadUrl("http://www.itheima.com/"); // 指定要加载的网页
    }
}

