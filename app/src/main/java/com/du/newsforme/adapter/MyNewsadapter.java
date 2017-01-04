package com.du.newsforme.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.du.newsforme.R;
import com.du.newsforme.bean.News;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView 的 Adapter
 * Created by Administrator on 2016/10/25.
 */

public class MyNewsadapter extends RecyclerView.Adapter<MyNewsadapter.MyViewHolder> {


    private List<News.Newslist.Newslistdetail> list;
    onItemClickListenner listenner;

    public MyNewsadapter(List<News.Newslist.Newslistdetail> list) {
        this.list = list;
    }

    //让外部调用来达到设置监听目的
    public void setOnItemClikListenner(onItemClickListenner listenner) {
        this.listenner = listenner;
    }

    public void setData(List<News.Newslist.Newslistdetail> addlist) {
        list = addlist;
        notifyDataSetChanged();

    }

    public void addData(int index, List<News.Newslist.Newslistdetail> addlist) {

        list.addAll(index, addlist);

        notifyDataSetChanged();
    }


    public List<News.Newslist.Newslistdetail> getData() {
        return list;
    }



    /**
     * 返回一个自定义的viewholder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_newlist, parent, false);//要写viewgroup 和false
        // 否则会出现recyclerview宽度显示不全

        return new MyViewHolder(view);
    }

    /**
     * 填充onCreateViewHolder方法返回的holder中的控件,即对view进行数据填充
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        if (list != null) {
            News.Newslist.Newslistdetail newslistdetail = list.get(position);
            holder.tvTitle.setText(newslistdetail.getTitle());
            holder.tvDesc.setText(newslistdetail.getAuthor_name());
            holder.imageView.setImageURI(newslistdetail.getThumbnail_pic_s());
//            Log.e("tupianjiazai","zenmejiazaide");
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivNews)
        SimpleDraweeView imageView;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDesc)
        TextView tvDesc;

//        private TextView tv;

        MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//             tv =  (TextView) itemView.findViewById(R.id.tv_rv_newslist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listenner != null) {
                        listenner.geini(itemView, getLayoutPosition(), list.get
                                (getLayoutPosition()));
                    }
                }
            });
        }
    }

    //设置监听
    public interface onItemClickListenner {
        //用来暴露给调用者使用的参数
        void geini(View v, int position, News.Newslist.Newslistdetail news);
    }
}


