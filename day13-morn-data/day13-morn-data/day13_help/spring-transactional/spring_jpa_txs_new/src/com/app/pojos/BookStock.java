package com.app.pojos;
import javax.persistence.*;

@Entity
@Table(name="book_stock")
public class BookStock {
	private String isbn;
	private int availableStock;
	public BookStock() {
		System.out.println("in cnstr of " + getClass().getName());
	}
	public BookStock(String isbn, int availableStock) {
		super();
		this.isbn = isbn;
		this.availableStock = availableStock;
	}
	@Id
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Column(name="stock")
	public int getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}
	@Override
	public String toString() {
		return "BookStock [isbn=" + isbn + ", availableStock=" + availableStock + "]";
	}
	
	

}
