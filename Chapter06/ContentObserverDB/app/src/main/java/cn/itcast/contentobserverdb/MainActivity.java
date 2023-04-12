package cn.itcast.contentobserverdb;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    private ContentResolver resolver;
    private Uri uri;
    private ContentValues values;
    private Button btnInsert;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); //初始化界面
        createDB(); //创建数据库
    }
    private void initView() {
        btnInsert = findViewById(R.id.btn_insert);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        btnSelect = findViewById(R.id.btn_select);
        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
    }
    private void createDB() {
        //创建数据库并向info表中添加3条数据
        PersonDBOpenHelper helper = new PersonDBOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        for (int i = 0; i < 3; i++) {
            ContentValues values = new ContentValues();
            values.put("name", "itcast" + i);
            db.insert("info", null, values);
        }
        db.close();
    }
    @Override
    public void onClick(View v) {
        //得到一个内容提供者的解析对象
        resolver = getContentResolver();
        //获取一个Uri路径
        uri = Uri.parse("content://cn.itcast.contentobserverdb/info");
        //新建一个ContentValues对象，该对象以key-values的形式来添加数据到数据库表中
        values = new ContentValues();
        switch (v.getId()) {
            case R.id.btn_insert:
                Random random = new Random();
                values.put("name", "add_itcast" + random.nextInt(10));
                Uri newuri = resolver.insert(uri, values);
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                Log.i("数据库应用", "添加");
                break;
            case R.id.btn_delete:
                //返回删除数据的条目数
                int deleteCount = resolver.delete(uri, "name=?",
                        new String[]{"itcast0"});
                Toast.makeText(this, "成功删除了" + deleteCount + "行",
                        Toast.LENGTH_SHORT).show();
                Log.i("数据库应用", "删除");
                break;
            case R.id.btn_select:
                List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                //返回查询结果，是一个指向结果集的游标
                Cursor cursor = resolver.query(uri, new String[]{"_id", "name"},
                        null, null, null);
                //遍历结果集中的数据，将每一条遍历的结果存储在一个List的集合中
                while (cursor.moveToNext()) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("_id", cursor.getString(0));
                    map.put("name", cursor.getString(1));
                    data.add(map);
                }
                //关闭游标，释放资源
                cursor.close();
                Log.i("数据库应用", "查询结果：" + data.toString());
                break;
            case R.id.btn_update:
                //将数据库info表中name为itcast1的这条记录更改为name是update_itcast
                values.put("name", "update_itcast");
                int updateCount = resolver.update(uri, values, "name=?",
                        new String[]{"itcast1"});
                Toast.makeText(this, "成功更新了" + updateCount + "行",
                        Toast.LENGTH_SHORT).show();
                Log.i("数据库应用", "更新");
                break;
        }
    }
}

