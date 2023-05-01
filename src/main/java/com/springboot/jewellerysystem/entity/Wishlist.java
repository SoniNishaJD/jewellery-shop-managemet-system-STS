package com.springboot.jewellerysystem.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "wishlist")
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	@Basic
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "entry_date", nullable = false)
	private Date entryDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Wishlist other = (Wishlist) obj;
		return Objects.equals(id, other.id) && Objects.equals(entryDate, other.entryDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, entryDate);
	}

	public Wishlist() {
		super();
	}

	public Wishlist(Integer id, Product product, User user, Date entryDate) {
		super();
		this.id = id;
		this.product = product;
		this.user = user;
		this.entryDate = entryDate;
	}
	public Wishlist(Product product, User user, Date entryDate) {
		super();
		
		this.product = product;
		this.user = user;
		this.entryDate = entryDate;
	}
	public Wishlist(Date entryDate) {
		super();
		this.entryDate = entryDate;
	}

	@Override
	public String toString() {
		return "Wishlist [id=" + id + ", entryDate=" + entryDate + "]";
	}
}
