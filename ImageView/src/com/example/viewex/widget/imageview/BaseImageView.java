package com.example.viewex.widget.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * BaseImageView
 * @author lf2596352
 *
 */
public abstract class BaseImageView extends ImageView {
    private static final String TAG = BaseImageView.class.getSimpleName();

    protected Context mContext;
    /**
     * 设置两张图片相交时的模式(18种)
     * 先绘制的图是目标图(DST)，后绘制的图是源图(SRC)
     * PorterDuff.Mode.ADD：饱和度叠加
     * PorterDuff.Mode.CLEAR：没东西
     * PorterDuff.Mode.DARKEN：取两图层全部区域，交集部分颜色加深
     * PorterDuff.Mode.DST：只保留目标图的alpha和color，所以绘制出来只有目标图
     * PorterDuff.Mode.DST_ATOP：源图和目标图相交处绘制目标图，不相交的地方绘制源图
     * PorterDuff.Mode.DST_IN：两者相交的地方绘制目标图，绘制的效果会受到原图处的透明度影响
     * PorterDuff.Mode.DST_OUT：在不相交的地方绘制目标图
     * PorterDuff.Mode.DST_OVER：目标图绘制在上方
     * PorterDuff.Mode.LIGHTEN：取两图层全部区域，点亮交集部分颜色
     * PorterDuff.Mode.MULTIPLY：取两图层交集部分叠加后颜色
     * PorterDuff.Mode.OVERLAY：叠加
     * PorterDuff.Mode.SCREEN：取两图层全部区域，交集部分变为透明色
     * PorterDuff.Mode.SRC：只保留源图像的alpha和color，所以绘制出来只有源图
     * PorterDuff.Mode.SRC_ATOP：源图和目标图相交处绘制源图，不相交的地方绘制目标图
     * PorterDuff.Mode.SRC_IN：两者相交的地方绘制源图
     * PorterDuff.Mode.SRC_OUT：不相交的地方绘制源图
     * PorterDuff.Mode.SRC_OVER：把源图绘制在上方
     * PorterDuff.Mode.XOR：不相交的地方按原样绘制源图和目标图
     */
    private static final Xfermode sXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//    private BitmapShader mBitmapShader;
    private Bitmap mMaskBitmap;
    private Paint mPaint;
    private WeakReference<Bitmap> mWeakBitmap;

    public BaseImageView(Context context) {
        super(context);
        sharedConstructor(context);
    }

    public BaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context);
    }

    public BaseImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        sharedConstructor(context);
    }

    private void sharedConstructor(Context context) {
        mContext = context;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void invalidate() {
        mWeakBitmap = null;
        if (mMaskBitmap != null) { mMaskBitmap.recycle(); }
        super.invalidate();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            int i = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(),
                    null, Canvas.ALL_SAVE_FLAG);
            try {
                Bitmap bitmap = mWeakBitmap != null ? mWeakBitmap.get() : null;
                // Bitmap not loaded.
                if (bitmap == null || bitmap.isRecycled()) {
                    Drawable drawable = getDrawable();
                    if (drawable != null) {
                        // Allocation onDraw but it's ok because it will not always be called.
                        bitmap = Bitmap.createBitmap(getWidth(),
                                getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas bitmapCanvas = new Canvas(bitmap);
                        drawable.setBounds(0, 0, getWidth(), getHeight());
                        drawable.draw(bitmapCanvas);

                        // If mask is already set, skip and use cached mask.
						if (mMaskBitmap == null || mMaskBitmap.isRecycled()) {
                            mMaskBitmap = getBitmap();
						}

                        // Draw Bitmap.
                        mPaint.reset();
                        mPaint.setFilterBitmap(false);
                        mPaint.setXfermode(sXfermode);
//                        mBitmapShader = new BitmapShader(mMaskBitmap,
//                                Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//                        mPaint.setShader(mBitmapShader);
                        bitmapCanvas.drawBitmap(mMaskBitmap, 0.0f, 0.0f, mPaint);

                        mWeakBitmap = new WeakReference<>(bitmap);
                    }
                }

                // Bitmap already loaded.
                if (bitmap != null) {
                    mPaint.setXfermode(null);
//                    mPaint.setShader(null);
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
                    return;
                }
            } catch (Exception e) {
                System.gc();

                Log.e(TAG, String.format("Failed to draw, Id :: %s. Error occurred :: %s", getId(), e.toString()));
            } finally {
                canvas.restoreToCount(i);
            }
        } else {
            super.onDraw(canvas);
        }
    }

    public abstract Bitmap getBitmap();
}
