package com.example.jun.presenter;

import com.example.jun.MainActivity;

/**
 * 第三步 建BasePresenter   主要是初始化显示页面 和得到页面  和 防止内存泄漏
 */
public class BasePresenter
{
    MainActivity activity;
    /**
     * 初始化view
     * @param
     */
    public void setView(MainActivity activity)
    {
        this.activity= activity;
    }


    public MainActivity getView()
    {
        return activity;
    }
    /**
     * 防止内存泄漏
     */
    public void dettachView()
    {
        activity = null;
    }



}
