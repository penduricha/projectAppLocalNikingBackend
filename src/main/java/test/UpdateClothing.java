package test;

import com.github.javafaker.Faker;

import dao.Clothing_DAO;
import entities.*;

public class UpdateClothing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Faker faker = new Faker();
		Clothing_DAO clothing_DAO=new Clothing_DAO();
		System.out.println("List Clothing:");
		System.out.println(clothing_DAO.getListClothing_ToString());
		Clothing clothingUpdate=new Clothing(faker.name().lastName(),1*10, 1*1000);		
		clothingUpdate.setClothingId(27);
		if(clothing_DAO.updateClothing(clothingUpdate))
		{
			System.out.println("List Clothing after updated:");
			System.out.println(clothing_DAO.getListClothing_ToString());
		}
	}

}
