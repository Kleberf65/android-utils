package br.kleberf65.androidutils.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import br.kleberf65.androidutils.base.interfaces.DefaultFragmentOptions;

public abstract class BaseBindingFragment<B extends ViewDataBinding> extends Fragment implements DefaultFragmentOptions {

    protected B binding;
    protected Context context;
    private View loadingContentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();
    }

    @Nullable
    @Override
    @SuppressLint("InflateParams")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        loadingContentView = LayoutInflater.from(context).inflate(layoutLoadingView(), null);
        relativeLayout.addView(binding.getRoot(), 0, new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.addView(loadingContentView, 1, new RelativeLayout.LayoutParams(-1, -1));
        return relativeLayout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeUi();
    }

    public void setLoadingContentView(View view) {
        loadingContentView = view;
    }

    public void setLoadingContentView(@LayoutRes int layoutId) {
        loadingContentView = LayoutInflater.from(context).inflate(layoutId, null);
    }

    public void showLoadingContentView() {
        loadingContentView.setVisibility(View.VISIBLE);
    }

    public void hideLoadingContentView() {
        loadingContentView.setVisibility(View.GONE);
    }

    public void setLoadingContentBackgroundColor(@IdRes int color) {
        loadingContentView.setBackgroundColor(color);
    }

    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public abstract int getLayout();

    public abstract void initializeUi();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
