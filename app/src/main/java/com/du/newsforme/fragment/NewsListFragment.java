package com.du.newsforme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.du.newsforme.MainActivity;
import com.du.newsforme.MyBackActivity;
import com.du.newsforme.R;
import com.du.newsforme.adapter.MyNewsadapter;
import com.du.newsforme.base.BaseFragment;
import com.du.newsforme.bean.News;
import com.du.newsforme.commons.Contant;
import com.du.newsforme.fragment.decoration.DividerGridItemDecoration;
import com.du.newsforme.utils.OKGoUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.lzy.okgo.utils.OkLogger.tag;

/**
 * Created by Administrator on 2016/10/25.
 */

public class NewsListFragment extends BaseFragment {

    @BindView(R.id.materia_refresh_widget)
    MaterialRefreshLayout materialRefreshLayout;
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    List list = new ArrayList();
    private MyNewsadapter newsadapter;
    boolean isLoadMore = true;
    private News news;
    String type1;
    private LinearLayoutManager layoutManager;

    public static NewsListFragment newInstance(String type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reachTOTop(MainActivity.NewsEvent event) {
        int start = layoutManager.findFirstVisibleItemPosition();
        if (start == 0) {

        } else {
            recyclerView.smoothScrollToPosition(0);
//        Toast.makeText(mContext, "我收到喽收到喽" +
//                "！", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int initResource() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void initEvent() {

        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(false);
        materialRefreshLayout.setLoadMore(isLoadMore);

        //设置布局管理器
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        //设置分割线
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        //使RecyclerView保持固定的大小，该信息被用于自身的优化。
        recyclerView.setHasFixedSize(true);
        //设置item增加或删除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator() {
        });
//        getData();
        newsadapter = new MyNewsadapter(list);
        recyclerView.setAdapter(newsadapter);
        newsadapter.setOnItemClikListenner(new MyNewsadapter.onItemClickListenner() {
            @Override
            public void geini(View v, int position, News.Newslist.Newslistdetail news) {

                Intent intent = new Intent(mContext, MyBackActivity.class);
                intent.putExtra("news", news);
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.goin, R.anim.goout);
//                View transitionView = v.findViewById(R.id.ivNews);
//                ActivityOptionsCompat options =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                                transitionView, getString(R.string.transition_news_img));
//
//                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }


        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == newsadapter.getItemCount()) {
                    //加载更多
//                    Toast.makeText(getContext(), "没有了哦，可以试试上拉刷新哈", Toast.LENGTH_SHORT).show();
                    Snackbar.make(getView(), "没有了哦，可以试试上啦刷新哈", Snackbar.LENGTH_SHORT).setAction
                            ("走起", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //上啦刷新
                                    materialRefreshLayout.autoRefreshLoadMore();
                                }
                            }).setActionTextColor(getActivity().getColor(R.color.colorPrimary))
                            .show();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }
        });
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        getData();
//                        newsadapter.notifyDataSetChanged();
//                        materialRefreshLayout.finishRefresh();
//                    }
//                }, 2000);
                list = null;
                refershtype = "1";
                requestNews();


            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        isLoadMore = false;
//
//                        //通知刷新
//                        newsadapter.addData(newsadapter.getData().size(), list);
//                        //mRecyclerView.scrollToPosition(mAdapter.getLists().size());
//                        /**
//                         * 完成加载数据后，调用此方法，要不然刷新的效果不会消失
//                         */
//                        materialRefreshLayout.finishRefreshLoadMore();
//                    }
//                }, 3000);
                refershtype = "2";
                requestNews();

            }


        });

    }

    @Override
    public void initdata() {
        type1 = this.getArguments().getString("type");
        String url = "http://wthrcdn.etouch.cn/weather_mini?city=";
        String tag = "xiaohua";

        requestNews();
    }

    String refershtype = null;

    private void requestNews() {
        OKGoUtils.get(Contant.HOSTNAME, tag, type1, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                news = gson.fromJson(s, News.class);
                list = news.getResult().getData();

                if (refershtype != null && refershtype == "1") {
                    newsadapter.setData(list);
                    newsadapter.notifyDataSetChanged();
                    materialRefreshLayout.finishRefresh();

                } else if (refershtype != null && refershtype == "2") {
                    newsadapter.addData(0, list);
                    newsadapter.notifyDataSetChanged();
                    materialRefreshLayout.finishRefreshLoadMore();
                } else {
                    newsadapter.setData(list);
                }

                refershtype = null;

            }
        });

    }
}
