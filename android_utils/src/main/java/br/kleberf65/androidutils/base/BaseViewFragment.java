package br.kleberf65.androidutils.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.kleberf65.androidutils.R;

public abstract class BaseViewFragment extends Fragment {

    protected View baseView;
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
        baseView = inflater.inflate(getLayout(), container, false);
        loadingContentView = LayoutInflater.from(context).inflate(R.layout.layout_loading_content, null);
        relativeLayout.addView(baseView, 0, new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.addView(loadingContentView, 1, new RelativeLayout.LayoutParams(-1, -1));
        return relativeLayout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeUi();
    }

    private void setLoadingContentView(View view) {
        loadingContentView = view;
    }

    private void setLoadingContentView(@LayoutRes int layoutId) {
        loadingContentView = LayoutInflater.from(context).inflate(layoutId, null);
    }

    public void showLoadingContentView() {
        loadingContentView.setVisibility(View.VISIBLE);
    }

    public void hideLoadingContentView() {
        loadingContentView.setVisibility(View.GONE);
    }

    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public abstract int getLayout();

    public abstract void initializeUi();
}
