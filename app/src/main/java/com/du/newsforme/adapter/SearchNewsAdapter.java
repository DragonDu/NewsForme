package com.du.newsforme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.du.newsforme.R;
import com.du.newsforme.bean.SearchNews;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Administrator on 2016/10/29.
 */

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter
        .Mysearchviewholder> {
    private List<SearchNews.SearNewList> list;
    onSearchNewsClicklistenner clicklistenner;


    public void addData(List<SearchNews.SearNewList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnSearchNewsClicklistenner(onSearchNewsClicklistenner clicklistenner) {
        this.clicklistenner = clicklistenner;

    }


    @Override
    public Mysearchviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerview_search, parent, false);
        return new Mysearchviewholder(view);
    }

    @Override
    public void onBindViewHolder(Mysearchviewholder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getPdate_src());
        holder.img.setImageURI(list.get(position).getImg());
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Mysearchviewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdv_img_search)
        SimpleDraweeView img;
        @BindView(R.id.tv_searchtitle)
        TextView title;
        @BindView(R.id.tv_searchdate)
        TextView date;

        public Mysearchviewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clicklistenner != null) {
                        clicklistenner.onClick(list.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    public interface onSearchNewsClicklistenner {
        void onClick(SearchNews.SearNewList item);
    }
}
