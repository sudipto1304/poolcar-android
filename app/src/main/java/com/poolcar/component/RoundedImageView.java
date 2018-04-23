package com.poolcar.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.poolcar.R;

@SuppressLint("AppCompatCustomView")
public class RoundedImageView extends AppCompatImageView {

    private int borderWidth = 10;
    private int viewWidth;
    private int viewHeight;
    private Bitmap image;
    private Paint paint;
    private Paint paintBorder;
    private BitmapShader shader;
    private int borderColor = Color.WHITE;
    private int shadowColor= Color.BLACK;
    private boolean isBorderRequired = true;
    private boolean isShadowRequired = true;

    public RoundedImageView(Context context)
    {
        super(context);
        setup();
    }

    public RoundedImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView);
        try {
            borderWidth = ta.getInteger(R.styleable.RoundedImageView_border_width, 10);
            isBorderRequired = ta.getBoolean(R.styleable.RoundedImageView_border, true);
            isShadowRequired = ta.getBoolean(R.styleable.RoundedImageView_shadow, true);
            shadowColor = ta.getColor(R.styleable.RoundedImageView_shadow_color, Color.BLACK);
            borderColor = ta.getColor(R.styleable.RoundedImageView_border_color, Color.WHITE);
        } finally {
            ta.recycle();
        }
        setup();
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setup();
    }

    private void setup()
    {
        paint = new Paint();
        paint.setAntiAlias(true);

        if(isBorderRequired) {
            paintBorder = new Paint();
            setBorderColor(borderColor);
            paintBorder.setAntiAlias(true);
            this.setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);
        }

        if(isShadowRequired)
            paintBorder.setShadowLayer(4.0f, 0.0f, 2.0f, shadowColor);
    }

    public void setBorderWidth(int borderWidth)
    {
        this.borderWidth = borderWidth;
        this.invalidate();
    }

    public void setBorderColor(int borderColor)
    {
        if (paintBorder != null)
            paintBorder.setColor(borderColor);

        this.invalidate();
    }

    private void loadBitmap()
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();

        if (bitmapDrawable != null)
            image = bitmapDrawable.getBitmap();
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas)
    {
        loadBitmap();

        if (image != null)
        {
            shader = new BitmapShader(Bitmap.createScaledBitmap(image, 50, 50, false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            int circleCenter = viewWidth / 2;
            canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter + borderWidth - 4.0f, paintBorder);
            canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter - 4.0f, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec, widthMeasureSpec);

        viewWidth = width - (borderWidth * 2);
        viewHeight = height - (borderWidth * 2);

        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec)
    {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY)
        {
            result = specSize;
        }
        else
        {
            // Measure the text
            result = viewWidth;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight, int measureSpecWidth)
    {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY)
        {
            result = specSize;
        }
        else
        {
            result = viewHeight;
        }

        return (result + 2);
    }

}
