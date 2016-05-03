package com.adityaladwa.letsalert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.adityaladwa.letsalert.api.model.EventList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar_event_detail)
    Toolbar toolbar;

    @Bind(R.id.event_detail)
    TextView textViewEventDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);

        EventList.Event event = getIntent().getParcelableExtra("extra_event");
        toolbar.setTitle(event.getName());
        textViewEventDetail.setText(event.getDescription());
    }
}
