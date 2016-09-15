package com.example.viewex.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.imageview.R;
import com.example.viewex.widget.imageview.CustomShapeImageView;
import com.example.viewex.widget.imageview.CustomShapeSquareImageView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * svg gridview适配器
 * @author lf2596352
 *
 */
public class SvgImagesAdapter extends BaseAdapter{
	private List<Integer> mSvgRawResourceIds = new ArrayList<>();

    private Context mContext;

    public SvgImagesAdapter(Context context) {
        mContext = context;

        mSvgRawResourceIds.add(R.raw.shape_star);//五角星形
        mSvgRawResourceIds.add(R.raw.shape_heart);//心形
        mSvgRawResourceIds.add(R.raw.shape_flower);//花型
        mSvgRawResourceIds.add(R.raw.shape_star_2);//锯齿盘
        mSvgRawResourceIds.add(R.raw.shape_star_3);//锯齿盘
        mSvgRawResourceIds.add(R.raw.shape_circle_2);//怪圆形
        mSvgRawResourceIds.add(R.raw.shape_5);//五边形
        mSvgRawResourceIds.add(R.raw.imgview_diamond);
        mSvgRawResourceIds.add(R.raw.imgview_heart);
        mSvgRawResourceIds.add(R.raw.imgview_hexagon);
        mSvgRawResourceIds.add(R.raw.imgview_octogon);
        mSvgRawResourceIds.add(R.raw.imgview_pentagon);
        mSvgRawResourceIds.add(R.raw.imgview_star);
    }

    @Override
    public int getCount() {
        return mSvgRawResourceIds.size();
    }

    @Override
    public Integer getItem(int i) {
        return mSvgRawResourceIds.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mSvgRawResourceIds.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return new CustomShapeSquareImageView(mContext, R.drawable.sample_1,CustomShapeImageView.Shape.SVG, getItem(i));// It is just a sample ;)
    }
}
