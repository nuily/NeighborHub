package nyc.c4q.huilin.neighborhoodhub.model;

/**
 * Created by huilin on 2/1/17.
 */

public class Users {
    String username;
    String name;
    String homeLocation;

    public Users() {

    }


    public Users(String username, String name, String homeLocation) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public void setName(String name) {
        this.name = name;
    }
}
