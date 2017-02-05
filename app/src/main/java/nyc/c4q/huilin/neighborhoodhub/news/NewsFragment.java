package nyc.c4q.huilin.neighborhoodhub.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.huilin.neighborhoodhub.R;
import nyc.c4q.huilin.neighborhoodhub.news.model.Complaint;
import nyc.c4q.huilin.neighborhoodhub.news.model.ComplaintsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashiquechowdhury on 2/5/17.
 */
public class NewsFragment extends Fragment {
    private RecyclerView newsRecycler;
    private NewsAdapter newsAdapter;
    List<Complaint> complaintList;

    public static Fragment newInstance() {
        NewsFragment newFrag = new NewsFragment();
        return newFrag;
    }

    public NewsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        newsRecycler = (RecyclerView) view.findViewById(R.id.news_recycler);
        complaintList = new ArrayList<>();
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        newsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        callRetrofit();
    }

    private void callRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://data.cityofnewyork.us/")
                .build();

        ComplaintsService myService = retrofit.create(ComplaintsService.class);
        Call<List<Complaint>> myCall = myService.getComplaints();

        myCall.enqueue(new Callback<List<Complaint>>() {
            @Override
            public void onResponse(Call<List<Complaint>> call, Response<List<Complaint>> response) {
                List<Complaint> complaintList = response.body();
                newsAdapter = new NewsAdapter(complaintList);
                newsRecycler.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<List<Complaint>> call, Throwable t) {
            }
        });
    }
}
