package nyc.c4q.huilin.neighborhoodhub.model;

/**
 * Created by huilin on 2/1/17.
 */

public class Posts {
    String timestamp;
    String content;
    String poster;

    public Posts() {

    }

    public Posts(String timestamp, String content, String poster) {

        this.timestamp = timestamp;
        this.content = content;
        this.poster = poster;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
