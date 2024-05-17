package testAWS;

import dao.Clothing_DAO;
import dao.Store_DAO;
import entities.*;
public class TestJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Store_DAO store_DAO=new Store_DAO();
		Clothing_DAO clothing_DAO=new Clothing_DAO();
		System.out.println(store_DAO.getListStoreJson());
		System.out.println(store_DAO.getListStoreFromDynamoDB_ToString());
		System.out.println(clothing_DAO.getListClothingJson());
		System.out.println(clothing_DAO.getListClothingFromDynamoDB_ToString());
		
		//Store storeAdd=new Store("Uni", 90.56, "1 NUL");
		//storeAdd.setStoreId(101);
		/*
		if(store_DAO.addDataDynamoDB(storeAdd))
		{
			System.out.println("List after added:");
			System.out.println(store_DAO.getListStoreFromDynamoDB());
		}*/
		
		
	}

}
