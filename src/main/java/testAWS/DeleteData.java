package testAWS;

import dao.Store_DAO;

public class DeleteData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Store_DAO store_DAO=new Store_DAO();
		System.out.println(store_DAO.getListStoreJson());
		System.out.println(store_DAO.getListStoreFromDynamoDB_ToString());
		/*
		if(store_DAO.deleteDynamoDB(110))
		{
			System.out.println("List after Deleted:");
			System.out.println(store_DAO.getListStoreFromDynamoDB_ToString());
		}else
		{
			System.out.println("Không thể tìm thấy.");
		}*/
	}

}
