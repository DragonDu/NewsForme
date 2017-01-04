package com.du.newsforme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.du.newsforme.adapter.SearchNewsAdapter;
import com.du.newsforme.base.BaseApplication;
import com.du.newsforme.bean.SearchNews;
import com.du.newsforme.commons.Contant;
import com.du.newsforme.fragment.decoration.CardViewtemDecortion;
import com.du.newsforme.utils.OKGoUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/28.
 */

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.tb_search)
    Toolbar toolbar;
    @BindView(R.id.rl_newsearch)
    RecyclerView recyclerView;


    private String query;
    private SearchNewsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        query = getIntent().getExtras().getString("query");
        toolbar.setTitle("关于:" + query);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initEvent();
        initData();


    }

    private void initEvent() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        refreshLayout.setIsOverLay(false);
//        refreshLayout.setWaveShow(false);
//        refreshLayout.setLoadMore(true);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new CardViewtemDecortion());
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new SearchNewsAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnSearchNewsClicklistenner(new SearchNewsAdapter.onSearchNewsClicklistenner() {

            @Override
            public void onClick(SearchNews.SearNewList item) {
                Log.e("搜索新闻item", item.toString());
                Intent intent = new Intent(BaseApplication.getInstance(), Mysearchnews.class);
                intent.putExtra("searchnews", item);
                startActivity(intent);
            }
        });
    }

    private void initData() {


        OKGoUtils.search(Contant.SEARCHHOST, query, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                SearchNews news = gson.fromJson(s, SearchNews.class);

                adapter.addData(news.getResult());
            }
        });


    }
}
