package com.cjwsc.idcm.widget.password;

/**
 * Created by Morning on 2017/8/17.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.cjwsc.idcm.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 虚拟键盘
 */
public class VirtualKeyboardView extends RelativeLayout implements View.OnClickListener {

    Context context;

    private GridView gridView;

    private RelativeLayout layoutBack;

    private ArrayList<Map<String, String>> valueList;


    public VirtualKeyboardView(Context context) {
        this(context, null);
    }

    public VirtualKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = View.inflate(context, R.layout.view_virtual_keyboard, null);

        valueList = new ArrayList<>();

        layoutBack = (RelativeLayout) view.findViewById(R.id.layoutBack);
        layoutBack.setOnClickListener(this);

        gridView = (GridView) view.findViewById(R.id.gv_keybord);

        setView();

        addView(view);
    }

    public RelativeLayout getLayoutBack() {
        return layoutBack;
    }

    public ArrayList<Map<String, String>> getValueList() {
        return valueList;
    }

    public GridView getGridView() {
        return gridView;
    }

    private void setView() {

        /* 初始化按钮上应该显示的数字 */
        for (int i = 1; i < 13; i++) {
            Map<String, String> map = new HashMap<String, String>();
            if (i < 10) {
                map.put("name", String.valueOf(i));
            } else if (i == 10) {
                map.put("name", ".");
            } else if (i == 11) {
                map.put("name", String.valueOf(0));
            } else if (i == 12) {
                map.put("name", "");
            }
            valueList.add(map);
        }

        KeyBoardAdapter keyBoardAdapter = new KeyBoardAdapter(context, valueList);
        gridView.setAdapter(keyBoardAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}