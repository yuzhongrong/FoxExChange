package com.android.com.kline.widgets.kline;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.com.kline.widgets.beans.DataParse;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.orhanobut.logger.Logger;


/**
 * Created by loro on 2017/2/8.
 */
public class MyCombinedChart extends CombinedChart {
    private MyLeftMarkerView myMarkerViewLeft;
    private MyHMarkerView myMarkerViewH;
    private MyBottomMarkerView myBottomMarkerView;
    private DataParse minuteHelper;

    public MyCombinedChart(Context context) {
        super(context);
    }

    public MyCombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCombinedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setMarker(MyLeftMarkerView markerLeft, MyHMarkerView markerH, DataParse minuteHelper) {
        myMarkerViewLeft = markerLeft;
        myMarkerViewH = markerH;
        this.minuteHelper = minuteHelper;
    }

    public void setMarker(MyLeftMarkerView markerLeft, MyBottomMarkerView markerBottom, DataParse minuteHelper) {
        myMarkerViewLeft = markerLeft;
        myBottomMarkerView = markerBottom;
        minuteHelper = minuteHelper;
    }

    public void setMarker(MyLeftMarkerView markerLeft, MyBottomMarkerView markerBottom, MyHMarkerView markerH, DataParse minuteHelper) {
        myMarkerViewLeft = markerLeft;
        myBottomMarkerView = markerBottom;
        myMarkerViewH = markerH;
        this.minuteHelper = minuteHelper;
    }

    @Override
    protected void drawMarkers(Canvas canvas) {
        if (!mDrawMarkerViews || !valuesToHighlight())
            return;
        for (int i = 0; i < mIndicesToHighlight.length; i++) {
            Highlight highlight = mIndicesToHighlight[i];
            int xIndex = mIndicesToHighlight[i].getXIndex();
            int dataSetIndex = mIndicesToHighlight[i].getDataSetIndex();
            float deltaX = mXAxis != null
                    ? mXAxis.mAxisRange
                    : ((mData == null ? 0.f : mData.getXValCount()) - 1.f);
            if (xIndex <= deltaX && xIndex <= deltaX * mAnimator.getPhaseX()) {
                Entry e = mData.getEntryForHighlight(mIndicesToHighlight[i]);
                // make sure entry not null
                if (e == null || e.getXIndex() != mIndicesToHighlight[i].getXIndex())
                    continue;
                float[] pos = getMarkerPosition(e, highlight);
                // check bounds
                if (!mViewPortHandler.isInBounds(pos[0], pos[1]))
                    continue;

                if (null != myMarkerViewH) {
                    myMarkerViewH.refreshContent(e, mIndicesToHighlight[i]);
                    int width = (int) mViewPortHandler.contentWidth();
                    myMarkerViewH.setTvWidth(width);
                    myMarkerViewH.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    myMarkerViewH.layout(0, 0, width,
                            myMarkerViewH.getMeasuredHeight());
                    myMarkerViewH.draw(canvas, mViewPortHandler.contentLeft(), mIndicesToHighlight[i].getTouchY() - myMarkerViewH.getHeight() / 2);
                }

                if (null != myMarkerViewLeft) {
                    //修改标记值
                    float yValForHighlight = mIndicesToHighlight[i].getTouchYValue();
                    myMarkerViewLeft.setData(yValForHighlight);

                    myMarkerViewLeft.refreshContent(e, mIndicesToHighlight[i]);

                    myMarkerViewLeft.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    myMarkerViewLeft.layout(0, 0, myMarkerViewLeft.getMeasuredWidth(),
                            myMarkerViewLeft.getMeasuredHeight());

                    myMarkerViewLeft.draw(canvas, mViewPortHandler.contentLeft(), mIndicesToHighlight[i].getTouchY() - myMarkerViewLeft.getHeight() / 2);

                }

                if (null != myBottomMarkerView) {
                    String time = minuteHelper.getKLineDatas().get(mIndicesToHighlight[i].getXIndex()).date;
                    myBottomMarkerView.setData(time);
                    myBottomMarkerView.refreshContent(e, mIndicesToHighlight[i]);

                    myBottomMarkerView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    myBottomMarkerView.layout(0, 0, myBottomMarkerView.getMeasuredWidth(),
                            myBottomMarkerView.getMeasuredHeight());

                    myBottomMarkerView.draw(canvas, pos[0] - myBottomMarkerView.getWidth() / 2, mViewPortHandler.contentBottom());
                }


            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

              //  getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_MOVE:

                Logger.d("------------isHighlightEnabled()-------------->"+isDragDecelerationEnabled());
                break;
            case MotionEvent.ACTION_UP:
           //     getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        return super.onTouchEvent(event);
    }
}
