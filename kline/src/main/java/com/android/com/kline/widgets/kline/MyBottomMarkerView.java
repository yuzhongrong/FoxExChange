package com.android.com.kline.widgets.kline;

import android.content.Context;
import android.widget.TextView;

import com.android.com.kline.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

/**
 * Created by loro on 2017/2/8.
 */
public class MyBottomMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    private TextView markerTv;
    private String time;
    public MyBottomMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        markerTv = findViewById(R.id.marker_tv1);
        markerTv.setTextSize(10);
    }

    public void setData(String time){

        this.time=time;
    }
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        markerTv.setText(time);
    }

    @Override
    public int getXOffset(float xpos) {
        return 0;
    }

    @Override
    public int getYOffset(float ypos) {
        return 0;
    }
}
