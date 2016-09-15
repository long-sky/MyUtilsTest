package com.example.viewex.widget.imageview;

import com.example.imageview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;

/**
 * shape:rectangle	����
 * 		 circle		Բ��
 * 		 svg		app:svg_raw_resource���Զ�����״(��������raw��svgͼ)
 * @author lf2596352
 *
 */
public class CustomShapeImageView extends BaseImageView {

    public static class Shape {
        public static final int CIRCLE = 1;
        public static final int RECTANGLE = 2;
        public static final int SVG    = 3;
    }

    private int mShape = Shape.CIRCLE;
    private int mSvgRawResourceId;

    public CustomShapeImageView(Context context) {
        super(context);
    }

    public CustomShapeImageView(Context context, int resourceId, int shape, int svgRawResourceId) {
        this(context);

        setImageResource(resourceId);
        mShape = shape;
        mSvgRawResourceId = svgRawResourceId;
    }

    public CustomShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context, attrs);
    }

    public CustomShapeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        sharedConstructor(context, attrs);
    }

    private void sharedConstructor(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomShapeImageView);
        mShape = a.getInt(R.styleable.CustomShapeImageView_shape, Shape.CIRCLE);
        mSvgRawResourceId = a.getResourceId(R.styleable.CustomShapeImageView_svg_raw_resource, 0);
        a.recycle();
    }

    @Override
    public Bitmap getBitmap() {
        switch (mShape) {
            case Shape.CIRCLE:
                return CircleImageView.getBitmap(getWidth(), getHeight());
            case Shape.RECTANGLE:
                return RectangleImageView.getBitmap(getWidth(), getHeight());
            case Shape.SVG:
                return SvgImageView.getBitmap(mContext, getWidth(), getHeight(), mSvgRawResourceId);
        }
        return null;
    }
}
