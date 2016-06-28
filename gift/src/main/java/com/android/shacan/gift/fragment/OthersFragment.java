package com.android.shacan.gift.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.shacan.gift.R;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class OthersFragment extends Fragment {

    private View view;
    private Context mContext;
    public static OthersFragment newInstance(){
        OthersFragment fragment = new OthersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_well_chose,container,false);
        return view;
    }
}
