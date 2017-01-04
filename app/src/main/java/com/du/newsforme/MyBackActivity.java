package com.du.newsforme;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.du.newsforme.bean.News;
import com.du.newsforme.utils.OKGoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lzy.okgo.callback.StringCallback;
import com.mob.commons.SHARESDK;

import org.greenrobot.eventbus.EventBus;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import okhttp3.Call;
import okhttp3.Response;

import static com.du.newsforme.R.id.fab;

/**
 * Created by Administrator on 2016/10/27.
 */

public class MyBackActivity extends SwipeBackActivity {
    @BindView(R.id.iv_News)
    SimpleDraweeView imageView;
    @BindView(R.id.tb_newsd)
    Toolbar toolbar;
    @BindView(R.id.probar)
    ProgressBar progressBar;
    @BindView(R.id.html_text)
    HtmlTextView htmlTextView;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.fab_share)
    FloatingActionButton sharebutton;

    private SwipeBackLayout swipeBackLayout;
    private News.Newslist.Newslistdetail news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.newsdetail);
        ButterKnife.bind(this);
        ShareSDK.initSDK(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initview();
        initEvent();

        initData();
    }


    private void initview() {

        swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        sharebutton.setOnClickListener(new View.OnClickListener() {
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

        news = (News.Newslist.Newslistdetail) getIntent()
                .getSerializableExtra("news");
        toolbarLayout.setTitle(news.getTitle());
        imageView.setImageURI(news.getThumbnail_pic_s());
        Log.e("详情网页", news.getUrl());
        OKGoUtils.get(news.getUrl(), "newsde", null, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e("返回的s值", s);
//                htmlTextView.setHtmlFromString(s,false);
//                htmlTextView.setHtmlFromString(s, new HtmlTextView.LocalImageGetter());

                htmlTextView.setHtml(s, new HtmlHttpImageGetter(htmlTextView));
                progressBar.setVisibility(View.GONE);
            }
        });

    }

}
