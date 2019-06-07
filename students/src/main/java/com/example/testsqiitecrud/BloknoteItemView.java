package com.example.testsqiitecrud;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class BloknoteItemView extends AppCompatTextView {

    private Paint marginPaint;
    private Paint linePaint;
    private int paperColor;
    private float margin;


    public BloknoteItemView(Context context) {
        super(context);
        init();
    }

    public BloknoteItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BloknoteItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        Resources myResources = getResources();

        marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        marginPaint.setColor(myResources.getColor(R.color.notepad_margin));
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(myResources.getColor(R.color.notepad_lines));

        paperColor = myResources.getColor(R.color.notepad_paper);
        margin = myResources.getDimension(R.dimen.notepad_margin);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(paperColor);
        canvas.drawLine(0,0,getMeasuredHeight(),0,linePaint);
        canvas.drawLine(0,getMeasuredHeight(),getMeasuredWidth(),getMeasuredHeight(),linePaint);
        canvas.drawLine(margin,0,margin,getMeasuredHeight(),marginPaint);

        canvas.save();
        canvas.translate(margin,0);

        super.onDraw(canvas);
        canvas.restore();
    }
}
