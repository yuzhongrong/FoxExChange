package com.cjwsc.idcm.widget.edittext;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cjwsc.idcm.R;
import com.cjwsc.idcm.Utils.ScreenUtil;
import com.cjwsc.idcm.model.bean.CountryCodeBean;

import java.util.ArrayList;
import java.util.List;


public class SelectCountry extends LinearLayout implements OnClickListener, View.OnTouchListener

{
    EditText et = null;
    ImageView image = null;
    PopupWindow selectPopupWindow = null;
    LayoutInflater inflater = null;


    public interface OnSelectedClickListener {
        public void OnSelected(Object sender, int position, ArrayList<CountryCodeBean.DataBeanX.DataBean> data);
    }

    private OnSelectedClickListener onSelectedClickListener = null;

    public OnSelectedClickListener getOnSelectedClickListener() {
        return onSelectedClickListener;
    }

    public void setOnSelectedClickListener(OnSelectedClickListener listener) {
        this.onSelectedClickListener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (null == selectPopupWindow && getWidth() > 0) {
            View loginwindow = (View) inflater.inflate(R.layout.options_country, null);
            ListView listView = (ListView) loginwindow.findViewById(R.id.list);
            //设置自定义Adapter
            OptionsAdapter optionsAdapter = new OptionsAdapter(getContext());
            if (null != listView) {
                listView.setAdapter(optionsAdapter);
                listView.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long arg3) {
                        onSelect(position);
                        CountryCodeBean.DataBeanX.DataBean dataBean = list.get(position);
                        et.setText(dataBean.getName());
                        et.setSelection(dataBean.getName().length());

                        selectPopupWindow.dismiss();
                    }
                });
            }
            selectPopupWindow = new PopupWindow(loginwindow, getWidth(), LayoutParams.WRAP_CONTENT, true);
            selectPopupWindow.setOutsideTouchable(true);
            selectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    image.setImageResource(R.drawable.item_down);
                }
            });
            selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public SelectCountry(Context context) {
        this(context, null);
    }

    public SelectCountry(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_baseweplus, this);
        //et = (EditText) findViewById(R.id.edittext);
        //image = (ImageView) findViewById(R.id.btn_select);
        if (null != image) image.setOnClickListener(this);
// 		if(null != et) et.setOnClickListener(this);
        if (null != image) image.setOnTouchListener(this);
//		if(null != et) et.setOnTouchListener(this);
    }

    public SelectCountry(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (list.size() > 0) {
                    image.setImageResource(R.drawable.item_up);
                    selectPopupWindow.showAsDropDown(this, 0, -ScreenUtil.dp2px(-15, getContext()));
                }
                break;
        }
        return false;
    }

    @Override
    public void onClick(View arg0) {
        selectPopupWindow.showAsDropDown(this, 0, -ScreenUtil.dp2px(-15, getContext()));
    }

    private ArrayList<CountryCodeBean.DataBeanX.DataBean> list = new ArrayList<CountryCodeBean.DataBeanX.DataBean>();

    public void setBindData(List<CountryCodeBean.DataBeanX.DataBean> data) {
        list.clear();
        list.addAll(data);
        if (list.size() > 1) {
            CountryCodeBean.DataBeanX.DataBean dataBean = list.get(0);
            String name = dataBean.getName();
            et.setText(name);
            if (!TextUtils.isEmpty(name)){

                et.setSelection(name.length());
            }
            onSelect(0);
        }
    }

    private int selectIndex = 0;

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setHint(String string) {
        et.setHint(string);
    }

    void onSelect(int selectIndex) {
        this.selectIndex = selectIndex;

        image.setImageResource(R.drawable.item_down);
        if (null != onSelectedClickListener)
            onSelectedClickListener.OnSelected(this, selectIndex, list);
    }

    public void setSelectItem(String data) {
        for (int i = 0; i < list.size(); i++) {
            CountryCodeBean.DataBeanX.DataBean dataBean = list.get(i);
            String countryName = dataBean.getName();
            if (countryName.equals(data)) {
                et.setText(countryName);
                et.setSelection(countryName.length());
                onSelect(i);
            }
        }
    }

    class OptionsAdapter extends BaseAdapter {
        private Context context;

        /**
         * 自定义构造方法
         *
         * @param
         * @param
         * @param
         */
        public OptionsAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                //下拉项布局
                convertView = LayoutInflater.from(context).inflate(R.layout.option_country_item, null);
                holder.textView = (TextView) convertView.findViewById(R.id.item_text);
                holder.mTextView = (TextView) convertView.findViewById(R.id.tv_count_code);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if ((position == list.size() - 1 && list.size() > 1) || list.size() == 1) {
                holder.mTextView.setVisibility(GONE);
            } else {
                holder.mTextView.setVisibility(VISIBLE);
            }
            holder.textView.setText(list.get(position).getName());
            holder.mTextView.setText(list.get(position).getAreacode());
            return convertView;
        }

    }

    class ViewHolder {
        TextView textView;
        TextView mTextView;
    }
}
