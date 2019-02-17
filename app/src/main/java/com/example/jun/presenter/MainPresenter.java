package com.example.jun.presenter;

import com.example.jun.bean.LoginBean;
import com.example.jun.model.HttpUtils;

/**
 * 第四步  继承BasePresenter
 */
public class MainPresenter extends BasePresenter implements HttpUtils.getView<LoginBean>
{

    HttpUtils instance;

    /**
     * 第十七部：  写一个构造方法
     */
    public MainPresenter() {
        /**
         * 第十八步：：：：得到工具类
         */
        instance = HttpUtils.getInstance();
        /**
         * 第二十一步：调用MainPresenter 回调方法
         */
        instance.HuiDiao(this);

    }

    /**
     *  第五步   定义请求方法 记得传地址 http
     * @param url
     */
    public void login(String url)
    {
        /**
         * 第十九步：   调用 get方法
         */
        instance.doGet(url);


        /**
         * 第二十步：   实现httpUtil的接口  实现方法  在最上面   记得看
         */
    }
    public void reg(String name,String pass)
    {


    }

    @Override
    public void chenggong(LoginBean o) {
        /**
         * 第二十二步  ：加载成功给  显示页面
         */
        getView().Success(o);
        /**
         * 第二十三步   ： 进入到activity页面
         */

    }

    @Override
    public void shibai() {

    }
}
