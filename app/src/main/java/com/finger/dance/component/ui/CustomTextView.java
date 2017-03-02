package com.finger.dance.component.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        setCustomFont();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont();
    }

    private void setCustomFont() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/Pacifico-Regular.ttf");
        setTypeface(typeface);
    }
}
