package IMDb_simulation;

import java.util.List;

public class Movie implements Comparable<Movie>{
    private String title;
    private int releaseYear;
    private Genre genre;
    private List<Actor> castList;
    private Type type;
    private List<Review>  reviewList;
    private double reviewAverage;


    public Movie(String title, int releaseYear, Genre genre, List<Actor> castList, Type type, List<Review> reviewList) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.castList = castList;
        this.type = type;
        this.reviewList = reviewList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Actor> getCastList() {
        return castList;
    }

    public void setCastList(List<Actor> castList) {
        this.castList = castList;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public double getReviewAverage() {
        return reviewAverage;
    }

    public void setReviewAverage(double reviewAverage) {
        this.reviewAverage = getAverageReviewValueForMovie();
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre=" + genre +
                ", castList=" + castList +
                ", type=" + type +
                ", reviewList=" + reviewList +
                ", reviewAverage=" + reviewAverage +
                '}';
    }

    @Override
    public int compareTo(Movie anotherMovie) {
        return Integer.compare(releaseYear, anotherMovie.getReleaseYear());
    }

    //media review-rilor
    public double getAverageReviewValueForMovie() {
        int sum = 0;
        for (Review review : reviewList) {
            sum += review.reviewValue;
        }
        reviewAverage = sum / getReviewList().size();
        return  sum / getReviewList().size();
    }

    public double findReviews() {
        double review = getAverageReviewValueForMovie();
        int maxReview = 5;
        for (int i = 0; i < reviewList.size(); i++) {
            if (reviewList.get(i).reviewValue.equals(maxReview)) {
            }
        }
        return review;
    }

}
