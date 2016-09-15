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
     * ��������ͼƬ�ཻʱ��ģʽ(18��)
     * �Ȼ��Ƶ�ͼ��Ŀ��ͼ(DST)������Ƶ�ͼ��Դͼ(SRC)
     * PorterDuff.Mode.ADD�����Ͷȵ���
     * PorterDuff.Mode.CLEAR��û����
     * PorterDuff.Mode.DARKEN��ȡ��ͼ��ȫ�����򣬽���������ɫ����
     * PorterDuff.Mode.DST��ֻ����Ŀ��ͼ��alpha��color�����Ի��Ƴ���ֻ��Ŀ��ͼ
     * PorterDuff.Mode.DST_ATOP��Դͼ��Ŀ��ͼ�ཻ������Ŀ��ͼ�����ཻ�ĵط�����Դͼ
     * PorterDuff.Mode.DST_IN�������ཻ�ĵط�����Ŀ��ͼ�����Ƶ�Ч�����ܵ�ԭͼ����͸����Ӱ��
     * PorterDuff.Mode.DST_OUT���ڲ��ཻ�ĵط�����Ŀ��ͼ
     * PorterDuff.Mode.DST_OVER��Ŀ��ͼ�������Ϸ�
     * PorterDuff.Mode.LIGHTEN��ȡ��ͼ��ȫ�����򣬵�������������ɫ
     * PorterDuff.Mode.MULTIPLY��ȡ��ͼ�㽻�����ֵ��Ӻ���ɫ
     * PorterDuff.Mode.OVERLAY������
     * PorterDuff.Mode.SCREEN��ȡ��ͼ��ȫ�����򣬽������ֱ�Ϊ͸��ɫ
     * PorterDuff.Mode.SRC��ֻ����Դͼ���alpha��color�����Ի��Ƴ���ֻ��Դͼ
     * PorterDuff.Mode.SRC_ATOP��Դͼ��Ŀ��ͼ�ཻ������Դͼ�����ཻ�ĵط�����Ŀ��ͼ
     * PorterDuff.Mode.SRC_IN�������ཻ�ĵط�����Դͼ
     * PorterDuff.Mode.SRC_OUT�����ཻ�ĵط�����Դͼ
     * PorterDuff.Mode.SRC_OVER����Դͼ�������Ϸ�
     * PorterDuff.Mode.XOR�����ཻ�ĵط���ԭ������Դͼ��Ŀ��ͼ
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
