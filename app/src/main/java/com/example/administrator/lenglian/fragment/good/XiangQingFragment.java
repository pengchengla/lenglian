package com.example.administrator.lenglian.fragment.good;

import android.view.View;
import android.widget.RadioGroup;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;

/**
 * Created by Administrator on 2017/9/1.
 */

public class XiangQingFragment extends BaseFragment {
    private RadioGroup rgp;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_xiangqing, null);
        rgp = (RadioGroup) view.findViewById(R.id.rgp);
        return view;
    }

    @Override
    protected void initData() {
        rgp.check(R.id.rb_tuwen);
    }
}
