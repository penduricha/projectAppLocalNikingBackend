package testAWS;

import dao.Store_DAO;
import entities.*;
public class Update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Store_DAO store_DAO=new Store_DAO();
		System.out.println(store_DAO.getListStoreFromDynamoDB_ToString());
		/*
		Store storeUpdate=new Store("Unimama", 90.56, "1 NUL");
		storeUpdate.setStoreId(110);
		if(store_DAO.addDataDynamoDB(storeUpdate))
		{
			System.out.println("Thành công");
		}*/
	}

}
