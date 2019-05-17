package com.example.twitterlib.wigdet.dialog
import android.view.Gravity
import android.view.View
import com.sleep.uulib.R
import com.sleep.uulib.widget.fragmentdialog.HelpCenterLogicSetter
import com.sleep.uulib.widget.fragmentdialog.IdentifyDialogLogicSetter
import com.sleep.uulib.widget.fragmentdialog.PayTypeQuestionDialogLogicSetter
import com.sleep.uulib.widget.fragmentdialog.PhoneCallLogicSetter

/**
 * Created by Sleep on 2017/8/3.
 */
class DialogFactory private constructor(){


    private object DialogFactoryHolder {
        val INSTANCE = DialogFactory()
    }

    companion object Initializer{
        val instance: DialogFactory by lazy { DialogFactoryHolder.INSTANCE }
    }

    /**
     * 获取通用dialog
     *
     * @param title 标题
     * @param describe 弹窗中间部分文字
     * @param pos 确定文字设置
     * @param neg 取消文字设置
     * @param cancelEnable 是否可取消
     * @param posListener 点击确定按钮的监听
     * @param negListener 取消按钮点击监听，默认为null，点击会dismiss弹窗(可取消的话)
     */
    fun getCommonDialog(title: String,
                        describe: String,
                        pos: String,
                        neg: String?,
                        cancelEnable: Boolean,
                        posListener: View.OnClickListener?,
                        negListener:View.OnClickListener? = null): CommonFragmentDialog {
        val dialogBuilder = CommonFragmentDialog.DialogBuilder()
        val commonLogicSetter = CommonLogicSetter(posListener, negListener)
        commonLogicSetter.setData(title, describe, pos, neg)
        return dialogBuilder.setLogicSetter(commonLogicSetter).setCancelEnable(cancelEnable).build()
    }
    /**
     * 身份认证
     */
    fun getIdentifyComfirm(mNumberMoblic:String,posListener: View.OnClickListener?): CommonFragmentDialog {
        val dialogBuilder = CommonFragmentDialog.DialogBuilder()
        val commonLogicSetter = HelpCenterLogicSetter(mNumberMoblic,posListener)
        return dialogBuilder
                .setLayoutResouce(R.layout.view_gestrue_change_dialog)
                .setLogicSetter(commonLogicSetter)
                .setCancelEnable(true)
                .build()
    }
    fun getPhoneCallDialog(posListener: View.OnClickListener?):CommonFragmentDialog{
        val dialogBuilder = CommonFragmentDialog.DialogBuilder()
        val phoneCallLogicSetter = PhoneCallLogicSetter("",posListener)
        return dialogBuilder
                .setLayoutResouce(R.layout.view_phone_call_dialog)
                .setLogicSetter(phoneCallLogicSetter)
                .setCancelEnable(true)
                .build()
    }

    /**
     * 获取选择图片的dialog
     */
    fun getChooseImgDialog(choosePhotoListener: View.OnClickListener, chooseCameraListener: View.OnClickListener): CommonFragmentDialog {
        val dialogBuilder = CommonFragmentDialog.DialogBuilder()
        val chooseImgDialogLogicSetter = ChooseImgDialogLogicSetter(choosePhotoListener, chooseCameraListener)
        return dialogBuilder
                .setLayoutResouce(R.layout.view_choose_img)
                .setGravity(Gravity.BOTTOM)
                .setAnimStyleResource(R.style.dialog_bottom_anim)
                .setLogicSetter(chooseImgDialogLogicSetter)
                .setCancelEnable(true)
                .build()
    }

    /**
     * 获取底部对话框
     */
    fun getBottomDialog(layoutRes:Int,logicSetter: CommonFragmentDialog.ILogicSetter): CommonFragmentDialog{
        val dialogBuilder = CommonFragmentDialog.DialogBuilder()
        return dialogBuilder
                .setLayoutResouce(layoutRes)
                .setGravity(Gravity.BOTTOM)
                .setIsMatchParent(true)
                .setAnimStyleResource(R.style.dialog_bottom_anim)
                .setLogicSetter(logicSetter)
                .setCancelEnable(true)
                .build()
    }

    /**
     * 获取验证码的时候弹出的验证窗口
     */
    fun identifyDialog(listener: IdentifyDialogLogicSetter.ConfirmClickListener): CommonFragmentDialog{
        val dialogBuilder = CommonFragmentDialog.DialogBuilder()
        return dialogBuilder
                .setLayoutResouce(R.layout.dialog_identify_code)
                .setLogicSetter(IdentifyDialogLogicSetter(listener))
                .setCancelEnable(true)
                .build()
    }

    /**
     * 项目详情还款方式弹窗
     */
    fun getPaytypeQestionDialog(): CommonFragmentDialog{
        val dialogBuilder = CommonFragmentDialog.DialogBuilder()
        val paytypeQuestionDialog = PayTypeQuestionDialogLogicSetter()
        return dialogBuilder
                .setLayoutResouce(R.layout.view_pay_type_question_dialog)
                .setLogicSetter(paytypeQuestionDialog)
                .setCancelEnable(true)
                .build()
    }

}