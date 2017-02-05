package nyc.c4q.huilin.neighborhoodhub.news.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ashiquechowdhury on 2/5/17.
 */

public interface ComplaintsService {
    @GET("resource/fhrw-4uyv.json")
    Call<List<Complaint>> getComplaints();
}
