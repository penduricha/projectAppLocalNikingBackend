package test;

import dao.Store_DAO;
import entities.*;
public class Update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Store_DAO store_DAO=new Store_DAO();
		System.out.println("List Store:");
		System.out.println(store_DAO.getListStore_ToString());
		Store storeUpdate=new Store("Ben",1000.5,"4 Road Min");
		storeUpdate.setStoreId(10);
		if(store_DAO.updateStore(storeUpdate))
		{
			System.out.println("List Store After Updated:");
			System.out.println(store_DAO.getListStore_ToString());
		}
	}

}
