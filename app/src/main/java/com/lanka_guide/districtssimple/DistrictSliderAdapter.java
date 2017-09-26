package com.lanka_guide.districtssimple;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

class DistrictSliderAdapter extends PagerAdapter {
    private Context mContext;
    private int[] districtImageIds = Districts.getDistrictImagesId();

    DistrictSliderAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return districtImageIds.length;
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(districtImageIds[i]);
        container.addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        container.removeView((ImageView) obj);
    }
}