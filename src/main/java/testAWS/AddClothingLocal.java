package testAWS;

import dao.Clothing_DAO;
import entities.Clothing;

public class AddClothingLocal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clothing_DAO clothing_DAO=new Clothing_DAO();
		Clothing clothing=new Clothing("Hang Minh",10, 10);
		clothing.setClothingId(3);
		if(clothing_DAO.addClothing(clothing, 1, 2))
		{
			System.out.println(clothing_DAO.getListClothing_ToString());
		}
	}

}
