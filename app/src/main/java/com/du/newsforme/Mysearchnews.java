package com.du.newsforme;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.du.newsforme.bean.News;
import com.du.newsforme.bean.SearchNews;
import com.du.newsforme.utils.OKGoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lzy.okgo.callback.StringCallback;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/29.
 */

public class Mysearchnews extends SwipeBackActivity {
    @BindView(R.id.clt_search)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.sdv_News)
    SimpleDraweeView simpleDraweeView;

    @BindView(R.id.tb_searchnewsdetail)
    Toolbar toolbar;
    @BindView(R.id.pb_search)
    ProgressBar progressBar;
    @BindView(R.id.webview_news)
    WebView webView;
    @BindView(R.id.fab_search_share)
    FloatingActionButton actionButton;
    private SwipeBackLayout swipeBackLayout;
    private SearchNews.SearNewList news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sb_searchnese);
        ButterKnife.bind(this);
        ShareSDK.initSDK(this);

        initview();
        initEvent();
        initData();
    }


    private void initview() {

        swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "回到顶端", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "爱分享", Snackbar.LENGTH_SHORT)
                        .setAction("分享", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Toast.makeText(getApplicationContext(), "可以分享哇", Toast
//                                        .LENGTH_SHORT).show();
                                share();
                            }
                        }).setActionTextColor(getColor(R.color.colorPrimary))
                        .show();


            }
        });
    }
    private void share() {


        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(news.getTitle());
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(news.getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(news.getTitle());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(news.getUrl());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("不发表意见");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("www.baidu,com");

        // 启动分享GUI
        oks.show(this);
    }
    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void initData() {

        news = (SearchNews.SearNewList) getIntent().getSerializableExtra
                ("searchnews");

        collapsingToolbarLayout.setTitle(news.getTitle());
        simpleDraweeView.setImageURI(news.getImg());
//        Log.e("详情网页", news.getUrl());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);// 支持双击缩放

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.loadUrl(news.getUrl());
    }

}
