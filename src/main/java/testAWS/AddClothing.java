package testAWS;

import dao.Clothing_DAO;
import entities.*;
public class AddClothing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clothing_DAO clothing_DAO=new Clothing_DAO();
		System.out.println(clothing_DAO.getListClothingFromDynamoDB_ToString());
		Clothing clothing=new Clothing("Hang Minh",10, 10);
		clothing.setClothingId(1);
		
		
		if(clothing_DAO.addDataDynamoDB(clothing, 1))
		{
			System.out.println("Thêm thành công.");
		}
	}

}
