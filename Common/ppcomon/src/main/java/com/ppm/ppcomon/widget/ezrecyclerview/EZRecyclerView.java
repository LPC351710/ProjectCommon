package com.ppm.ppcomon.widget.ezrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Author dodoca_android.
 * Date 2017/6/5.
 */

public class EZRecyclerView extends RecyclerView {
    private OnLoadListener onLoadListener;
    private boolean disableLoadMore;
    private LoadState state;
    private boolean spanHeader;//span your header
    private boolean spanFooter;//span your footer
    private boolean spanLoadFooter;//span your load footer

    private enum LoadState {
        IDLE, LOADING
    }

    public EZRecyclerView(Context context) {
        super(context);
        init();
    }

    public EZRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EZRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
        disableLoadMore = true;
        state = LoadState.IDLE;
    }

    /**
     * @param spanHeader if span your header, must be set before {@link EZRecyclerView#setAdapter(Adapter)}.
     */
    public void setSpanHeader(boolean spanHeader) {
        this.spanHeader = spanHeader;
    }

    /**
     * @param spanFooter if span your footer, must be set before {@link EZRecyclerView#setAdapter(Adapter)}.
     */
    public void setSpanFooter(boolean spanFooter) {
        this.spanFooter = spanFooter;
    }

    /**
     * @param spanLoadFooter if span your loadFooter, must be set before {@link EZRecyclerView#setAdapter(Adapter)}.
     */
    public void setSpanLoadFooter(boolean spanLoadFooter) {
        this.spanLoadFooter = spanLoadFooter;
    }

    private void init() {
        onLoadListener = null;
        state = LoadState.IDLE;
        disableLoadMore = true;
        spanHeader = false;
        spanFooter = false;
        spanLoadFooter = false;
        addItemDecoration(new EZLineDecoration());
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter == null) {
            throw new IllegalArgumentException("Adapter is null");
        }
        super.setAdapter(adapter);
        useLoadFooter();
        disableLoad();
    }

    public void setLayoutManagerWithSpan(LayoutManager layout, boolean spanHeader, boolean spanFooter, boolean spanLoadFooter) {
        this.spanHeader = spanHeader;
        this.spanFooter = spanFooter;
        this.spanLoadFooter = spanLoadFooter;
        setLayoutManager(layout);
        useSpanForGridLayoutManager();
    }

    @Override
    public EZAdapter getAdapter() {
        return (EZAdapter) super.getAdapter();
    }

    /**
     * when you are use {@link GridLayoutManager},you can span your header,footer,loadFooter.
     */
    public void useSpanForGridLayoutManager() {
        if (getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) getLayoutManager();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (spanHeader && position < getAdapter().getHeaderCount()) {
                        return gridLayoutManager.getSpanCount();
                    }

                    if (spanFooter && position >= getAdapter().getHeaderCount() + getAdapter().getListCount()
                            && position < getAdapter().getItemCount() - 1) {
                        return gridLayoutManager.getSpanCount();
                    }

                    if (spanLoadFooter && position == getAdapter().getItemCount() - 1) {
                        return gridLayoutManager.getSpanCount();
                    }

                    return 1;
                }
            });
        }
    }

    /**
     * use load footer for load more function.
     */
    private void useLoadFooter() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int count = getLayoutManager().getItemCount();

                if (count == 0 || disableLoadMore || onLoadListener == null) {
                    getAdapter().showLoadFooter(false);
                    return;
                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (state == LoadState.LOADING) {
                        return;
                    }

                    int last;

                    if (getLayoutManager() instanceof LinearLayoutManager) {
                        last = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                    } else if (getLayoutManager() instanceof GridLayoutManager) {
                        last = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                    } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
                        getAdapter().showLoadFooter(false);
                        return;
                    } else {
                        return;
                    }

                    //Log.i("temp", "first:" + first + "<>last:" + last + "<>count:" + count);

                    if (last == count - 1) {
                        state = LoadState.LOADING;
                        getAdapter().showLoadFooter(true);
                        onLoadListener.onLoad();
                    }
                }
            }
        });
    }

    public void handleLoad(int pageSize, List tList) {
        handleLoad(pageSize, tList == null ? 0 : tList.size());
    }

    /**
     * @param pageSize the default page size
     * @param realSize the real loaded size
     *                 if pageSize > realSize, stop load more.
     */
    public void handleLoad(int pageSize, int realSize) {
        disableLoadMore = pageSize > realSize;
        state = LoadState.IDLE;

        if (disableLoadMore) {
            getAdapter().showLoadFooter(false);
        }
    }

    /**
     * disable load more function.
     */
    public void disableLoad() {
        disableLoadMore = true;
        state = LoadState.IDLE;
        getAdapter().showLoadFooter(false);
    }

    public interface OnLoadListener {
        void onLoad();
    }

    public abstract static class EZAdapter<T, VH extends ViewHolder> extends Adapter<ViewHolder> {
        private int position;
        private final int TYPE_HEADER = 10001;
        private final int TYPE_FOOTER = 10002;
        private final int TYPE_LOAD_FOOTER = 10003;
        private final int TYPE_ITEM = 10004;
        private List<View> headerList;
        private List<View> footerList;
        private View loadFooter;
        private List<T> list;

        private EZRecyclerView ezRecyclerView;
        private int pageSize;

        public EZAdapter() {
            super();
            headerList = new ArrayList<>();
            footerList = new ArrayList<>();
            list = new ArrayList<>();
        }

        public EZAdapter(EZRecyclerView ezRecyclerView, int pageSize) {
            this();
            enableLoadMore(ezRecyclerView, pageSize);
        }

        public void enableLoadMore(EZRecyclerView ezRecyclerView, int pageSize) {
            this.ezRecyclerView = ezRecyclerView;
            this.pageSize = pageSize;
        }

        @Override
        public final ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEADER) {
                return new ViewHolder(headerList.get(getHeaderPosition(position))) {
                };
            }

            if (viewType == TYPE_FOOTER) {
                return new ViewHolder(footerList.get(getFooterPosition(position))) {
                };
            }

            if (viewType == TYPE_LOAD_FOOTER) {
                if (loadFooter == null) {
                    loadFooter = new EZLoadFooter(parent.getContext()).getView();
                    showLoadFooter(false);
                }
                return new ViewHolder(loadFooter) {
                };
            }

            return ezOnCreateViewHolder(parent, viewType, parent.getContext());
        }

        @SuppressWarnings("unchecked")
        @Override
        public final void onBindViewHolder(ViewHolder holder, int position) {
            if (getItemViewType(position) == TYPE_HEADER
                    || getItemViewType(position) == TYPE_FOOTER
                    || getItemViewType(position) == TYPE_LOAD_FOOTER) {
                return;
            }

            ezOnBindViewHolder((VH) holder, getListPosition(position), holder.itemView.getContext(), getList().get(getListPosition(position)));
        }

        @Override
        public final int getItemCount() {
            return getHeaderCount() + getListCount() + getFooterCount() + 1;
        }

        @Override
        public final int getItemViewType(int position) {
            this.position = position;

            if (position < getHeaderCount()) {
                return TYPE_HEADER;
            }

            if (position >= getHeaderCount() + getListCount() && position < getHeaderCount() + getListCount() + getFooterCount()) {
                return TYPE_FOOTER;
            }

            if (position == getHeaderCount() + getListCount() + getFooterCount() + 1 - 1) {
                return TYPE_LOAD_FOOTER;
            }

            return ezGetItemViewType();
        }

        /**
         * override this instead of {@link EZAdapter#getItemViewType(int)} for many view types.
         *
         * @return custom view type.
         */
        public int ezGetItemViewType() {
            return TYPE_ITEM;
        }

        /**
         * @param loadFooter your custom load footer view. must be set before {@link EZRecyclerView#setAdapter(Adapter)}.
         */
        public void setLoadFooter(View loadFooter) {
            this.loadFooter = loadFooter;
        }

        public void showLoadFooter(boolean show) {
            if (loadFooter != null) {
                if (show && loadFooter.getVisibility() == View.VISIBLE
                        || !show && loadFooter.getVisibility() == View.INVISIBLE) {
                    return;
                }
                loadFooter.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
            }
        }

        public abstract VH ezOnCreateViewHolder(ViewGroup parent, int viewType, Context context);

        public abstract void ezOnBindViewHolder(VH holder, int position, Context context, T t);

        public View inflate(Context context, int layoutResId) {
            return LayoutInflater.from(context).inflate(layoutResId, null);
        }

        public List<T> getList() {
            return this.list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }

        public void refresh(List<T> list) {
            this.list.clear();
            if (list != null) {
                this.list.addAll(list);
            }
            notifyDataSetChanged();
            handleLoad(list);
        }

        public void load(List<T> list) {
            if (list != null) {
                this.list.addAll(list);
            }
            notifyDataSetChanged();
            handleLoad(list);
        }

        private void handleLoad(List list) {
            if (ezRecyclerView != null) {
                ezRecyclerView.handleLoad(pageSize, list);
            }
        }

        private int getListCount() {
            return list.size();
        }

        private int getListPosition(int position) {
            return position - getHeaderCount();
        }

        public void addHeader(View view) {
            headerList.add(view);
        }

        public void removeHeader(View view) {
            headerList.remove(view);
        }

        private int getHeaderCount() {
            return headerList.size();
        }

        private int getHeaderPosition(int position) {
            return position;
        }

        public void addFooter(View view) {
            footerList.add(view);
        }

        public void removeFooter(View view) {
            footerList.remove(view);
        }

        private int getFooterCount() {
            return footerList.size();
        }

        private int getFooterPosition(int position) {
            return position - getHeaderCount() - getListCount();
        }
    }
}
