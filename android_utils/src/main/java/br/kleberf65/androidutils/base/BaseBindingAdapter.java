package br.kleberf65.androidutils.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBindingAdapter<B extends ViewDataBinding, Model>
        extends RecyclerView.Adapter<BaseBindingAdapter<B, Model>.BaseViewHolder> {

    protected List<Model> dataList = new ArrayList<>();

    public BaseBindingAdapter() {
    }

    protected BaseBindingAdapter(List<Model> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                getLayout(), parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindView(holder, dataList.get(position), position);
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

    public abstract void bindDataView(BaseViewHolder holder, B binding, Model model, int position);

    protected class BaseViewHolder extends RecyclerView.ViewHolder {

        private final B holderBinding;

        public BaseViewHolder(B binding) {
            super(binding.getRoot());
            this.holderBinding = binding;
        }

        private void bindView(BaseViewHolder holder, Model model, int position) {
            bindDataView(holder, holderBinding, model, position);
        }
    }

}
