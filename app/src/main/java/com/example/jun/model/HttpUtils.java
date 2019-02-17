package com.example.jun.model;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.jun.bean.LoginBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 十一步，在这呢
 * @param <T>
 */
public class HttpUtils<T>
{
    public static HttpUtils instance;
    LoginBean loginBean;
    public HttpUtils()
    {

    }

    /**
     * 创建单例模式  死记   第十二步
     * @return
     */
    public static HttpUtils getInstance()
    {
        if(instance == null)
        {
            return new HttpUtils();
        }else{
            return instance;
        }
    }

    /**
     * 第十四步：写handler
     */
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            T obj = (T) msg.obj;


            /**
             * 第十六部  ：回调解析成功的方法
             */
            gv.chenggong(obj);


            /**
             * 第十七步：进到mainPresenter 层了
             */




        }
    };
    /**
     * 封装get方法  第十三步
     */
    public void doGet(String path)
    {
        OkHttpClient okHttpClient = new OkHttpClient();
        //使用构建者设计模式创建对象
        Request request = new Request.Builder().url(path).get().build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //当前数据回调在子线程
                String string = response.body().string();
                Log.e("myMessage",""+string);
                Gson gson = new Gson();
                T t = (T) gson.fromJson(string,LoginBean.class);
                //callbackData.onSuccess(t);
                Message message = handler.obtainMessage();
                message.obj = t;
                handler.sendMessage(message);

            }
        });

    }
    /**
     * 封装post方法
     */
    public void doPost(String url,String name,String page,String count)
    {
        //创建okhttpduixinag
        OkHttpClient httpClient = new OkHttpClient.Builder()

                .readTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS)
                .build();
        //创建body体存值
        final FormBody body = new FormBody.Builder()
                .add("keyword", name)
                .add("count", page)
                .add("count", count)
                .build();
        //创建request请求
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
              gv.shibai();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("+++++++=",body+"");
                Gson gson = new Gson();

                T t = (T) gson.fromJson(string,LoginBean.class);
                //callbackData.onSuccess(t);
                Message message = handler.obtainMessage();
                message.obj = t;
                handler.sendMessage(message);

            }
        });

    }

    /**
     * 第十五步  ：写接口   从这往下抄
     * @param <D>
     */
    public interface getView<D>
    {
        public void chenggong(D d);
        public void shibai();
    }
    getView gv;
    public void HuiDiao(getView gv)
    {
        this.gv =gv;
    }

}
