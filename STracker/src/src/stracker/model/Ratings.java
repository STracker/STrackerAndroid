package src.stracker.model;

public class Ratings {
	private double Rating;
	private int Total;
	
	public Ratings(double rating, int total) {
		super();
		Rating = rating;
		Total = total;
	}
	public double getRating() {
		return Rating;
	}
	public void setRating(double rating) {
		Rating = rating;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		Total = total;
	}
}
