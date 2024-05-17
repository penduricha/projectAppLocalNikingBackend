package entitiesDynamoDB;

import java.util.Objects;

public class Clothing {
	private int clothingId;
	private String clothingName;
	private int quantity;
	private double price;
	private int storeId;
	@Override
	public int hashCode() {
		return Objects.hash(clothingId, storeId);
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
		return clothingId == other.clothingId && storeId == other.storeId;
	}
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
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public Clothing(String clothingName, int quantity, double price, int storeId) {
		super();
		//this.clothingId = clothingId;
		this.clothingName = clothingName;
		this.quantity = quantity;
		this.price = price;
		this.storeId = storeId;
	}
	public Clothing() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Clothing [clothingId=" + clothingId + ", clothingName=" + clothingName + ", quantity=" + quantity
				+ ", price=" + price + ", storeId=" + storeId + "]";
	}
	
}
