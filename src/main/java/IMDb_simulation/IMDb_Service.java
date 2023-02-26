package IMDb_simulation;

import java.util.*;
import java.util.stream.Collectors;

public class IMDb_Service {
    private List<Movie> movieList;


    public IMDb_Service(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getFilmList() {
        return movieList;
    }

    public void setFilmList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public String toString() {
        return "IMDb_Service{" +
                "filmList=" + movieList +
                '}';
    }


    //Gruparea tuturor filmelor dupa gen
    public static Map<Genre, List<Movie>> getAllFilmsByGenre(List<Movie> movieList) {
        Map<Genre, List<Movie>> filmByGenre = new HashMap<>();
        for (Movie movie : movieList) {
            if (!filmByGenre.containsKey(movie.getGenre())) {
                List<Movie> list = new ArrayList<>();
                list.add(movie);
                filmByGenre.put(movie.getGenre(), list);
            } else {
                filmByGenre.get(movie.getGenre()).add(movie);
            }
        }
        return filmByGenre;
    }

    //Gasirea tuturor filmelor in care joaca un anumit actor
    public static List<String> getAllfilmsTitlesWithTheSameActor(Actor actor, List<Movie> movieList) {
        List<String> filmTitlesWithTheSameActor = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getCastList().contains(actor)) {
                filmTitlesWithTheSameActor.add(movieList.get(i).getTitle());
            }
        }
        return filmTitlesWithTheSameActor;
    }

    //Ordonarea filmelor dupa anul aparitiei
    public static List<Movie> getAllFilmsOrderedByYear(List<Movie> movieList) {
        List<Movie> movieByYear = new ArrayList<>();
        movieByYear.addAll(movieList);
        Collections.sort(movieByYear);

        //movieList.sort(Comparator.comparing(m -> m.getReleaseYear()));

        //Set<Movie> movieByYearSet = new TreeSet<>();
        //movieByYearSet.addAll(movieList);

        //movieList.stream().sorted().collect(Collectors.toList());

        return movieByYear;
    }

    //Ordonarea filmelor dupa review-uri, descrescator (de la media de review-uri cea mai buna in jos)
    public static List<String> getMoviesOrderedByAverageReview(List<Movie> movieList) {
       /* List<Movie> moviesOrderedByAverageReview = new ArrayList<>();
        moviesOrderedByAverageReview.addAll(movieList);
        moviesOrderedByAverageReview.sort(Comparator.comparingDouble(Movie::getAverageReviewValueForMovie));
       // movieList.sort((movie1, movie2)-> Double.valueOf(movie1.getAverageReviewValueForMovie()).compareTo(Double.valueOf(movie2.getAverageReviewValueForMovie())));
        return moviesOrderedByAverageReview;*/
        //return movieList.stream().sorted(Comparator.comparingDouble(Movie::getAverageReviewValueForMovie).reversed()).collect(Collectors.toList()); //var 2
        return movieList.stream().sorted(Comparator.comparingDouble(Movie::getAverageReviewValueForMovie).reversed()).map(movie -> movie.getTitle() + " " + movie.getAverageReviewValueForMovie()).collect(Collectors.toList()); //doar cu titluri
    }

    //Gasirea tuturor actorilor care au jucat in filme de un anumit tip si gen
    //lista de actori rezultat este un set
    //1. ma duc prin lista de filme
    //2. daca filmul curent are genul si tipul cautat
    //2.1 adaug toti actorii filmului in lista de actori rezultat
    public static Set<Actor> getAllActorsWithTheSameTypeOrGenre(Type type, Genre genre, List<Movie> movieList) {
        Map<Genre, List<Movie>> filmsByGenre = getAllFilmsByGenre(movieList);
        Set<Actor> actorsWIthTheSameTypeOrGenre = new HashSet<>();
        if (filmsByGenre.containsKey(genre)) {
            List<Movie> allFilmsFromSearchedGenre = filmsByGenre.get(genre);
            for (Movie movie : allFilmsFromSearchedGenre) {
                if (movie.getType().equals(type)) {
                    actorsWIthTheSameTypeOrGenre.addAll(movie.getCastList());
                }
            }
        }
        return actorsWIthTheSameTypeOrGenre;
    }

    //Gasirea celui mai apreciat film de un anumit tip si gen
    public static Double getTheMostAppreciatedMovieByTypeAndGenre(Movie movie, List<Movie> movieList) {
        double foundedApreciatedMovie = movie.findReviews();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).equals(movie.findReviews())) ;
        }
        return foundedApreciatedMovie;
    }
    //1. ma duc prin lista de filme
    //2. compar daca filmul curent are mai multe reviwuri de 5 stele decat un max
    //2.1 daca da, filmul curent este cel mai apreciat

    //fac in plus o metoda care accepta ca parametru un film si returneaza cate review-uri de 5 stele are
    public  Movie findReviews(Type typeFound, Genre genreFound) {
        Movie theMostAppreciatedMovieOfACertainTypeAndGenre = null;
        double max = 0;
        for (Movie movie : movieList) {
            if (movie.getGenre().equals(genreFound) && movie.getType().equals(typeFound) && movie.getReviewAverage() > max) {
                theMostAppreciatedMovieOfACertainTypeAndGenre = movie;
                max = movie.getAverageReviewValueForMovie();
            }
        }
        return theMostAppreciatedMovieOfACertainTypeAndGenre;
    }

    //Gasirea autorului care a jucat in cele mai multe filme
    //1. in cate filme a jucat fiecare actor - generez o mapa cu cheia autor si valoare numarul de filme in care a jucat, avand la dispozitie lista de filme
    //2. care e actorul care a jucat in cele mai multe
    public static Map<String, Integer> getNoOfMoviesByActor(List<Actor> castList) {
        Map<String, Integer> noOfMoviesByActor = new HashMap<>();
        for (int i = 0; i < castList.size(); i++) {
            String actor = castList.get(i).getName();
            if (!noOfMoviesByActor.containsKey(actor)) {
                noOfMoviesByActor.put(actor, 1);
            } else {
                noOfMoviesByActor.put(actor, noOfMoviesByActor.get(actor) + 1);
            }
        }
        return noOfMoviesByActor;
    }

    //metoda ajutatoare pt addReview()
    public Movie findMovieByName(String movieName) {
        Movie movie = null;
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getTitle().equals(movieName)) {
                movie = movieList.get(i);
            }
        }
        return movie;
    }

    //Cele mai popular film (bazat pe cat de des apar acele filme apar in listele de filme favorite ale utilizatorilor) *****
    //1. ma duc prin lista de utilizatori
    //2. ma duc prin lista de filme favorite de la fiecare utilizator
    //3. pun in mapa ca si cheie filmul curent si ca si valoare ii adaug 1
    //4. aflu valoare maxima din mapa

    public List<String> getFavoriteMovies(List<User> userList) {
        List<String> foundedFavoriteMovies = getMoviesOrderedByAverageReview(movieList);

        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < foundedFavoriteMovies.size(); j++) {
                Map<Movie, Integer> movieMap = new HashMap<>();

                if (foundedFavoriteMovies.contains(movieList.get(i).getTitle())) {
                    movieMap.put(movieList.get(i), movieMap.get(movieList.get(i)) + 1);
                } else {
                    movieMap.put(movieList.get(i), 1);
                }
            }
        }
        return foundedFavoriteMovies;
    }
}