package entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.*;

@Entity
@Table(name = "Store")
public class Store {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId")
	private int storeId;
	@Column(name = "storeName", length = 100, columnDefinition = "nvarchar(25)",nullable = false)
	private String storeName;
	@Column(name = "income", length = 100, nullable = false)
	private double income;
	@Column(name = "address", length = 100, columnDefinition = "nvarchar(50)",nullable = false)
	private String address;
	//Map with Store
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
	private List<Clothing> listClothing=new ArrayList<>();
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Clothing> getListClothing() {
		return listClothing;
	}
	public void setListClothing(List<Clothing> listClothing) {
		this.listClothing = listClothing;
	}
	@Override
	public int hashCode() {
		return Objects.hash(storeId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		return storeId == other.storeId;
	}
	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Store(String storeName, double income, String address) {
		super();
		this.storeName = storeName;
		this.income = income;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", storeName=" + storeName + ", income=" + income + ", address=" + address
				+ "]";
	}
	
}
