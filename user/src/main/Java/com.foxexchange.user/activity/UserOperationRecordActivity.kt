package com.foxexchange.user.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.user.R
import com.foxexchange.common.activitys.BaseFoxExChangeKTActivity
import com.foxexchange.common.adapter.MyPagerAdapter
import com.foxexchange.user.fragments.UserLoginHistoryFragment
import com.foxexchange.user.fragments.UserSafetySettingHistoryFragment
import kotlinx.android.synthetic.main.activity_operation_record.*

/**
 * 操作记录
 * @ling
 */
@Route(path = "/user/activity/UserOperationRecordActivity", extras = 0)
class UserOperationRecordActivity : BaseFoxExChangeKTActivity(){

    lateinit var img_back : ImageView
    lateinit var tv_title : TextView

    private val titles : Array<String> = arrayOf("登录历史", "安全设置历史")

    private val fragmentList : ArrayList<Fragment> =
            arrayListOf(UserLoginHistoryFragment(), UserSafetySettingHistoryFragment())

    override val layoutId: Int
        get() = R.layout.activity_operation_record

    override fun onInitView(bundle: Bundle?) {
        img_back = findViewById(R.id.img_back)
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = getString(R.string.str_operation_record)

        vp_operation_record.adapter = MyPagerAdapter(supportFragmentManager, fragmentList)
        tab_operation_record.setViewPager(vp_operation_record, titles, this, fragmentList)
    }

    override fun onEvent() {
        img_back.setOnClickListener(this::onClick)
    }

    fun onClick(v : View?) {
        when (v) {
            img_back -> finish()
        }
    }

}