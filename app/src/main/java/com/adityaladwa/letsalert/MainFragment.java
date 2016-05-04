package com.adityaladwa.letsalert;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adityaladwa.letsalert.adapter.RecyclerMainAdapter;
import com.adityaladwa.letsalert.api.EndPoint;
import com.adityaladwa.letsalert.api.model.MyEvent;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Aditya on 24-Apr-16.
 */
public class MainFragment extends Fragment implements RecyclerMainAdapter.OnItemClickListener {
    public static final String TAG = MainFragment.class.getSimpleName();

    private RecyclerMainAdapter mainAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Subscription mainSubscription;
    private String type;

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

        type = getArguments().getString(getString(R.string.bundle_fragment));
        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        callapi(type);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        callapi(type);
    }

    private void callapi(final String type) {

        Observer<ArrayList<MyEvent>> observer = new Observer<ArrayList<MyEvent>>() {
            @Override
            public void onCompleted() {
                smoothProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.getMessage(), e);
            }

            @Override
            public void onNext(ArrayList<MyEvent> events) {
                if (events.size() == 0) {
                    smoothProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "No events in this category", Toast.LENGTH_SHORT).show();
                } else {

                    mainAdapter = new RecyclerMainAdapter(events, MainFragment.this);
                    mainAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(mainAdapter);
                    Log.d(TAG, events.get(0).getName());
                }
            }
        };

        if (type == getString(R.string.bundle_main))
            mainSubscription = retrofit.create(EndPoint.class).getEventsRx().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(observer);
        else if (type == getString(R.string.bundle_police))
            retrofit.create(EndPoint.class).getPoliceEventsRx().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(observer);
        else if (type == getString(R.string.bundle_electricity))
            retrofit.create(EndPoint.class).getElectricityEventsRx().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(observer);
        else if (type == getString(R.string.bundle_water))
            retrofit.create(EndPoint.class).getWaterEventsRx().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(observer);
        else if (type == getString(R.string.bundle_college))
            retrofit.create(EndPoint.class).getCollegeEventsRx().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(observer);


    }


    @Override
    public void onStop() {
        super.onStop();
        if (mainSubscription != null)
            mainSubscription.unsubscribe();
    }

    @Override
    public void onItemClick(MyEvent item) {
        Intent intent = new Intent(getActivity(), EventDetailActivity.class);
        intent.putExtra("extra_event", item);
        startActivity(intent);
    }
}



