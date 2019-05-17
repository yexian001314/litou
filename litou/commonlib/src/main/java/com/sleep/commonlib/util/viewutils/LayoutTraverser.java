package com.sleep.commonlib.util.viewutils;

import android.view.View;
import android.view.ViewGroup;

/**
 * 遍历所以的子view
 *
 * @author create by yexm
 * @time 2018/1/9 14:11
 */

public class LayoutTraverser {
    public interface Processor {
        void process(View view);
    }

    private final Processor processor;

    private LayoutTraverser(Processor processor) {
        this.processor = processor;
    }

    public static LayoutTraverser build(Processor processor) {
        return new LayoutTraverser(processor);
    }

    public void traverse(ViewGroup root) {
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            final View child = root.getChildAt(i);
            if (processor != null) {
                processor.process(child);
            }

            if (child instanceof ViewGroup) {
                traverse((ViewGroup) child);
            }
        }
    }
}
