package com.adityaladwa.letsalert;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adityaladwa.letsalert.adapter.RecyclerMainAdapter;
import com.adityaladwa.letsalert.api.EndPoint;
import com.adityaladwa.letsalert.api.model.EventList;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Aditya on 24-Apr-16.
 */
public class MainFragment extends Fragment {
    public static final String TAG = MainFragment.class.getSimpleName();

    private RecyclerMainAdapter mainAdapter;
    private ArrayList<EventList.Event> mEvents;
    private LinearLayoutManager linearLayoutManager;

    @Bind(R.id.recycler_view_main)
    RecyclerView mRecyclerView;

    @Inject
    Retrofit retrofit;

    @Bind(R.id.smooth_progress)
    SmoothProgressBar smoothProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Inflated Fragment layout");
        View view = inflater.inflate(R.layout.content_main, container, false);
        ButterKnife.bind(this, view);

        ((App) getActivity().getApplication()).getNetComponent().inject(this);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        callapi();
        return view;
    }

    private void callapi() {

        retrofit.create(EndPoint.class).getEventsRx().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ArrayList<EventList.Event>>() {
                    @Override
                    public void onCompleted() {
                        smoothProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage(), e);
                    }

                    @Override
                    public void onNext(ArrayList<EventList.Event> events) {
                        mainAdapter = new RecyclerMainAdapter(events, getActivity());
                        mainAdapter.notifyDataSetChanged();
                        mRecyclerView.setAdapter(mainAdapter);
                        Log.d(TAG, events.get(0).getName());
                    }
                });
    }


}



