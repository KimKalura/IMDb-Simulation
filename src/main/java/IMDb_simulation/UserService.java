package IMDb_simulation;

public class UserService {
    private IMDb_Service imDb_service;

    public UserService(IMDb_Service imDb_service) {
        this.imDb_service = imDb_service;
    }

    public IMDb_Service getImDb_service() {
        return imDb_service;
    }

    public void setImDb_service(IMDb_Service imDb_service) {
        this.imDb_service = imDb_service;
    }


    //Adaugarea unui review la un film
    public void addReview(Review review, String movieName) {
        //1. gasesc filmul in lista de filme din imdb service
        Movie movie = imDb_service.findMovieByName(movieName);
        //2. adaug reviewul la lista de reviewuri a filmului gasit
        movie.getReviewList().add(review);
    }

    //Adaugarea unui film la o lista de filme favorite
    public User addMovieOnFavoriteMovieList(Movie movie, User user) {
        user.getFavoriteMovies().add(movie.getTitle());
        return user;
    }

}
