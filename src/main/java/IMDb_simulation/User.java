package IMDb_simulation;

import java.util.List;

public class User {
    private List<String> favoriteMovies;


    public User(List<String> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public List<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(List<String> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    @Override
    public String toString() {
        return "User{" +
                "favoriteMovies=" + favoriteMovies +
                '}';
    }
}
