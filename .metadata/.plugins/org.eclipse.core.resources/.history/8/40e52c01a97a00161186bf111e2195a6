package com.example.viewex.util;

import android.content.Context;

public class DimensionUtil {
	/**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
	public static int dip2px(Context context,float f){
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (f * scale + 0.5f);
	}
}
