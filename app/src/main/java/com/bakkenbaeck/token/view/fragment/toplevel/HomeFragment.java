package com.bakkenbaeck.token.view.fragment.toplevel;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakkenbaeck.token.R;
import com.bakkenbaeck.token.databinding.FragmentHomeBinding;
import com.bakkenbaeck.token.model.local.ActivityResultHolder;
import com.bakkenbaeck.token.presenter.HomePresenter;
import com.bakkenbaeck.token.presenter.factory.HomePresenterFactory;
import com.bakkenbaeck.token.presenter.factory.PresenterFactory;
import com.bakkenbaeck.token.view.fragment.BasePresenterFragment;

public class HomeFragment extends BasePresenterFragment<HomePresenter, HomeFragment> {

    private FragmentHomeBinding binding;
    private ActivityResultHolder resultHolder;
    private HomePresenter presenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final @Nullable Bundle inState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        this.binding.title.setText(getString(R.string.tab_1));
        return binding.getRoot();
    }

    public FragmentHomeBinding getBinding() {
        return this.binding;
    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return new HomePresenterFactory();
    }

    @Override
    protected void onPresenterPrepared(@NonNull HomePresenter presenter) {
        this.presenter = presenter;
        tryProcessResultHolder();
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        this.resultHolder = new ActivityResultHolder(requestCode, resultCode, data);
        tryProcessResultHolder();
    }

    private void tryProcessResultHolder() {
        if (this.presenter == null || this.resultHolder == null) {
            return;
        }

        this.presenter.handleActivityResult(this.resultHolder, this.getContext());
        this.resultHolder = null;
    }

    @Override
    protected int loaderId() {
        return 5;
    }
}