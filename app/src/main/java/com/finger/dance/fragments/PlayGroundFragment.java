package com.finger.dance.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finger.dance.R;
import com.finger.dance.adapters.TileAdapter;
import com.finger.dance.data.TileColorData;
import com.finger.dance.models.TileModel;

import java.util.ArrayList;
import java.util.List;


public class PlayGroundFragment extends Fragment {
    private static final String LEVEL = "level";
    private int level;
    private RecyclerView recyclerView;
    private List<TileModel> tileList;
    private TileAdapter tileAdapter;

    public PlayGroundFragment() {
        // Required empty public constructor
    }

    public static PlayGroundFragment newInstance(int level) {
        PlayGroundFragment fragment = new PlayGroundFragment();
        Bundle args = new Bundle();
        args.putInt(LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            level = getArguments().getInt(LEVEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_play_ground, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        tileList = new ArrayList<>();

        tileAdapter = new TileAdapter(getContext(),tileList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tileAdapter);
        new TileColorData(tileList,tileAdapter,getActivity());
        return rootView;
    }

}
