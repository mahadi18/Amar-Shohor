package com.cyclic_order.amarshohor.SlidingActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyclic_order.amarshohor.R;

public class AboutFragment extends Fragment {
	
	public AboutFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.about, container, false);
         
        return rootView;
    }
}
