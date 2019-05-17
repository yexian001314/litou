package com.shqj.arrange.fragment


import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.uulib.bean.ProjectDetailBean
import com.sleep.uulib.uubase.UUBaseFragment
import com.sleep.uulib.widget.GridLayoutItemDecoration
import com.shqj.arrange.R
import com.shqj.arrange.adapter.RelateInformationAdapter
import kotlinx.android.synthetic.main.arrange_fragment_relate_information.*


/**
 * 详情中的相关资料页.
 */
class RelateInformationFragment : UUBaseFragment() {

    private lateinit var loanType: String
    private var list: List<ProjectDetailBean.ImageInfo>? = null
    /**
     * 当前用户是否投资过这个产品
     */
    private var currentType = ProjectDetail.NOT_LOGIN

    override fun getLayoutResourse(): Int {
        return R.layout.arrange_fragment_relate_information
    }

    override fun initView(view: View) {
    }

    override fun initData() {
    }

    override fun onFirstUserVisible() {
        if (judgeUserIsLogin()) {
            setView(loanType, list)
        }
    }

    override fun onUserVisible() {
        super.onUserVisible()
        if (judgeUserIsLogin()) {
            setView(loanType, list)
        }
    }

    override fun retryGetData() {
    }

    /**
     * 外部调用设置数据
     */
    fun setData(loanType: String, list: List<ProjectDetailBean.ImageInfo>?) {
        this.loanType = loanType
        this.list = list
        if (judgeUserIsLogin()) {
            setView(loanType, list)
        }
    }

    /**
     * 判断用户是否登录并显示不同的界面
     */
    private fun judgeUserIsLogin(): Boolean {

        return when (currentType) {
            ProjectDetail.CONTENT -> {
                ll_content.visibility = View.VISIBLE
                tv_not_login.visibility = View.INVISIBLE
                true
            }
            ProjectDetail.NOT_INVEST -> {
                tv_not_login.text = "为保护债务人信息\n此项目详情仅供已投资人查看\n感谢您的理解与配合"
                ll_content.visibility = View.INVISIBLE
                tv_not_login.visibility = View.VISIBLE
                true
            }
            ProjectDetail.NOT_LOGIN -> {
                tv_not_login.text = "抱歉，该内容仅登录后可查看"
                ll_content.visibility = View.INVISIBLE
                tv_not_login.visibility = View.VISIBLE
                false
            }
        }
    }

    fun setCurrentUserIsInvest(currentType: ProjectDetail = ProjectDetail.NOT_LOGIN) {
        this.currentType = currentType
        judgeUserIsLogin()
    }

    /**
     * 设置界面
     */
    private fun setView(loanType: String, list: List<ProjectDetailBean.ImageInfo>?) {
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        relate_information_recycler.layoutManager = gridLayoutManager
        relate_information_recycler.isNestedScrollingEnabled = false
        relate_information_recycler.addItemDecoration(GridLayoutItemDecoration(context, 10f))
        var adapter: BaseQuickAdapter<ProjectDetailBean.ImageInfo, BaseViewHolder>? = null
        when (loanType) {
            "1" -> {
                adapter = RelateInformationAdapter(context, R.layout.item_car_loan, loanType)
            }
            "3" -> {
                adapter = RelateInformationAdapter(context, R.layout.item_enterprise_loan, loanType)
            }
        }
        relate_information_recycler.adapter = adapter
        adapter?.setNewData(list)
        adapter?.setEnableLoadMore(false)
    }

    enum class ProjectDetail {
        CONTENT,
        NOT_LOGIN,
        NOT_INVEST
    }
}
