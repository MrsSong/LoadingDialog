package com.ltkj.app.progressloaddialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView


/**
@des:
@author: 不变
@time: 2024/4/11 17:09
 */
class ProgressDialog : Dialog {

    private var mView: View? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mProgressDialog: ProgressDialog? = null
        fun create(context: Context,str:String = "加载中..."): ProgressDialog {
            if (mProgressDialog == null) {

                mProgressDialog = ProgressDialog(context, str)

            }
            return mProgressDialog!!
        }

        fun dismiss() {
            mProgressDialog?.dismiss()
        }
    }

    constructor(context: Context) : super(context) {
        initViewProgress("加载中...")
    }


    constructor(context: Context,str:String = "加载中...") : super(context) {
        initViewProgress(str)
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener)

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && isShowing) {
            dismiss()
        }
        return true
    }

    private fun initViewProgress(str:String) {
        mView = LayoutInflater.from(context).inflate(R.layout.layout_progress_loading2, null, false)

        setCanceledOnTouchOutside(true)
        window?.setContentView(mView!!)
        // 设置窗口背景为透明
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        // 设置背景 dim 效果为 0，即不显示背景阴影
        window?.setDimAmount(0f)
        setCancelable(false)
    }


    override fun dismiss() {
        mProgressDialog = null
        super.dismiss()
    }

    override fun show() {
        val progressBar = mView?.findViewById<ImageView>(R.id.progress)
        progressBar?.clearAnimation()
        //动画
        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.progress_drawable_new)
        val lin = LinearInterpolator() //设置动画匀速运动
        animation.interpolator = lin
        //给图片添加动画
        progressBar?.startAnimation(animation)
        super.show()
    }


}