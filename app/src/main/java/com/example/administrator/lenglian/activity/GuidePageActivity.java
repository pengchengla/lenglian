package com.example.administrator.lenglian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.utils.SpUtils;

/**
 * Created by Administrator on 2017/8/24.
 */

public class GuidePageActivity extends BaseActivity {
    private ViewPager vp;
    private GuidePagerAdapter mGuidePagerAdapter;
    private int[] imgurls = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidepage);
        vp = (ViewPager) findViewById(R.id.vp);
        if (mGuidePagerAdapter == null) {
            mGuidePagerAdapter = new GuidePagerAdapter();
        }
        vp.setAdapter(mGuidePagerAdapter);
    }

    private class GuidePagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=View.inflate(GuidePageActivity.this,R.layout.item_guide,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_guide);
            TextView tv_into= (TextView) view.findViewById(R.id.tv_into);
            imageView.setImageResource(imgurls[position]);
            container.addView(view);
            if (position==2){
                tv_into.setVisibility(View.VISIBLE);
                tv_into.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SpUtils.putBoolean(GuidePageActivity.this, "guide", true);
                        Intent intent = new Intent(GuidePageActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }else {
                tv_into.setVisibility(View.GONE);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
