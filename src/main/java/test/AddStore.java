package test;

import com.github.javafaker.Faker;

import dao.Store_DAO;
import entities.*
;public class AddStore {
	public static void main(String[] args) {
		Faker faker = new Faker();
		Store_DAO store_DAO=new Store_DAO();
		Store storeAdd=new Store(faker.name().firstName(), 100, " Road Alulu.");
		if(store_DAO.addStore(storeAdd))
		{
			System.out.println("List Store:");
			System.out.println(store_DAO.getListStore_ToString());
		}
	}
}
