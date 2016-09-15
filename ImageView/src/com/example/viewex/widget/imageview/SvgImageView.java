package com.example.viewex.widget.imageview;

import com.example.imageview.R;
import com.example.viewex.util.svg.SVG;
import com.example.viewex.util.svg.SVGParser;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * SvgImageView(无app:svg_raw_resource属性时为方形)
 * @author lf2596352
 *
 */
public class SvgImageView extends BaseImageView {

    private int mSvgRawResourceId;

    public SvgImageView(Context context) {
        super(context);
    }

    public SvgImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context, attrs);
    }

    public SvgImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        sharedConstructor(context, attrs);
    }

    private void sharedConstructor(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomShapeImageView);
        mSvgRawResourceId = a.getResourceId(R.styleable.CustomShapeImageView_svg_raw_resource, 0);
        a.recycle();
    }

    public static Bitmap getBitmap(Context context, int width, int height, int svgRawResourceId) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        if (svgRawResourceId > 0) {
            SVG svg = SVGParser.getSVGFromInputStream(
                    context.getResources().openRawResource(svgRawResourceId), width, height);
            canvas.drawPicture(svg.getPicture());
        } else {
            canvas.drawRect(new RectF(0.0f, 0.0f, width, height), paint);
        }

        return bitmap;
    }

    @Override
    public Bitmap getBitmap() {
        return getBitmap(mContext, getWidth(), getHeight(), mSvgRawResourceId);
    }
}

