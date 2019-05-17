package com.sleep.commonlib.util.viewutils;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 2017/12/22.
 */

public class ViewLists {
    private ViewLists() {}

    public static List<View> find(ViewGroup root, Object tag) {
        FinderByTag finderByTag = new FinderByTag(tag);
        LayoutTraverser.build(finderByTag).traverse(root);
        return finderByTag.getViews();
    }

    public static <T extends View> List<T> find(ViewGroup root, Class<T> type) {
        FinderByType<T> finderByType = new FinderByType<T>(type);
        LayoutTraverser.build(finderByType).traverse(root);
        return finderByType.getViews();
    }

    private static class FinderByTag implements LayoutTraverser.Processor {
        private final Object searchTag;
        private final List<View> views = new ArrayList<View>();

        private FinderByTag(Object searchTag) {
            this.searchTag = searchTag;
        }

        @Override
        public void process(View view) {
            Object viewTag = view.getTag();

            if (viewTag != null && viewTag.equals(searchTag)) {
                views.add(view);
            }
        }

        private List<View> getViews() {
            return views;
        }
    }

    private static class FinderByType<T extends View> implements LayoutTraverser.Processor {
        private final Class<T> type;
        private final List<T> views;

        private FinderByType(Class<T> type) {
            this.type = type;
            views = new ArrayList<T>();
        }

        @Override
        @SuppressWarnings("unchecked")
        public void process(View view) {
            if (type.isInstance(view)) {
                views.add((T) view);
            }
        }

        public List<T> getViews() {
            return views;
        }
    }
}
