package EOfilm;

import EOfilm.movie;

public class cartitem
{
	private Double price;
	private int qty;
	movie movie;
	
	public cartitem(movie movie, Double price)
	{
		this.price = price;
		this.movie = movie;
		qty = 1;
	}
	
	public void setQty(int new_qty)
	{
		qty = new_qty;
	}
	public void addOne()
	{
		this.qty++;
	}
	
	public Double getPrice()
	{
		return this.price;
	}
	public int getQty()
	{
		return this.qty;
	}
	public String getMovieID()
	{
		return this.movie.getId();
	}
	
	public String getMovieTitle()
	{
		return this.movie.getTitle();
	}
	
}
