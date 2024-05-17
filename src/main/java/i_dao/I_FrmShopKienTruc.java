package i_dao;

public interface I_FrmShopKienTruc {
	public void addEvent();
	//method Store
	public void renderTableStore();
	public void addDataStore();
	public void deleteDataStore();
	public void updateDataStore();
	public boolean validateInputStore();
	public void renderTableStoreFindByName();
	//method Clothing
	public void renderTableClothing();
	public void addDataClothing();
	public void updateDataClothing();
	public boolean validateInputClothing();
	public void renderTableClothingFindByName();
	public void deleteDataClothing();
	//demploy
	public void demployStore();
	public void demployClothing();
	//reset components
	public void resetStore();
	public void resetClothing();
	//size table
	public int getClothingIdMax();
}
