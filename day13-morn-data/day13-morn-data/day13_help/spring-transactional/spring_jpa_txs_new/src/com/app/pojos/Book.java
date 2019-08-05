package com.app.pojos;
import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {
	private String isbn, name;
	private double price;

	public Book() {
		System.out.println("in cnstr of " + getClass().getName());
	}

	public Book(String isbn, String name, double price) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.price = price;
	}
	@Id
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Column(name="book_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", name=" + name + ", price=" + price + "]";
	}
}
