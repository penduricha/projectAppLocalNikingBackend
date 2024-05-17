package test;

import dao.Clothing_DAO;
import dao.Store_DAO;

public class GetList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clothing_DAO clothing_DAO=new Clothing_DAO();
		Store_DAO store_DAO=new Store_DAO();
		System.out.println("List Clothing:");
		System.out.println(clothing_DAO.getListClothing_ToString());
		//Bogan
		System.out.println("List Clothing that name Bogan:");
		System.out.println(clothing_DAO.getListClothingByName_ToString("Bogan"));
		System.out.println("List Store:");
		System.out.println(store_DAO.getListStore_ToString());
		System.out.println("List Store that name Herbert:");
		System.out.println(store_DAO.getListStoreByName_Tostring("Herbert"));
	}

}
