package IMDb_simulation;

import java.util.ArrayList;
import java.util.List;

public class MainFilm {
    public static void main(String[] args) {
        //ACTORS -- CAST LIST
        List<Actor> castList1 = new ArrayList<>();
        Actor actor1 = new Actor("Tom Hanks");
        Actor actor2 = new Actor("Leslie Zemeckis");
        Actor actor3 = new Actor("Michael Jeter");
        Actor actor4 = new Actor("Daryl Sabara");
        castList1.add(actor1);
        castList1.add(actor2);
        castList1.add(actor3);
        castList1.add(actor4);

        List<Actor> castList2 = new ArrayList<>();
        Actor actor5 = new Actor("Keanu Reevess");
        Actor actor6 = new Actor("Carrie-Anne Moss");
        Actor actor7 = new Actor("Laurence Fishburne");
        Actor actor8 = new Actor("Gloria Foster");
        castList2.add(actor5);
        castList2.add(actor6);
        castList2.add(actor7);
        castList2.add(actor8);

        List<Actor> castList3 = new ArrayList<>();
        Actor actor9 = new Actor("Rowan Atkinson");
        castList3.add(actor9);

        List<Actor> castList4 = new ArrayList<>();
        Actor actor10 = new Actor("Megumi Okina");
        Actor actor11 = new Actor("Junko Baileya");
        castList4.add(actor10);
        castList4.add(actor11);

        List<Actor> castList5 = new ArrayList<>();
        Actor actor12 = new Actor("Priyanka Chopra Jonas");
        Actor actor13 = new Actor("Jada Pinkett Smith");
        castList5.add(actor5);
        castList5.add(actor12);
        castList5.add(actor13);


        //REVIEW LIST
        List<Review> reviewList1 = new ArrayList<>();
        reviewList1.add(Review.FOUR_STARS);
        reviewList1.add(Review.THREE_STARS);
        reviewList1.add(Review.ONE_STAR);

        List<Review> reviewList2 = new ArrayList<>();
        reviewList2.add(Review.FIVE_STARS);
        reviewList2.add(Review.FOUR_STARS);

        List<Review> reviewList3 = new ArrayList<>();
        reviewList3.add(Review.FOUR_STARS);
        reviewList3.add(Review.THREE_STARS);
        reviewList3.add(Review.THREE_STARS);

        List<Review> reviewList4 = new ArrayList<>();
        reviewList4.add(Review.THREE_STARS);
        reviewList4.add(Review.TWO_STARS);
        reviewList4.add(Review.ONE_STAR);

        List<Review> reviewList5 = new ArrayList<>();
        reviewList5.add(Review.FIVE_STARS);
        reviewList5.add(Review.TWO_STARS);
        reviewList5.add(Review.ONE_STAR);


        // FILMS
        Movie movie1 = new Movie("Polar Express", 2004, Genre.FANTASY, castList1, Type.MOVIE, reviewList1);
        Movie movie2 = new Movie("Matrix", 1999, Genre.ACTION, castList2, Type.MOVIE, reviewList2);
        Movie movie3 = new Movie("Mr. Bean:The Animated Series", 2002, Genre.COMEDY, castList3, Type.TV_SHOW, reviewList3);
        Movie movie4 = new Movie("The Grudge", 2002, Genre.HORROR, castList4, Type.TV_SHOW, reviewList4);
        Movie movie5 = new Movie("The Matrix Resurrection", 2021, Genre.ACTION, castList5, Type.TV_SHOW, reviewList5);

        //LISTA FAVORITA DE FILME
        List<String> favoriteMovie1 = new ArrayList<>();
        List<String> favoriteMovie2 = new ArrayList<>();
        List<String> favoriteMovie3 = new ArrayList<>();

        //USERS
        User user1 = new User(favoriteMovie1);
        System.out.println(">> ");
        System.out.println(user1.getFavoriteMovies().add("Polar Express"));
        System.out.println(user1.getFavoriteMovies().add("The Grudge"));

        User user2 = new User(favoriteMovie2);
        System.out.println(user2.getFavoriteMovies().add("The Matrix Resurrection"));
        System.out.println(user2.getFavoriteMovies().add("Mr. Bean:The Animated Series"));

        User user3 = new User(favoriteMovie3);
        System.out.println(user3.getFavoriteMovies().add("Polar Express"));
        System.out.println(user3.getFavoriteMovies().add("The Grudge"));
        System.out.println(user3.getFavoriteMovies().add("The Matrix Resurrection"));

        //USERS LIST
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        System.out.println("USER1  " + user1);
        System.out.println("USER2  " + user2);
        System.out.println("USER3  " + user3);


        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        movieList.add(movie4);
        movieList.add(movie5);

        //METOHODS
        System.out.println(">> All Movies by Genre");
        System.out.println(IMDb_Service.getAllFilmsByGenre(movieList));

        System.out.println(">> All Movies with the same Actor");
        System.out.println(IMDb_Service.getAllfilmsTitlesWithTheSameActor(actor5, movieList));

        System.out.println(">> All Movies ordered by Year");
        System.out.println(IMDb_Service.getAllFilmsOrderedByYear(movieList));

        System.out.println(">> Sorting movies by reviews, descending");
        System.out.println(IMDb_Service.getMoviesOrderedByAverageReview(movieList));


        System.out.println(">> All Actors who played in movies with the same Type and Genre");
        System.out.println(IMDb_Service.getAllActorsWithTheSameTypeOrGenre(Type.MOVIE,Genre.FANTASY, movieList));

        System.out.println(">> Actor who played in the most films");
        System.out.println(IMDb_Service.getNoOfMoviesByActor(castList2));

        System.out.println(">> The most appreciated movie by type and genre:");
        System.out.println(IMDb_Service.getTheMostAppreciatedMovieByTypeAndGenre(movie1, movieList));
        //System.out.println(IMDb_Service.findReviews(Type.MOVIE, Genre.COMEDY));

        System.out.println(">> Find movie by name:");
        IMDb_Service imDb_service = new IMDb_Service(movieList);
        System.out.println(imDb_service.findMovieByName("Mr. Bean:The Animated Series"));

        UserService userService = new UserService(new IMDb_Service(movieList));
        System.out.println(">> Add a review:");
        userService.addReview(Review.FIVE_STARS, "The Grudge");
        System.out.println(movie4);

        System.out.println(">> Add movie on favorite movie list: " + userService.addMovieOnFavoriteMovieList(movie2, user1));

        System.out.println(">> Fav movie: " + imDb_service.getFavoriteMovies(userList));

    }

}
