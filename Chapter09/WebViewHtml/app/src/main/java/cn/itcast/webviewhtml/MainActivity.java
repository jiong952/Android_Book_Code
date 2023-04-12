package cn.itcast.webviewhtml;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取布局管理器中添加的WebView控件
        WebView webview = findViewById(R.id.webView);
        // 创建一个字符串构建器，将要显示的HTML内容放置在该构建器中
        StringBuilder sb = new StringBuilder();
        sb.append("<div>请选择您要学习的课程：</div>");
        sb.append("<ul>");
        sb.append("<li>新媒体课程</li>");
        sb.append("<li>大数据课程</li>");
        sb.append("<li>人工智能课程</li>");
        sb.append("</ul>");
        // 加载数据
        webview.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8",
                null);
    }
}

