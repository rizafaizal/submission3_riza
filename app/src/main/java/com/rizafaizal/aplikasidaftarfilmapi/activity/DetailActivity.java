package com.rizafaizal.aplikasidaftarfilmapi.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rizafaizal.aplikasidaftarfilmapi.R;
import com.rizafaizal.aplikasidaftarfilmapi.recyclerview.model.Film;

public class DetailActivity extends AppCompatActivity {
    ImageView imgFilm;
    TextView txtJudulFilm, txtReleaseFilm, txtScoreFilm, txtDescFilm;
    private ProgressBar progressBar;

    public static final String EXTRA_FILMS= "extra_films";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgFilm = findViewById(R.id.img_photo_detail);
        txtJudulFilm = findViewById(R.id.txt_judul_detail);
        txtReleaseFilm = findViewById(R.id.txt_release_detail);
        txtScoreFilm = findViewById(R.id.txt_score_detail);
        txtDescFilm = findViewById(R.id.txt_desc_detail);
        progressBar = findViewById(R.id.progressBar_detail);

        showLoading(true);

        Film films = getIntent().getParcelableExtra(EXTRA_FILMS);
        if (films != null) {
            showLoading(false);
            txtJudulFilm.setText(films.getTitle());
            txtReleaseFilm.setText(films.getRelease());
            txtScoreFilm.setText(String.valueOf(films.getScore()));
            txtDescFilm.setText(films.getOverview());
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w780"+films.getPoster())
                    .apply(new RequestOptions().override(950, 900))
                    .into(imgFilm);
        }

        //Nama Bar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getResources().getString(R.string.bar_detail_film));
        ab.setSubtitle(films.getTitle());
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
