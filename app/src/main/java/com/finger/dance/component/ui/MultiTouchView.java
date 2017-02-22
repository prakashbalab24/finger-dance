package com.finger.dance.component.ui;

/**
 * Created by prakash-bala on 20/2/17.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import com.finger.dance.R;

public class MultiTouchView extends View {
    public static int totalPointers=0;

    private static final int SIZE =20;

    private SparseArray<PointF> mActivePointers;
    private Paint mPaint;

    private Paint textPaint;

    public int getTotalPointers(){
        return totalPointers;

    }


    public MultiTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mActivePointers = new SparseArray<>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // set painter color to a color you like
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(50);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);
                mActivePointers.put(pointerId, f);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                    PointF point = mActivePointers.get(event.getPointerId(i));
                    if (point != null) {
                        point.x = event.getX(i);
                        point.y = event.getY(i);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                mActivePointers.remove(pointerId);
                break;
            }
        }
        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw all pointers
        for (int size = mActivePointers.size(), i = 0; i < size; i++) {
            PointF point = mActivePointers.valueAt(i);
            if (point != null)
                mPaint.setColor(getResources().getColor(R.color.colorAccent));
            canvas.drawCircle(point.x, point.y, SIZE, mPaint);
        }
        canvas.drawText("Total pointers detected: " + mActivePointers.size(), 10, 40 , textPaint);
        if(totalPointers<=mActivePointers.size()){
            totalPointers = mActivePointers.size();
        }
    }

}