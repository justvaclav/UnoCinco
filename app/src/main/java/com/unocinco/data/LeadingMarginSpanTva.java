package com.unocinco.data;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

class LeadingMarginSpanTva implements LeadingMarginSpan.LeadingMarginSpan2 {
    private int margin;
    private int lines;

    LeadingMarginSpanTva(int lines, int margin) {
        this.margin = margin;
        this.lines = lines;
    }

    /* Возвращает значение, на которе должен быть добавлен отступ */
    @Override
    public int getLeadingMargin(boolean first) {
        if (first) {
            /*
             * Данный отступ будет применен к количеству строк
             * возвращаемых getLeadingMarginLineCount()
             */
            return margin;
        } else {
            // Отступ для всех остальных строк
            return 0;
        }
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir,
                                  int top, int baseline, int bottom, CharSequence text,
                                  int start, int end, boolean first, Layout layout) {}

    /*
     * Возвращает количество строк, к которым должен быть
     * применен отступ возвращаемый методом getLeadingMargin(true)
     * Замечание:
     * Отступ применяется только к N строкам первого параграфа.
     */
    @Override
    public int getLeadingMarginLineCount() {
        return lines;
    }
};