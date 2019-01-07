package com.foxexchange.android.fragments

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.cjwsc.idcm.kotlin.base.BaseKTFragment
import com.foxexchange.android.R
import kotlinx.android.synthetic.main.fragment_mine_layout.*

class MineKTFragment : BaseKTFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_mine_layout

    override fun onInitView(bundle: Bundle?) {

    }

    override fun onEvent() {


        is_login_ly.setOnClickListener {
            //个人信息
            ARouter.getInstance().build("/user/activity/UserPersonalInfoActivity")
                    .withString("test","123432")
                    .navigation(mContext)
        }
        recharge_coins_ly.setOnClickListener{
            //充币
            ARouter.getInstance().build("/user/activity/UserRechargeCoinActivity")
                    .withString("test","123432")
                    .navigation(mContext)
        }
        withdraw_coins_ly.setOnClickListener{
            //提币
            ARouter.getInstance().build("/user/activity/UserWithDrawCoinActivity")
                    .withString("", "")
                    .navigation(mContext)
        }
        transfer_coins_ly.setOnClickListener{
            //划转
            ARouter.getInstance().build("/user/activity/UserTransferAssetActivity")
                    .withString("", "")
                    .navigation(mContext)
        }
        my_asset_ly.setOnClickListener{
            //我的资产
            ARouter.getInstance().build("/user/activity/UserMyAssetKTActivity")
                    .withString("", "")
                    .navigation(mContext)
        }
        my_orders_ly.setOnClickListener{
            //我的订单
            ARouter.getInstance().build("/user/activity/UserMyOrdersActivity")
                    .withString("", "")
                    .navigation(mContext)
        }
        certification_ly.setOnClickListener{
            //实名认证
            ARouter.getInstance().build("/user/activity/UserRealNameCertificateActivity")
                    .withString("", "")
                    .navigation(mContext)
        }
        invite_commission_ly.setOnClickListener{
            //邀请返佣
            ARouter.getInstance().build("/user/activity/UserInviteReturnCommissionActivity")
                    .navigation()
        }
        security_center_ly.setOnClickListener{
            //安全中心
            ARouter.getInstance().build("/user/activity/UserSecurityCenterActivity")
                    .withString("", "")
                    .navigation(mContext)
        }
        withdraw_addr_ly.setOnClickListener{
            //提币地址
            ARouter.getInstance().build("/user/activity/UserWithDrawCoinsAddressActivity")
                    .withString("", "")
                    .navigation(mContext)
        }
        messages_center_ly.setOnClickListener{
            //消息中心
            ARouter.getInstance().build("/user/activity/UserMessageCenterActivity")
                    .withString("", "")
                    .navigation(mContext)
        }
        help_center_ly.setOnClickListener{
            //帮助中心
            ARouter.getInstance().build("/user/activity/UserFeedBackActivity")
                    .withString("", "")
                    .navigation(mContext)
        }
        personal_setting_ly.setOnClickListener{
            //设置
            ARouter.getInstance().build("/user/activity/UserSettingActivity")
                    .withString("", "")
                    .navigation(mContext)
        }
    }

    override fun lazyFetchData() {

    }
}