package i_dao;

import java.util.List;

import com.google.gson.Gson;

import entities.*;



public interface I_Clothing_DAO {
	public boolean addClothing(Clothing clothing,int storeId,int clothingIdMax);
	public List<Clothing> getListClothing();
	public String getListClothing_ToString();
	public boolean updateClothing(Clothing clothing);
	public List<Clothing> getListClothingByName(String clothingName);
	public String getListClothingByName_ToString(String clothingName);
	public boolean deleteClothing(int clothingId);
	public Clothing findClothing(int clothingId);
	//aws
	public boolean demployAllLocalToDynamoDB();
	public boolean addDataDynamoDB(Clothing clothing,int storeId);
	public boolean deleteDynamoDB(int clothingId);
	//public boolean updateDataDynamoDB();
	public String getListClothingJson();
	public List<entitiesDynamoDB.Clothing> getListClothingFromDynamoDB();
	public String getListClothingFromDynamoDB_ToString();
	public Gson getGson();
	//xóa full dữ liệu
	public void deleteAllDynamoDB();
}
