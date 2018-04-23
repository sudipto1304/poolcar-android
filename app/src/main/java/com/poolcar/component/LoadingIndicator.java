package com.poolcar.component;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;

public class LoadingIndicator extends Indicator{

    float scaleFloat1;
    float scaleFloat2;
    float degrees;

    public LoadingIndicator() {
    }

    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing = 12.0F;
        float x = (float)(this.getWidth() / 2);
        float y = (float)(this.getHeight() / 2);
        canvas.save();
        canvas.translate(x, y);
        canvas.scale(this.scaleFloat1, this.scaleFloat1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0.0F, 0.0F, x / 2.5F, paint);
        canvas.restore();
        canvas.translate(x, y);
        canvas.scale(this.scaleFloat2, this.scaleFloat2);
        canvas.rotate(this.degrees);
        paint.setStrokeWidth(3.0F);
        paint.setStyle(Paint.Style.STROKE);
        float[] startAngles = new float[]{225.0F, 45.0F};

        for(int i = 0; i < 2; ++i) {
            RectF rectF = new RectF(-x + circleSpacing, -y + circleSpacing, x - circleSpacing, y - circleSpacing);
            canvas.drawArc(rectF, startAngles[i], 90.0F, false, paint);
        }

    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(new float[]{1.0F, 0.3F, 1.0F});
        scaleAnim.setDuration(1000L);
        scaleAnim.setRepeatCount(-1);
        this.addUpdateListener(scaleAnim, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                LoadingIndicator.this.scaleFloat1 = (Float)animation.getAnimatedValue();
                LoadingIndicator.this.postInvalidate();
            }
        });
        ValueAnimator scaleAnim2 = ValueAnimator.ofFloat(new float[]{1.0F, 0.6F, 1.0F});
        scaleAnim2.setDuration(1000L);
        scaleAnim2.setRepeatCount(-1);
        this.addUpdateListener(scaleAnim2, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                LoadingIndicator.this.scaleFloat2 = (Float)animation.getAnimatedValue();
                LoadingIndicator.this.postInvalidate();
            }
        });
        ValueAnimator rotateAnim = ValueAnimator.ofFloat(new float[]{0.0F, 180.0F, 360.0F});
        rotateAnim.setDuration(1000L);
        rotateAnim.setRepeatCount(-1);
        this.addUpdateListener(rotateAnim, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                LoadingIndicator.this.degrees = (Float)animation.getAnimatedValue();
                LoadingIndicator.this.postInvalidate();
            }
        });
        ArrayList<ValueAnimator> animators = new ArrayList();
        animators.add(scaleAnim);
        animators.add(scaleAnim2);
        animators.add(rotateAnim);
        return animators;
    }
}
