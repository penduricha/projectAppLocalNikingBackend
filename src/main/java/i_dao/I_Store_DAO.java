package i_dao;
import entities.*;
import java.util.*;

import com.google.gson.Gson;
public interface I_Store_DAO {
	public boolean addStore(Store store);
	public List<Store> getListStore();
	public String getListStore_ToString();
	public boolean updateStore(Store store);
	public List<Store> getListStoreByName(String storeName);
	public String getListStoreByName_Tostring(String storeName);
	public Store findStore(int storeId);
	//AWS
	public boolean demployAllLocalToDynamoDB();
	public boolean addDataDynamoDB(Store store);
	public boolean deleteDynamoDB(int storeId);
	public boolean updateDataDynamoDB();
	public String getListStoreJson();
	public List<Store> getListStoreFromDynamoDB();
	public String getListStoreFromDynamoDB_ToString();
	public Gson getGson();
	public Store findStoreInDynamoDB(int storeId);
	public List<Store> getListStoreFromDynamoDBByName(String storeId);
	public void deleteAllDynamoDB();
}
