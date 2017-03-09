package com.finger.dance.fragments;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.finger.dance.R;
import com.finger.dance.adapters.TileAdapter;
import com.finger.dance.component.ui.RadialGradientView;
import com.finger.dance.data.TileColorData;
import com.finger.dance.models.TileModel;
import com.finger.dance.utils.AppConstants;
import com.finger.dance.utils.ColorGradient;

import java.util.ArrayList;
import java.util.List;


public class PlayGroundFragment extends Fragment {
    private int level;
    private RecyclerView recyclerView;
    private List<TileModel> tileList;
    private TileAdapter tileAdapter;
    private RadialGradientView gradientView;

    public PlayGroundFragment() {
        // Required empty public constructor
    }

    public static PlayGroundFragment newInstance(int level) {
        PlayGroundFragment fragment = new PlayGroundFragment();
        Bundle args = new Bundle();
        args.putInt(AppConstants.LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            level = getArguments().getInt(AppConstants.LEVEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play_ground, container, false);
        gradientView = (RadialGradientView) rootView.findViewById(R.id.background);
        ColorGradient.changeBackground(gradientView,getContext());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        tileList = new ArrayList<>();

        tileAdapter = new TileAdapter(getContext(),tileList,level,gradientView);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), level);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tileAdapter);
//
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // save rect of view in screen coordinates
//                final Rect viewRect = new Rect();
//                v.getGlobalVisibleRect(viewRect);
//
//                // create Explode transition with epicenter
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    Transition explode = new Explode();
//                    explode.setEpicenterCallback(new Transition.EpicenterCallback() {
//                        @Override
//                        public Rect onGetEpicenter(Transition transition) {
//                            return viewRect;
//                        }
//                    });
//                    explode.setDuration(1000);
//                    TransitionManager.beginDelayedTransition(recyclerView, explode);
//                }
//
//
//                // remove all views from Recycler View
//                recyclerView.setAdapter(null);
//                return false;
//            }
//        });
        new TileColorData(tileList,tileAdapter,getActivity(),level);
        return rootView;
    }


}
