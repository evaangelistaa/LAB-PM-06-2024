package com.example.praktikum_5.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.praktikum_5.DataSource;
import com.example.praktikum_5.PostinganAdapter;
import com.example.praktikum_5.R;
import com.example.praktikum_5.Sosialmedia;

public class HomeFragment extends Fragment {

    public static final String EXTRA_INSTAGRAM = "extra_instagram";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvPostingan = view.findViewById(R.id.rv_post);
        rvPostingan.setHasFixedSize(true);
        PostinganAdapter postinganAdapter = new PostinganAdapter(DataSource.sosialmedias);
        rvPostingan.setAdapter(postinganAdapter);

        ImageView iv_search = view.findViewById(R.id.IV_Search);
        ImageView iv_posting = view.findViewById(R.id.IV_Post);
        ImageView iv_profile = view.findViewById(R.id.IV_Profile);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Sosialmedia sosialmedia = bundle.getParcelable(EXTRA_INSTAGRAM);
            if (sosialmedia != null) {
                DataSource.sosialmedias.add(0, sosialmedia);
                postinganAdapter.notifyDataSetChanged();
            }
        }

        iv_search.setOnClickListener(v -> {
            SearchFragment searchFragment = new SearchFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, searchFragment)
                    .addToBackStack(null)
                    .commit();
        });

        iv_posting.setOnClickListener(v -> {
            PostinganFragment postinganFragment = new PostinganFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, postinganFragment)
                    .addToBackStack(null)
                    .commit();
        });

        iv_profile.setOnClickListener(v -> {
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, profileFragment)
                    .addToBackStack(null)
                    .commit();
        });

    }
}