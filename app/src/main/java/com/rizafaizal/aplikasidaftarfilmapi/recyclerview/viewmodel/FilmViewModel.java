package com.rizafaizal.aplikasidaftarfilmapi.recyclerview.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.rizafaizal.aplikasidaftarfilmapi.recyclerview.model.Film;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FilmViewModel extends ViewModel {

    private static final String API_KEY = "022df932950ade918fbf7c740d31e834";
    private MutableLiveData<ArrayList<Film>> listFilm = new MutableLiveData<>();

    //Movies
    public void setMovies(final String language) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Film> listItems = new ArrayList<>();
        String urlMovies = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language="+language;

        client.get(urlMovies, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject jObj = list.getJSONObject(i);
                        Film film = new Film();
                        film.setId(jObj.getInt("id"));
                        film.setTitle(jObj.getString("title"));
                        film.setRelease(jObj.getString("release_date"));
                        film.setOverview(jObj.getString("overview"));
                        film.setPopularity(jObj.getDouble("popularity"));
                        film.setScore(jObj.getDouble("vote_average"));
                        film.setPoster(jObj.getString("poster_path"));
                        listItems.add(film);
                    }
                    listFilm.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });

    }

    public LiveData<ArrayList<Film>> getMovies() {
        return listFilm;
    }

    //Tvshow
    public void setTvshow(final String language) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Film> listItems = new ArrayList<>();
        String urlMovies = "https://api.themoviedb.org/3/discover/tv?api_key="+API_KEY+"&language="+language;

        client.get(urlMovies, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject jObj = list.getJSONObject(i);
                        Film film = new Film();
                        film.setId(jObj.getInt("id"));
                        film.setTitle(jObj.getString("original_name"));
                        film.setRelease(jObj.getString("first_air_date"));
                        film.setOverview(jObj.getString("overview"));
                        film.setPopularity(jObj.getDouble("popularity"));
                        film.setScore(jObj.getDouble("vote_average"));
                        film.setPoster(jObj.getString("poster_path"));
                        listItems.add(film);
                    }
                    listFilm.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });

    }

    public LiveData<ArrayList<Film>> getTvshow() {
        return listFilm;
    }

}
