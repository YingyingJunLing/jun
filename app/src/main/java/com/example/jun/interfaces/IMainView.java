package com.example.jun.interfaces;

import com.example.jun.bean.LoginBean;

/**
 * 第二步 定义解析成功失败的方法
 */
public interface IMainView  extends IBaseView
{
     void Success(LoginBean bean);
      void Fail(String s);

}
