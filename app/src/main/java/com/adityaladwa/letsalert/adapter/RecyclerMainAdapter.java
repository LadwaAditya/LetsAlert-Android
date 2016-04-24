package com.adityaladwa.letsalert.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adityaladwa.letsalert.R;
import com.adityaladwa.letsalert.api.model.EventList;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Aditya on 24-Apr-16.
 */
public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.ReviewViewHolder> {

    private static final String LOG_TAG = RecyclerMainAdapter.class.getSimpleName();

    private ArrayList<EventList.Event> mEventList;
    private Context mContext;

    public RecyclerMainAdapter(ArrayList<EventList.Event> mEventList, Context mContext) {
        this.mEventList = mEventList;
        this.mContext = mContext;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_event, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {

        holder.txTitle.setText(mEventList.get(position).getName());
        holder.tvDEsc.setText(mEventList.get(position).getDescription());

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


        public ReviewViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }


    }
}

