package br.kleberf65.androidutils.base.interfaces;

import br.kleberf65.androidutils.R;

public interface DefaultFragmentOptions {

    default int layoutLoadingView() {
        return R.layout.layout_loading_content;
    }
}
