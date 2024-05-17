package entities;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clothing")
public class Clothing {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothingId")
	private int clothingId;
	@Column(name = "clothingName", length = 100, columnDefinition = "nvarchar(25)",nullable = false)
	private String clothingName;
	@Column(name = "quantity", length = 100, nullable = false)
	private int quantity;
	@Column(name = "price", length = 100, nullable = false)
	private double price;
	//Map with Store
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "storeId")
	private Store store;
	public int getClothingId() {
		return clothingId;
	}
	public void setClothingId(int clothingId) {
		this.clothingId = clothingId;
	}
	public String getClothingName() {
		return clothingName;
	}
	public void setClothingName(String clothingName) {
		this.clothingName = clothingName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clothingId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clothing other = (Clothing) obj;
		return clothingId == other.clothingId;
	}
	public Clothing() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Clothing(String clothingName, int quantity, double price) {
		super();
		this.clothingName = clothingName;
		this.quantity = quantity;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Clothing [clothingId=" + clothingId + ", clothingName=" + clothingName + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
}
