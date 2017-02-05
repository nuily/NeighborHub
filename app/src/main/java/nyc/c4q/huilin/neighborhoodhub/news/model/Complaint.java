package nyc.c4q.huilin.neighborhoodhub.news.model;

/**
 * Created by ashiquechowdhury on 2/5/17.
 */

public class Complaint {
    String city;
    String complaint_type;
    String descriptor;
    String resolution_description;

    public String getResolution_description() {
        return resolution_description;
    }

    public String getComplaint_type() {
        return complaint_type;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public String getCity() {
        return city;
    }
}
