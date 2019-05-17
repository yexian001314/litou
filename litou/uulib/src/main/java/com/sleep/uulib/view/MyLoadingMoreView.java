package com.sleep.uulib.view;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.sleep.uulib.R;

/**
 * 作者：alsoWell on 2017/3/29 10:02
 * 邮箱：1161882463@qq.com
 */
public class MyLoadingMoreView extends LoadMoreView {
    private Context mContext;
    private View mMview;

    public MyLoadingMoreView(Context context) {
        mContext = context;
    }

    @Override
    public int getLayoutId() {

        return R.layout.item_list_footer;
    }

    @Override
    protected int getLoadingViewId() {

        return R.id.rotation_footer_progress;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.loading_error_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.loading_view ;
    }
}
