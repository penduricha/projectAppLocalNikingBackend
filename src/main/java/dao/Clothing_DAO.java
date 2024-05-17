package dao;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import aws.AWS_Connect;
import database.Connect;
import database.Hibernate;
import entities.*;
import i_dao.I_Clothing_DAO;
import jakarta.persistence.TypedQuery;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class Clothing_DAO implements I_Clothing_DAO {
	List<Clothing> listClothing = new ArrayList<>();
	private Connect con = new Connect();
	Hibernate hibernate=new Hibernate("dbsDistribution");
	Store_DAO store_DAO=new Store_DAO();
	
	String accessKey = "AKIA5FTZEKGZ4FSAM7GJ";
	String secretKey = "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";
	String region = "ap-southeast-1";
	String bucketName = "buckettqn";
	String tableName = "Clothing";
	/*
	 * String accessKey, String region, String secretKey, String bucketName, String
	 * tableName)
	 */
	AWS_Connect aws_Connect = new AWS_Connect(accessKey, region, secretKey, bucketName, tableName);
	public Clothing_DAO() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * String sqlAdd="insert into Milk(milkId,name,expireDate,price,star,storageId) values (?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement_Insert = con.con().prepareStatement(sqlAdd);
			preparedStatement_Insert.setInt(1, getListMilk().size()+1);
			preparedStatement_Insert.setNString(2, m.getName());
			preparedStatement_Insert.setDate(3,m.getExpireDate());
			preparedStatement_Insert.setDouble(4,m.getPrice());
			preparedStatement_Insert.setNString(5,m.getStar());
			preparedStatement_Insert.setInt(6,storageId);
			
			preparedStatement_Insert.executeUpdate();
			con.con().close();
			preparedStatement_Insert.close();
			System.out.println("Đã thêm Milk thành công");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
	 */
	@Override
	public boolean addClothing(Clothing clothing,int storeId,int clothingIdMax) {
		//public Clothing(String clothingName, int quantity, double price) 
		String sqlAdd="insert into Clothing(clothingId,clothingName,quantity,price,storeId) values (?,?,?,?,?)";
		Store storeFind= store_DAO.findStore(storeId);
		if(storeFind!=null)
		{
			try {
				PreparedStatement preparedStatement_Insert = con.con().prepareStatement(sqlAdd);
				preparedStatement_Insert.setInt(1, clothingIdMax+1);
				preparedStatement_Insert.setNString(2, clothing.getClothingName());
				preparedStatement_Insert.setInt(3, clothing.getQuantity());
				preparedStatement_Insert.setDouble(4, clothing.getPrice());		
				preparedStatement_Insert.setInt(5, storeId);	
				preparedStatement_Insert.executeUpdate();
				con.con().close();
				preparedStatement_Insert.close();
				System.out.println("Thêm dữ liệu Clothing thành công với: "+clothingIdMax+1);
				return true;
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println(e);
				//herbinate.closeTransaction();
				return false;
			}
		}else
		{
			System.out.println("Không tìm thấy.");
			return false;
		}
		
	}
	@Override
	public List<Clothing> getListClothing() {
		try {
			TypedQuery<Clothing> query = hibernate.getEntityManager().createQuery("SELECT s FROM Clothing s", Clothing.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			//herbinate.closeTransaction();
			return null;
		}
	}
	@Override
	public String getListClothing_ToString() {
		String s="";
		for(Clothing clothing: getListClothing())
		{
			s+=clothing+"\n";
		}
		return s;
	}
	/*
	 * public boolean updateStore(Store store) {
		// TODO Auto-generated method stub
		//public Store(String storeName, double income, String address)
		try {
			hibernate.getTransaction().begin();
			// Lấy đối tượng từ cơ sở dữ liệu
			Store storeFind = hibernate.getEntityManager().find(Store.class, store.getStoreId());
			// Thực hiện các thay đổi trên đối tượng
			if (storeFind != null) {
				//public Store(String storeName, double income, String address) 
				storeFind.setStoreName(store.getStoreName());
				storeFind.setIncome(store.getIncome());
				storeFind.setAddress(store.getAddress());
				
				hibernate.getEntityManager().merge(storeFind);
				hibernate.getTransaction().commit();
				System.out.println("Cập nhật Store thành công với id là: "+store.getStoreId());
				return true;
			} else {
				System.out.println("Không tìm thấy.");
				return false;
			}

		} catch (Exception e) {
			if (hibernate.getTransaction().isActive()) {
				hibernate.getTransaction().rollback();
			}
			e.printStackTrace();
			System.out.println(e);
		} finally {
			//entityManager.close();
		}
		return false;
	}
	 */
	@Override
	public boolean updateClothing(Clothing clothing) {
		// TODO Auto-generated method stub
		//Store storeFind= store_DAO.findStore(store.getStoreId());
		try {
			hibernate.getTransaction().begin();
			// Lấy đối tượng từ cơ sở dữ liệu
			Clothing clothingFind=findClothing(clothing.getClothingId());
			// Thực hiện các thay đổi trên đối tượng
			//public Clothing(String clothingName, int quantity, double price)
			if (clothingFind != null) {				
				clothingFind.setClothingName(clothing.getClothingName());
				clothingFind.setQuantity(clothing.getQuantity());
				clothingFind.setPrice(clothing.getPrice());
				//clothingFind.setStore(store);				
				hibernate.getEntityManager().merge(clothingFind);
				hibernate.getTransaction().commit();
				System.out.println("Cập nhật Clothing thành công với id là: "+clothing.getClothingId());
				return true;
			} else {
				System.out.println("Không tìm thấy.");
				return false;
			}
		}catch(Exception e) {
			if (hibernate.getTransaction().isActive()) {
				hibernate.getTransaction().rollback();
			}
			e.printStackTrace();
			System.out.println(e);
		}finally {
				//entityManager.close();
		}
		return false;	
	}
	/*
	 * // TODO Auto-generated method stub
		try {
			hibernate.getTransaction().begin();
			// Kiểm tra xem khóa chính đã tồn tại hay chưa
			if (findSinhVien(maSV) != null) {
				// Nếu ID đã tồn tại, không thêm vào cơ sở dữ liệu
				// Xóa
				hibernate.getEntityManager().remove(findSinhVien(maSV));
				hibernate.getTransaction().commit();
				System.out.println("Xóa dữ liệu thành công! với " + maSV);
				return true;
			}
		} catch (Exception e) {
			if (hibernate.getTransaction() != null && hibernate.getTransaction().isActive()) {
				hibernate.getTransaction().rollback();
				return false;
			}
			e.printStackTrace();
			System.out.println(e);
			// herbinate.closeTransaction();
		}
		return false;
	 */
	/*
	 * SẼ ẢNH HƯỞNG QUAN HỆ
	 * try {
			hibernate.getTransaction().begin();
			// Kiểm tra xem khóa chính đã tồn tại hay chưa
			if (findClothing(clothingId) != null) {
				// Nếu ID đã tồn tại, không thêm vào cơ sở dữ liệu
				// Xóa
				hibernate.getEntityManager().remove(findClothing(clothingId));
				hibernate.getTransaction().commit();
				System.out.println("Xóa dữ liệu thành công! với " + clothingId);
				return true;
			}
		} catch (Exception e) {
			if (hibernate.getTransaction() != null && hibernate.getTransaction().isActive()) {
				hibernate.getTransaction().rollback();
				return false;
			}
			e.printStackTrace();
			System.out.println(e);
			// herbinate.closeTransaction();
		}
		return false;
	 */
	@Override
	public boolean deleteClothing(int clothingId) {
		// TODO Auto-generated method stub
		String sqlDelete="delete from Clothing where clothingId= ?";
		try {
			PreparedStatement preparedStatement = con.con().prepareStatement(sqlDelete);
			preparedStatement.setInt(1, clothingId);		
			preparedStatement.executeUpdate();
			con.con().close();
			preparedStatement.close();
			System.out.println("Đã xóa Clothing thành công");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
	}
	@Override
	public Clothing findClothing(int clothingId) {
		// TODO Auto-generated method stub
		return hibernate.getEntityManager().find(Clothing.class,clothingId);
	}
	@Override
	public List<Clothing> getListClothingByName(String clothingName) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Clothing> query = hibernate.getEntityManager().createQuery("SELECT c FROM Clothing c where c.clothingName= :clothingName", Clothing.class);
			query.setParameter("clothingName", clothingName);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			//herbinate.closeTransaction();
			return null;
		}
	}
	@Override
	public String getListClothingByName_ToString(String clothingName) {
		String s="";
		for(Clothing clothing: getListClothingByName(clothingName))
		{
			s+=clothing+"\n";
		}
		return s;
	}
	@Override
	public boolean demployAllLocalToDynamoDB() {
		// TODO Auto-generated method stub
		return false;
	}
	/*
	 * try {
			Item item = new Item().withPrimaryKey("storeId", store.getStoreId())
					.withString("storeName", store.getStoreName())
					.withDouble("income", store.getIncome())
					.withString("address", store.getAddress());
			PutItemSpec putItemSpec = new PutItemSpec().withItem(item).withReturnValues(ReturnValue.ALL_OLD); 
			aws_Connect.getTable().putItem(putItemSpec);
			System.out.println("Đã thêm store vào bảng DynamoDB");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Lỗi khi thêm vào bảng DynamoDB: " + e.getMessage());
			return false;
		}
	 */
	@Override
	public boolean addDataDynamoDB(Clothing clothing,int storeId) {
		// TODO Auto-generated method stub
		Store storeFind=store_DAO.findStoreInDynamoDB(storeId);
		if(storeFind!=null)
		{
			try {
				Item item = new Item().withPrimaryKey("clothingId",clothing.getClothingId())
						.withString("clothingName", clothing.getClothingName())
						.withInt("quantity", clothing.getQuantity())
						.withDouble("price", clothing.getPrice())
						.withInt("storeId", storeId);
				PutItemSpec putItemSpec = new PutItemSpec().withItem(item).withReturnValues(ReturnValue.ALL_OLD); 
				aws_Connect.getTable().putItem(putItemSpec);
				System.out.println("Đã thêm Clothing vào bảng DynamoDB");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Lỗi khi thêm vào bảng DynamoDB: " + e.getMessage());
				return false;
			}
		}else
		{
			System.out.println("Can't find Store.");
			return false;
		}
	}
	/*
	 * try {
            // Tạo yêu cầu DeleteItem
			DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("storeId", storeId);
            aws_Connect.getTable().deleteItem(deleteItemSpec);
            System.out.println("Xóa bảng Store thành công.");
            // Thực hiện yêu cầu DeleteItem
            // Trả về 1 nếu xóa thành công
            return true;
        } catch (DynamoDbException e) {
            // Xử lý lỗi nếu xóa không thành công
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
	 */
	@Override
	public boolean deleteDynamoDB(int clothingId) {
		// TODO Auto-generated method stub
		try {
            // Tạo yêu cầu DeleteItem
			DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("clothingId", clothingId);
            aws_Connect.getTable().deleteItem(deleteItemSpec);
            System.out.println("Xóa bảng Clothing thành công.");
            // Thực hiện yêu cầu DeleteItem
            // Trả về 1 nếu xóa thành công
            return true;
        } catch (DynamoDbException e) {
            // Xử lý lỗi nếu xóa không thành công
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
	}
	@Override
	public String getListClothingJson()throws DynamoDbException, DynamoDBMappingException {
		// TODO Auto-generated method stub
		String jsonClothing = "[";
		String[] jsonArray = new String[aws_Connect.getScanResponse().items().size()];
		// Chuyển đổi kết quả thành định dạng JSON
		int i = 0;
		for (Map<String, AttributeValue> item : aws_Connect.getScanResponse().items()) {
			Map<String, Object> jsonMap = new HashMap<>();
			for (Map.Entry<String, AttributeValue> entry : item.entrySet()) {
				String attributeName = entry.getKey();
				AttributeValue attributeValue = entry.getValue();
				Object value = null;
				// Chuyển đổi giá trị của thuộc tính thành kiểu dữ liệu Java tương ứng
				if (attributeValue.s() != null) {
					value = attributeValue.s();
				} else if (attributeValue.n() != null) {
					value = attributeValue.n();
				} else if (attributeValue.bool() != null) {
					value = attributeValue.bool();
				}
				// Thêm thuộc tính và giá trị tương ứng vào đối tượng JSON
				jsonMap.put(attributeName, value);
			}
			// Chuyển đổi đối tượng JSON thành chuỗi JSON
			jsonArray[i] = getGson().toJson(jsonMap);
			i++;
		}
		// In ra mảng JSON
		for (String json : jsonArray) {
			jsonClothing += json + ",\n";
		}
		// cắt 2 kí tự cuối
		jsonClothing = jsonClothing.substring(0, jsonClothing.length() - 2);
		jsonClothing = jsonClothing + "]\n";
		return jsonClothing;
	}
	@Override
	public List<entitiesDynamoDB.Clothing> getListClothingFromDynamoDB() {
		// TODO Auto-generated method stub
		List<entitiesDynamoDB.Clothing> listClothing = new ArrayList<>();
		Type listType = new TypeToken<List<entitiesDynamoDB.Clothing>>() {}.getType();
		try
		{
			listClothing= getGson().fromJson(getListClothingJson(), listType);
			return listClothing;
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			return null;
		}
	}
	@Override
	public String getListClothingFromDynamoDB_ToString() {
		String s = "";
		for (entitiesDynamoDB.Clothing clothing : getListClothingFromDynamoDB()) {
			s += clothing+ "\n";
		}
		return s;
	}
	@Override
	public Gson getGson() {
		Gson gson=new Gson();
		return gson;
	}
	@Override
	public void deleteAllDynamoDB()throws DynamoDbException,DynamoDBMappingException {
		// TODO Auto-generated method stub
		for(entitiesDynamoDB.Clothing clothing: getListClothingFromDynamoDB())
		{
			deleteDynamoDB(clothing.getClothingId());
			System.out.println("Đã xóa Clothing với id là: "+clothing.getClothingId());
		}
	}
}
