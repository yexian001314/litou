package com.shqj.mine.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.uulib.bean.RechargeLimitBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.util.GlideHelper
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListActivity
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.RechargeLimitPresenter
import kotlinx.android.synthetic.main.mine_activity_recharge_limit_introduce.*

/**
 * 充值限额说明
 *
 */
@Route(path = ArouterConstant.MINE_RECHARGE_LIMIT_INTRODUCE)
class RechargeLimitIntroduceActivity : UUBaseListActivity<RechargeLimitBean.BankLimitModelsBean, RechargeLimitBean>(), LoadListDataView<RechargeLimitBean> {

    private lateinit var mPresenter: RechargeLimitPresenter

    override fun initItemLayout() {
        super.mRecyclerView = recharge_introduction_recycler
        super.mSmartLayout = recharge_introduction_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.mine_item_recharge_limit)
    }

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_recharge_limit_introduce
    }

    override fun initData() {
        super.initData()
        setTitle(getString(R.string.title_recharge_limit_introduct))
        mPresenter = RechargeLimitPresenter(this, mStateManager)
    }

    override fun retryGetData() {
        mStateManager.showContent()
        mPresenter.getRechargeLimitData()
    }

    override fun loadData(data: RechargeLimitBean) {
        super.loadData(data)
        mAdapter?.setNewData(data.bankLimitModels)
    }

    override fun onListRefresh() {
        mPresenter.getRechargeLimitData()
    }

    override fun onListLoadMore() {
    }

    override fun getCurrentRequestPage(): Int {
        return 0
    }

    override fun commonHolder(viewHolder: BaseViewHolder, item: RechargeLimitBean.BankLimitModelsBean) {
        Glide.with(this)
                .applyDefaultRequestOptions(GlideHelper.getOptions(GlideHelper.ImageType.CIRCLE))
                .load(item.bankPhotoUrl)
                .into(viewHolder.getView(R.id.iv_bank_icon))
        viewHolder.setText(R.id.tv_bank_name,item.bank)
        val singleLimit = NumberFormatUtils.formatNumberWithTenThousand(item.singleLimit.toInt())
        val singleDayLimit = NumberFormatUtils.formatNumberWithTenThousand(item.singleDayLimit.toInt())
        if(item.countLimit != 0){
            viewHolder.setText(R.id.tv_bank_limit,"单笔限额$singleLimit,单日限额$singleDayLimit\n单日仅限${item.countLimit}笔成功交易")
        }else{
            viewHolder.setText(R.id.tv_bank_limit,"单笔限额$singleLimit,单日限额$singleDayLimit")
        }
    }
}
