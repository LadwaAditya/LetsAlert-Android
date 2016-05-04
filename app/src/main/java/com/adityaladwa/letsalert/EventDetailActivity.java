package com.adityaladwa.letsalert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.adityaladwa.letsalert.api.model.MyEvent;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar_event_detail)
    Toolbar toolbar;

    @Bind(R.id.event_detail)
    TextView textViewEventDetail;

    @Bind(R.id.toolbar_image_backdrop)
    ImageView backdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);

        MyEvent event = getIntent().getParcelableExtra("extra_event");
        toolbar.setTitle(event.getName());
        textViewEventDetail.setText(event.getDescription());

        switch (event.getUser().getDepartment()) {
            case "police":
                Glide.with(this).load(R.drawable.police).crossFade().into(backdrop);
                break;
            case "water":
                Glide.with(this).load(R.drawable.water).crossFade().into(backdrop);
                break;
            case "electricity":
                Glide.with(this).load(R.drawable.electricity2).crossFade().into(backdrop);
                break;
            case "college":
                Glide.with(this).load(R.drawable.college).crossFade().into(backdrop);
                break;

        }

        Glide.with(this).load(R.drawable.police).crossFade().into(backdrop);
    }
}
