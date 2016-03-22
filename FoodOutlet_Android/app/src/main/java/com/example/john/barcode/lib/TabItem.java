package com.example.john.barcode.lib;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class TabItem<T> implements TabListener {
    protected final Context mContext;
    protected final String mTag;
    protected final Class<T> mClass;
    protected final Bundle mArgs;
    protected Fragment mFragment;


    public TabItem(Context ctx, String mTag, Class<T> mClass) {
        this(ctx, mTag, mClass, null);

    }

    public TabItem(Context ctx, String mTag, Class<T> mClass, Bundle mArgs) {
        this.mContext = ctx;
        this.mTag = mTag;
        this.mClass = mClass;
        this.mArgs = mArgs;

//            mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
//            if (mFragment != null && !mFragment.isDetached()) {
//                android.app.FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
//                ft.detach(mFragment);
//                ft.commit();
//            }
    }

    public String getTag() {
        return mTag;
    }
}