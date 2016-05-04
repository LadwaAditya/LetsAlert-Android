package com.adityaladwa.letsalert.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adityaladwa.letsalert.R;
import com.adityaladwa.letsalert.api.model.MyEvent;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Aditya on 24-Apr-16.
 */
public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.ReviewViewHolder> {

    private static final String LOG_TAG = RecyclerMainAdapter.class.getSimpleName();

    private ArrayList<MyEvent> mEventList;
    private OnItemClickListener mlistener;
    private Context mContext;

    public RecyclerMainAdapter(ArrayList<MyEvent> mEventList, OnItemClickListener listener, Context context) {
        this.mEventList = mEventList;
        this.mlistener = listener;
        this.mContext = context;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_event, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bind(mEventList.get(position), mlistener);
    }

    @Override
    public int getItemCount() {
        if (mEventList != null)
            return mEventList.size();
        else
            return 0;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textview_event_title)
        TextView txTitle;
        @Bind(R.id.textview_event_description)
        TextView tvDEsc;
        @Bind(R.id.imageview_event_image)
        ImageView imageView;


        public ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final MyEvent item, final RecyclerMainAdapter.OnItemClickListener listener) {
            txTitle.setText(item.getName());
            tvDEsc.setText(item.getDescription());

            switch (item.getUser().getDepartment()) {
                case "police":
                    Glide.with(mContext).load(R.drawable.police).crossFade().into(imageView);
                    break;
                case "water":
                    Glide.with(mContext).load(R.drawable.water2).crossFade().into(imageView);
                    break;
                case "electricity":
                    Glide.with(mContext).load(R.drawable.electricity).crossFade().into(imageView);
                    break;
                case "college":
                    Glide.with(mContext).load(R.drawable.college).crossFade().into(imageView);
                    break;

            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mlistener.onItemClick(item);
                }
            });
        }


    }

    public interface OnItemClickListener {
        void onItemClick(MyEvent item);
    }
}

