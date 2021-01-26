package com.example.donacare.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.donacare.Adapter.HomeAdapter;
import com.example.donacare.Adapter.ViewPagerAdapter;
import com.example.donacare.Model.HomeModel;
import com.example.donacare.R;
import com.example.donacare.UI.DetailHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HomeModel> homeModels;
    ArrayAdapter adapter;
    HomeAdapter homeAdapter;
    CircleIndicator circleIndicator;
    Toolbar toolbar;
    SearchView searchView;
    ViewPager mViewPager;
    Button btnLhtSelengkapnya;

    // images array
    int[] images = {R.drawable.a1, R.drawable.a2};

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        circleIndicator = view.findViewById(R.id.indicator);
        mViewPager = view.findViewById(R.id.pager);
        recyclerView = view.findViewById(R.id.rvListHome);


        btnLhtSelengkapnya = (Button) view.findViewById(R.id.btnLhtSelengkapnya);
        btnLhtSelengkapnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailHome.class);
                startActivity(intent);
            }
        });

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(getActivity(), images);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
        circleIndicator.setViewPager(mViewPager);

        setHasOptionsMenu(true);

        homeModels = new ArrayList<>();
        getData();
        adapter = new ArrayAdapter<HomeAdapter>(getActivity(), R.layout.support_simple_spinner_dropdown_item);

        homeModels = new ArrayList<>();
        homeModels.add(new HomeModel("1", "Organisasi", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        homeModels.add(new HomeModel("2", "Kudus", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        homeModels.add(new HomeModel("3", "Jepara", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        homeModels.add(new HomeModel("4", "Semarang", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        homeModels.add(new HomeModel("5", "Rembang", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        homeModels.add(new HomeModel("6", "Jogjakarta", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));

        homeAdapter = new HomeAdapter(getContext(), homeModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);

        return view;
    }

    private void getData() {
        homeModels = new ArrayList<>();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                try {
                    recyclerView.setAdapter(homeAdapter);
                    homeAdapter.getFilter().filter(nextText);
                    for (HomeModel model : homeModels) {
                        Log.d("tes", ""+model.getTitle());
                    }
                } catch (Exception e) {
                    Log.d("error", "" + e.toString());
                }
                return false;
            }
        });
    }

}
