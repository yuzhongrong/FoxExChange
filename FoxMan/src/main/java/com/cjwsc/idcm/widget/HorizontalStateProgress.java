package com.cjwsc.idcm.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.Utils.DensityUtil;
import com.cjwsc.idcm.Utils.ScreenUtil;
import com.cjwsc.idcm.base.application.BaseApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzhongrong on 2017/8/24.
 *
 * 思路：先根据分辨率测量宽度 然后把宽度平分4份，然后开始画5个大圆 ，再画每个大圆下的文字和右边小圆
 */

public class HorizontalStateProgress extends View {


    private Context mContext;


    /**
     * 圆的属性定义
     */
    //大圆画笔
    Paint bigcirclepaint;
    Paint bigcirclepaint_stroke;
    //小圆画笔
    Paint smallcirclepaint;

    //文字画笔
    Paint textpaint;

    //实心圆颜色
    int bigcirclepaintColor;
    //空心圆颜色
   int  bigcirclepaint_stroke_Color;
    //小圆颜色
   int  smallcirclepaint_Color;

    /**
     * 圆的属性定义
     */

    //2个园之间的的距离
    private int dx;//默认圆之间的距离
    private int bigradius;//默认大圆圆半径
    private int smallradius;//默认小圆半径

    //开始圆心x和y坐标
    private int startx;//默认xy起始点位置

    private int starty=100;

    private int realy;//大圆和大圆之间的圆心距


    /**
     * 文字属性
     */

    //文字和圆之间的距离
    private int txttocircle=100;
    private  int textsize=45;
    private int textcolor=getResources().getColor(R.color.color_cccccc);//默认灰色



    /**
     * 屏幕属性 不可更改
     */
    private int mScreenWidth;
    private int mWidth;
    private int mHeight;


    //String[] str1 = new String[]{"已提交","审核申请","正在处理","退款审核","退货完成"};
  //  List<String> status=Arrays.asList(str1);

    List<String> status= new ArrayList<>();

    public HorizontalStateProgress(Context context) {

        this(context,null);

    }


    public HorizontalStateProgress(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);

    }

    public HorizontalStateProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {


        super(context, attrs, defStyleAttr);

        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.stateprogress);
        //实心大圆颜色
        bigcirclepaintColor=array.getColor(R.styleable.stateprogress_bigcirclepaintColor,
                getResources().getColor(R.color.color_e88272));

        //空心大圆颜色

        bigcirclepaint_stroke_Color=array.getColor(R.styleable.stateprogress_bigcirclepaint_stroke_Color,
                getResources().getColor(R.color.color_cccccc));


        //小圆颜色
        smallcirclepaint_Color=array.getColor(R.styleable.stateprogress_bigcirclepaint_stroke_Color,
                getResources().getColor(R.color.color_cccccc));

        dx=array.getDimensionPixelSize(R.styleable.stateprogress_dx, 30);

        //大圆半径 30，23，6 这些原来是这个viwe写死的px值
        bigradius=array.getDimensionPixelSize(R.styleable.stateprogress_bigradius,23);


        //小圆半径
        smallradius=array.getDimensionPixelSize(R.styleable.stateprogress_smallradius,6);

       //view的起始x，y
        startx=array.getDimensionPixelSize(R.styleable.stateprogress_startx,100);

        starty=array.getDimensionPixelSize(R.styleable.stateprogress_starty,100);


        //文字到圆的距离
        txttocircle=array.getDimensionPixelSize(R.styleable.stateprogress_txttocircle,100);

        textsize=array.getDimensionPixelSize(R.styleable.stateprogress_textsize,45);

        array.recycle();
        initPaint();
    }



    private void initPaint() {
        //初始化大画笔,空心不是填充色的画笔
        bigcirclepaint=new Paint();
        bigcirclepaint.setAntiAlias(true);
        bigcirclepaint.setStyle(Paint.Style.FILL);//填充
        bigcirclepaint.setColor(bigcirclepaintColor);

        bigcirclepaint_stroke=new Paint();
        bigcirclepaint_stroke.setColor(bigcirclepaint_stroke_Color);
        bigcirclepaint_stroke.setAntiAlias(true);
        bigcirclepaint_stroke.setStyle(Paint.Style.STROKE);
        bigcirclepaint_stroke.setStrokeWidth(5);
        //初始化小圆画笔
        smallcirclepaint=new Paint();
        smallcirclepaint.setAntiAlias(true);
        smallcirclepaint.setStyle(Paint.Style.FILL);
        smallcirclepaint.setColor(smallcirclepaint_Color);

        //初始化字体画笔
        textpaint=new Paint();
        textpaint.setAntiAlias(true);
        textpaint.setColor(textcolor);
        textpaint.setTextSize(textsize);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        mScreenWidth = ScreenUtil.getScreenWidth(BaseApplication.getContext());
        if(heightMode==MeasureSpec.EXACTLY){//如果是精确值或者填充内容

            mHeight=heightSize;
        } else {
                mHeight = DensityUtil.dip2px(BaseApplication.getContext(), 70);

            }



         if(widthMode==MeasureSpec.EXACTLY){
             mWidth=widthSize;

         } else{

             mWidth=mScreenWidth;
         }

        setMeasuredDimension(mWidth, mHeight);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if(status.size()>0){
            //画大圆

            this.setVisibility(VISIBLE);
            DrawBigCircle(canvas);
        }else{

            this.setVisibility(GONE);
        }





    }

    private void DrawBigCircle(Canvas canvas) {


        textpaint.setColor(getResources().getColor(R.color.color_cccccc));//默认开始为灰色

        realy=mWidth-(2*(bigradius+startx));

        int bigdx=realy/4;//每个大圆之间的圆心距离

        //画大圆 和文字

        for(int i=0;i<status.size();i++){


            if(i==0){//第一个圆逻辑

                    if(status.size()==1){
                        //默认文字为黑色 只有当前状态文字为选中色
                        textpaint.setColor(getResources().getColor(R.color.color_333333));
                        canvas.drawCircle(startx,starty,bigradius,bigcirclepaint);//实心大圆
                        canvas.drawText(status.get(i),startx-(getTextWidth(textpaint,status.get(i))/2),
                                starty+bigradius+txttocircle,textpaint);

                    }else{
                        canvas.drawCircle(startx,starty,bigradius,bigcirclepaint_stroke);//空心大圆
                        canvas.drawText(status.get(i),startx-(getTextWidth(textpaint,status.get(i))/2),
                                starty+bigradius+txttocircle,textpaint);

                        //画小圆
                        DrawSmallCircle(canvas,i,startx+bigradius+dx+smallradius);
                    }




            }
            else if(i==status.size()-1){

                //默认文字为黑色 只有当前状态文字为选中色
                textpaint.setColor(getResources().getColor(R.color.color_333333));
                canvas.drawCircle(startx+i*bigdx,starty,bigradius,bigcirclepaint);//实心大圆

                canvas.drawText(status.get(i),(startx+i*bigdx)-(getTextWidth(textpaint,status.get(i))/2), starty+bigradius+txttocircle,textpaint);

            }
            else{


                //默认文字为黑色 只有当前状态文字为选中色
                textpaint.setColor(getResources().getColor(R.color.color_cccccc));
                canvas.drawCircle(startx+i*bigdx,starty,bigradius,bigcirclepaint_stroke);
                canvas.drawText(status.get(i),(startx+i*bigdx)-(getTextWidth(textpaint,status.get(i))/2),
                        starty+bigradius+txttocircle,textpaint);


                DrawSmallCircle(canvas,i,startx+i*bigdx+bigradius+dx+smallradius);


            }




        }


    }

    private void DrawSmallCircle(Canvas canvas,int index,int start) {
        int smallincrement=dx+smallradius;//画下个小圆的x坐标的增量


            canvas.drawCircle(start+0*smallincrement,starty,smallradius,smallcirclepaint);

            canvas.drawCircle(start+1*smallincrement,starty,smallradius,smallcirclepaint);

            canvas.drawCircle(start+2*smallincrement,starty,smallradius,smallcirclepaint);

            canvas.drawCircle(start+3*smallincrement,starty,smallradius,smallcirclepaint);
            canvas.drawCircle(start+4*smallincrement,starty,smallradius,smallcirclepaint);

            canvas.drawCircle(start+5*smallincrement,starty,smallradius,smallcirclepaint);






    }

    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }



    public void setDatas(List<String> datas){

        this.status=datas;
        invalidate();


    }


}
