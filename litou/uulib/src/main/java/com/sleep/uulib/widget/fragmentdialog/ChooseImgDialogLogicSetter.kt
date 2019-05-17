package com.example.twitterlib.wigdet.dialog

import android.view.View
import com.sleep.uulib.R


/**
 * Created by Sleep on 2017/7/18.
 */
class ChooseImgDialogLogicSetter constructor(var choosePhotoListener: View.OnClickListener, var chooseCameraListener: View.OnClickListener) : CommonFragmentDialog.ILogicSetter {

    override fun setLogic(fragmentDialog: CommonFragmentDialog, rootView: View) {
        val choosePhoto=rootView.findViewById<View>(R.id.choose_photo)
        val chooseCamera=rootView.findViewById<View>(R.id.choose_camera)
        val cancel=rootView.findViewById<View>(R.id.choose_dialog_cancel)
        choosePhoto.setOnClickListener({ view ->
            choosePhotoListener.onClick(view)
            fragmentDialog.dismiss()
        })
        chooseCamera.setOnClickListener({ view ->
            chooseCameraListener.onClick(view)
            fragmentDialog.dismiss()
        })
        cancel.setOnClickListener { fragmentDialog.dismiss() }

    }
}