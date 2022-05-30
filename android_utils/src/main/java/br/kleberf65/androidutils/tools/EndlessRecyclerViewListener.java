package br.kleberf65.androidutils.tools;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EndlessRecyclerViewListener extends RecyclerView.OnScrollListener {

    private final LoadData loadData;
    private int currentPage;
    private int previousTotalItemCount = 0;
    private int visibleThreshold;
    private int lastVisibleItem;
    private boolean loading;

    public EndlessRecyclerViewListener(LoadData loadData, int currentPage) {
        this.loadData = loadData;
        this.currentPage = currentPage;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int totalItemCount;
        if (recyclerView.getLayoutManager() != null) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            totalItemCount = layoutManager.getItemCount();
            if (layoutManager instanceof LinearLayoutManager) {
                visibleThreshold = 2;
                lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (layoutManager instanceof GridLayoutManager) {
                visibleThreshold = 5;
                lastVisibleItem = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
        } else throw new RuntimeException("EndlessRecyclerViewListener LayoutManager not by empty");

        if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            loadData.onLoadData(++currentPage);
            loading = true;
        }
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }
    }

    public interface LoadData {
        void onLoadData(int nextPage);
    }
}
