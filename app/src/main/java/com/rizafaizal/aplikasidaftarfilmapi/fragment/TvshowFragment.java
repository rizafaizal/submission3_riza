package com.rizafaizal.aplikasidaftarfilmapi.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rizafaizal.aplikasidaftarfilmapi.R;
import com.rizafaizal.aplikasidaftarfilmapi.activity.DetailActivity;
import com.rizafaizal.aplikasidaftarfilmapi.recyclerview.adapter.FilmAdapter;
import com.rizafaizal.aplikasidaftarfilmapi.recyclerview.model.Film;
import com.rizafaizal.aplikasidaftarfilmapi.recyclerview.viewmodel.FilmViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvshowFragment extends Fragment {
    private FilmAdapter adapter;
    private ProgressBar progressBar;

    private FilmViewModel filmViewModel;

    //ArrayList
    private ArrayList<Film> list = new ArrayList<>();

    public TvshowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar_tvshow);

        RecyclerView recyclerView = view.findViewById(R.id.rc_tvshow);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FilmAdapter(list);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        //ItemClick
        adapter.setOnItemClickCallback(new FilmAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Film data) {
                showSelectedMovies(data);
            }
        });

        String bahasa = getString(R.string.language);

        filmViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(FilmViewModel.class);
        filmViewModel.setTvshow(bahasa);
        showLoading(true);

        filmViewModel.getTvshow().observe(this, new Observer<ArrayList<Film>>() {
            @Override
            public void onChanged(ArrayList<Film> weatherItems) {
                if (weatherItems != null) {
                    adapter.setData(weatherItems);
                    showLoading(false);
                }
            }
        });

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void showSelectedMovies(Film film) {
        Toast.makeText(getContext(), film.getTitle(), Toast.LENGTH_SHORT).show();

        Intent moveToDetail = new Intent(getContext(), DetailActivity.class);
        moveToDetail.putExtra(DetailActivity.EXTRA_FILMS, film);
        startActivity(moveToDetail);
    }
}
