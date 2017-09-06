package com.example.administrator.lenglian.fragment.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;

import static com.example.administrator.lenglian.R.id.container;

/**
 * description
 *
 */

public class BlankFragment extends BaseFragment {

    public static BlankFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("api", text);
        BlankFragment blankFragment = new BlankFragment();
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext,R.layout.order_recying,null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String api = getArguments().getString("api");
      //  接口
    }

    @Override
    protected void initData() {


    }
}
