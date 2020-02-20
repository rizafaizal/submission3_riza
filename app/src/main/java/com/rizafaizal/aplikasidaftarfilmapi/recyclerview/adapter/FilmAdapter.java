package com.rizafaizal.aplikasidaftarfilmapi.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rizafaizal.aplikasidaftarfilmapi.R;
import com.rizafaizal.aplikasidaftarfilmapi.recyclerview.model.Film;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private ArrayList<Film> mData = new ArrayList<>();

    public FilmAdapter(ArrayList<Film> lisFilm) {
        this.mData = lisFilm;
    }

    //::::::OnItemClick
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setData(ArrayList<Film> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FilmAdapter.FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new FilmViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilmAdapter.FilmViewHolder holder, int position) {
        holder.bind(mData.get(position));

        //Event onClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(mData.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtRelease, txtOverview, txtScore;
        ImageView imgPoster;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtRelease = itemView.findViewById(R.id.txt_release);
            txtScore = itemView.findViewById(R.id.txt_score);
            txtOverview = itemView.findViewById(R.id.txt_overview);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }

        void bind(Film film) {
            txtTitle.setText(film.getTitle());
            txtRelease.setText(film.getRelease());
            txtScore.setText(String.valueOf(film.getScore()));
            txtOverview.setText(film.getOverview());
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w185"+film.getPoster())
                    .apply(new RequestOptions().override(350, 550))
                    .into(imgPoster);
        }
    }

    //Interface OnItemClick
    public interface OnItemClickCallback {
        void onItemClicked(Film data);
    }
}
