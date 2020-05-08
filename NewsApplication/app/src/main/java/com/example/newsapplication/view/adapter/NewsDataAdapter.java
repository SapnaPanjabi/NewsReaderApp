package com.example.newsapplication.view.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsapplication.R;
import com.example.newsapplication.service.model.NewsData;

public class NewsDataAdapter extends PagedListAdapter<NewsData, NewsDataAdapter.NewsDataViewHolder> {

    Context context;
    OnNewsClickListener mOnNewsClickListener;
    private static DiffUtil.ItemCallback<NewsData> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<NewsData>() {
                @Override
                public boolean areItemsTheSame(NewsData oldItem, NewsData newItem) {
                    return oldItem.getNewsContent() == newItem.getNewsContent();
                }

                @Override
                public boolean areContentsTheSame(NewsData oldItem, NewsData newItem) {
                    return oldItem.getNewsContent().equals(newItem.getNewsContent());
                }
            };

    public NewsDataAdapter(Context mContext, OnNewsClickListener mOnNewsClickListener) {
        super(DIFF_CALLBACK);
        context = mContext;
        this.mOnNewsClickListener = mOnNewsClickListener;
    }


    protected NewsDataAdapter(@NonNull DiffUtil.ItemCallback<NewsData> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public NewsDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsDataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tupple_card, parent, false), mOnNewsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsDataViewHolder holder, int position) {
        holder.onBind(position);
    }


    public interface OnNewsClickListener {
        void onNewsClick(NewsData newsData);
    }

    class NewsDataViewHolder extends BaseViewHolder {
        OnNewsClickListener onNewsClickListener;
        ImageView imgHeadline;
        TextView tvTitle;
        NewsData newsData;

        public NewsDataViewHolder(View itemView, final OnNewsClickListener onNewsClickListener) {
            super(itemView);
            imgHeadline = itemView.findViewById(R.id.img_headline);
            tvTitle = itemView.findViewById(R.id.title_headline);
            this.onNewsClickListener = onNewsClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNewsClickListener.onNewsClick(getItem(getAdapterPosition()));
                }
            });
        }

        @Override
        protected void clear() {
            imgHeadline.setImageDrawable(null);
            tvTitle.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final NewsData mNewsData = getItem(position);

            if (mNewsData.getNewsImgURL() != null) {
                // imgHeadline.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_file_upload_black_24dp));
                Glide.with(itemView.getContext())
                        .load(mNewsData.getNewsImgURL())
                        //.load("https://static-news.moneycontrol.com/static-mcnews/2019/06/RESERVE-bank-of-India-770x433.jpg")
                        .apply(new RequestOptions().override(850, 350).placeholder(R.drawable.ic_file_download_black_24dp).optionalFitCenter())
                        .into(imgHeadline);
            }

            if (mNewsData.getNewsTitle() != null) {
                tvTitle.setText(mNewsData.getNewsTitle());
            }
        }

    }
}