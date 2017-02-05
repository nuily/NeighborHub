package nyc.c4q.huilin.neighborhoodhub.model.CrierPosts;

import java.io.Serializable;

/**
 * Created by rook on 2/3/17.
 */

public class CrierPost implements Serializable {
    private String title;
    private String description;
    private String user;
    private int supporters;

    public CrierPost(String title, String description, String user, int supporters) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.supporters = supporters;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getSupporters() {
        return supporters;
    }

    public void setSupporters(int supporters) {
        this.supporters = supporters;
    }
}
