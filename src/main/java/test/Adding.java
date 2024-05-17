package test;

import com.github.javafaker.Faker;

import dao.Clothing_DAO;
import dao.Store_DAO;
import entities.*;
public class Adding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Faker faker = new Faker();
		Store_DAO store_DAO=new Store_DAO();
		Clothing_DAO clothing_DAO=new Clothing_DAO();
		//adding
		//public Store(String storeName, double income, String address)
		//public Clothing(String clothingName, int quantity, double price)
		
		for(int i=1;i<=10;i++)
		{
			Store store1=new Store(faker.name().firstName(), i*100, i+" Road Alulu.");
			Clothing clothing1=new Clothing(faker.name().lastName(), i*10, i*1000);
			Clothing clothing2=new Clothing(faker.name().lastName(), i*10, i*1000);
			store1.getListClothing().add(clothing1);
			clothing1.setStore(store1);
			store1.getListClothing().add(clothing2);
			clothing2.setStore(store1);
			//Thêm vào
			store_DAO.addStore(store1);
		}
		System.out.println("List Store:");
		System.out.println(store_DAO.getListStore_ToString());
		System.out.println("List Clothing:");
		System.out.println(clothing_DAO.getListClothing_ToString());
		System.out.println("Size clothing: "+clothing_DAO.getListClothing().size());
		//Clothing clothingAdd=new Clothing(faker.name().lastName(),1*10, 1*1000);
		/*
		int storeId=2;
		if(clothing_DAO.addClothing(clothingAdd, storeId))
		{
			System.out.println("Thành công:");
			System.out.println("List Clothing after adding:");
			System.out.println(clothing_DAO.getListClothing_ToString());
		}*/
		
		
	}

}
