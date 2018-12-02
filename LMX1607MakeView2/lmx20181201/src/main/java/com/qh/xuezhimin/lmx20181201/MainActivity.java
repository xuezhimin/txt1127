package com.qh.xuezhimin.lmx20181201;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.qh.xuezhimin.lmx20181201.adapter.MyHotAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageView mBack;
    private GridView mHotSearch;
    private List<String> list_hot;
    /**
     * 清除历史记录
     */
    private Button mBtnDelAll;
    private FlowLayout mFlowLayout;
    /**
     * 请输入关键字
     */
    private EditText mSearchName;
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏原来的bar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initData();

//        toolbar.setLogo(R.drawable.icon_left);
//        toolbar.setTitle("Title");
//        toolbar.setSubtitle("Sub Title");

        toolbar.inflateMenu(R.menu.toolbar_menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        Toast.makeText(MainActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.follow:
                        Toast.makeText(MainActivity.this, "已关注", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.search:
                        String username = mSearchName.getText().toString();
                        //数据库插入搜索历史
                        helper.insert(username);
                        //流式布局添加搜索内容
                        mFlowLayout.addTextView(username);
                        break;
                }


                return false;
            }
        });


    }

    private void initData() {

        list_hot = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            list_hot.add("娃哈哈");
        }

        //热门搜索 适配器
        MyHotAdapter adapter = new MyHotAdapter(this, list_hot);
        mHotSearch.setAdapter(adapter);

    }

    private void initView() {
        toolbar = findViewById(R.id.tool_bar);
        mBack = findViewById(R.id.back);
        mHotSearch = findViewById(R.id.hot_search);
        mBtnDelAll = findViewById(R.id.btn_delAll);
        mFlowLayout = findViewById(R.id.flow_layout);
        mBack.setOnClickListener(this);
        mBtnDelAll.setOnClickListener(this);
        mSearchName = findViewById(R.id.search_name);

        //初始化数据库
        helper = new DBHelper(this);
        //历史搜索查询
        List<String> data = helper.query();
        for (int i = 0; i < data.size(); i++) {
            //流式布局添加历史
            mFlowLayout.addTextView(data.get(i));
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回键返回
            case R.id.back:
                finish();
                break;
            //清除历史记录
            case R.id.btn_delAll:
                //数据库清空所有数据
                helper.delete();
                mFlowLayout.removeAllViews();
                break;

        }
    }
}
