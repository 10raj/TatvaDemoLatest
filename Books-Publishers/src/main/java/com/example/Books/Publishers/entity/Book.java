package com.example.Books.Publishers.entity;

  

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String edition;
	private String category;
	private Double price;
	private Long isbn;
	private String authorNames;
	private Long readerId;
	private Date isuueDate;
	private Date returnDate;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH})
	@JoinColumn(name = "publisher_id", referencedColumnName = "id")
	@JsonIgnore
	private Publisher publisher;
	
}
