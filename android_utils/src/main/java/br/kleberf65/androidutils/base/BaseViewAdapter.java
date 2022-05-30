package br.kleberf65.androidutils.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewAdapter<Model>
        extends RecyclerView.Adapter<BaseViewAdapter<Model>.BaseViewHolder> {

    protected List<Model> dataList = new ArrayList<>();

    public BaseViewAdapter() {
    }

    protected BaseViewAdapter(List<Model> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(getLayout(), parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindView(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<Model> models) {
        int oudCount = this.dataList.size();
        this.dataList.addAll(models);
        notifyItemRangeChanged(oudCount, this.dataList.size());
    }

    public abstract int getLayout();

    public abstract void bindDataView(View itemView, Model model);

    protected class BaseViewHolder extends RecyclerView.ViewHolder {

        private final View holderItemView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            this.holderItemView = itemView;
        }

        private void bindView(Model model) {
            bindDataView(holderItemView, model);
        }
    }

}
