package com.ermile.salamquran.android.salamquran;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ermile.salamquran.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends Fragment {


  public LearnFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_learn, container, false);
  }

}
