package test;

import dao.Clothing_DAO;
import dao.Store_DAO;

public class Query {
	public static void main(String[] args) {
		Store_DAO store_DAO=new Store_DAO();
		Clothing_DAO clothing_DAO=new Clothing_DAO();
		System.out.println("List");
		System.out.println(store_DAO.getListStoreByName_Tostring("Ben"));
		System.out.println(clothing_DAO.getListClothingByName_ToString("Dicki"));
	}
}
