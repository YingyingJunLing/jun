package com.example.jun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jun.adapter.MyAdapter;
import com.example.jun.bean.LoginBean;
import com.example.jun.interfaces.IMainView;
import com.example.jun.presenter.MainPresenter;

/**
 * 第六步   实现接口IMainView  实现方法
 */
public class MainActivity extends AppCompatActivity implements IMainView {

    private MainPresenter mainPresenter;
    private RecyclerView rec;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 第八步  设置初始化和加载数据方法
         */
        initView();
        initData();
    }

    private void initData() {
        //初始化presenter
        /**
         * 第十步：初始化presenter层
         */
        mainPresenter = new MainPresenter();
        mainPresenter.setView(this);


        /**
         * 第十一步写工具类
         */

        /**
         * 第二十四：：调用 mainPresenter  显示方法    把地址给加进去
         */

        mainPresenter.login("http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=%E5%8D%AB%E8%A1%A3&page=1&count=30");
        /**
         * 第二十五步：  创建适配器
         *
         *
         * 进入适配器
         */

        myAdapter = new MyAdapter(MainActivity.this);
        rec.setAdapter(myAdapter);

    }
    private void initView() {
        /**
         * 第九步  得到控件 并设置属性
         */
        rec = findViewById(R.id.rec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rec.setLayoutManager(linearLayoutManager);
    }

    //使用p层 加载数据
//    private void loadDataFromNet() {
//
//    }
    @Override
    public void Success(LoginBean bean)
    {
        /**
         * 第二十六步：  调用适配器adapter setdata方法();;;
         */
        myAdapter.setData(bean.getResult());
    }

    @Override
    public void Fail(String s)
    {

    }

    /**
     * 第七步  ：防止内存泄漏  调用mainPresenter方法
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.dettachView();
    }
}
