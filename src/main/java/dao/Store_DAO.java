package dao;

import java.lang.reflect.Type;
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
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import aws.AWS_Connect;
import database.Connect;
import database.Hibernate;

import entities.Store;

import i_dao.I_Store_DAO;
import jakarta.persistence.TypedQuery;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class Store_DAO implements I_Store_DAO {
	//List<Store> listStore = new ArrayList<>();
	private Connect con = new Connect();
	Hibernate hibernate = new Hibernate("dbsDistribution");
	// Cài thông tin aws
	String accessKey = "AKIA5FTZEKGZ4FSAM7GJ";
	String secretKey = "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";
	String region = "ap-southeast-1";
	String bucketName = "buckettqn";
	String tableName = "Store";
	/*
	 * String accessKey, String region, String secretKey, String bucketName, String
	 * tableName)
	 */
	AWS_Connect aws_Connect = new AWS_Connect(accessKey, region, secretKey, bucketName, tableName);

	public Store_DAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addStore(Store store) {
		try {
			hibernate.getTransaction().begin();
			// Kiểm tra xem khóa chính đã tồn tại hay chưa

			if (hibernate.getEntityManager().find(Store.class, store.getStoreId()) != null) {
				// Nếu ID đã tồn tại, không thêm vào cơ sở dữ liệu
				System.out.println("Khóa chính đã tồn tại trong cơ sở dữ liệu.");
				// return false;
			}
			// Nếu khóa chính chưa tồn tại, thực hiện thêm vào cơ sở dữ liệu
			hibernate.getEntityManager().persist(store);
			hibernate.getTransaction().commit();
			System.out.println("Thêm dữ liệu Store thành công! với " + store.getStoreId());
			return true;
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
	}

	@Override
	public List<Store> getListStore() {
		try {
			TypedQuery<Store> query = hibernate.getEntityManager().createQuery("SELECT s FROM Store s", Store.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			// herbinate.closeTransaction();
			return null;
		}
	}

	@Override
	public String getListStore_ToString() {
		// TODO Auto-generated method stub
		String s = "";
		for (Store store : getListStore()) {
			s += store + "\n";
		}
		return s;
	}

	@Override
	public boolean updateStore(Store store) {
		// TODO Auto-generated method stub
		// public Store(String storeName, double income, String address)
		try {
			hibernate.getTransaction().begin();
			// Lấy đối tượng từ cơ sở dữ liệu
			Store storeFind = hibernate.getEntityManager().find(Store.class, store.getStoreId());
			// Thực hiện các thay đổi trên đối tượng
			if (storeFind != null) {
				// public Store(String storeName, double income, String address)
				storeFind.setStoreName(store.getStoreName());
				storeFind.setIncome(store.getIncome());
				storeFind.setAddress(store.getAddress());

				hibernate.getEntityManager().merge(storeFind);
				hibernate.getTransaction().commit();
				System.out.println("Cập nhật Store thành công với id là: " + store.getStoreId());
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
			// entityManager.close();
		}
		return false;
	}

	@Override
	public List<Store> getListStoreByName(String storeName) {
		// TODO Auto-generated method stub
		try {
			// "SELECT s from Store s where s.storeName= :storeName"
			TypedQuery<Store> query = hibernate.getEntityManager()
					.createQuery("SELECT s from Store s where s.storeName= :storeName", Store.class);
			query.setParameter("storeName", storeName);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			// herbinate.closeTransaction();
			return null;
		}
	}

	@Override
	public Store findStore(int storeId) {
		// TODO Auto-generated method stub
		return hibernate.getEntityManager().find(Store.class, storeId);
	}

	@Override
	public String getListStoreByName_Tostring(String storeName) {
		String s = "";
		for (Store store : getListStoreByName(storeName)) {
			s += store + "\n";
		}
		return s;
	}

	/*
	 * // Xóa tất cả các mục trong bảng ScanResponse scanResponse =
	 * client.scan(ScanRequest.builder().tableName(tableName).build()); for
	 * (Map<String, AttributeValue> item : scanResponse.items()) { DeleteItemRequest
	 * deleteRequest = DeleteItemRequest.builder() .tableName(tableName) .key(item)
	 * .build(); client.deleteItem(deleteRequest); }
	 * 
	 * System.out.println("Đã xóa tất cả dữ liệu trong bảng DynamoDB.");
	 */
	@Override
	public boolean demployAllLocalToDynamoDB() {
		// TODO Auto-generated method stub
		return false;
	}
	/*
	 * try {
			Item item = new Item().withPrimaryKey("maSV", sinhVien.getMaSV())
					.withString("tenSV", sinhVien.getTenSV())
					.withString("diaChi", sinhVien.getDiaChi())
					.withDouble("diem", sinhVien.getDiem());
			PutItemSpec putItemSpec = new PutItemSpec().withItem(item).withReturnValues(ReturnValue.ALL_OLD); 
			aws_Connect.getTable().putItem(putItemSpec);
			System.out.println("Đã thêm sinh viên " + sinhVien.getMaSV() + " vào bảng DynamoDB");
			return true;
		} catch (Exception e) {
			System.err.println("Lỗi khi thêm sinh viên vào bảng DynamoDB: " + e.getMessage());
			return false;
		}
	 */
	@Override
	public boolean addDataDynamoDB(Store store) {
		// TODO Auto-generated method stub
		try {
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
	}
	/*
	 * try {
            // Tạo yêu cầu DeleteItem
			DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("student_id", student_id);
            aws_Connect.getTable().deleteItem(deleteItemSpec);
            // Thực hiện yêu cầu DeleteItem
            // Trả về 1 nếu xóa thành công
            return 1;
        } catch (Exception e) {
            // Xử lý lỗi nếu xóa không thành công
            e.printStackTrace();
            return 0;
        }
	 */
	@Override
	public boolean deleteDynamoDB(int storeId) {
		// TODO Auto-generated method stub
		try {
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
	}

	@Override
	public boolean updateDataDynamoDB() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getListStoreJson() throws DynamoDbException, DynamoDBMappingException {
		// TODO Auto-generated method stub
		// Gson gson = new Gson();
		String jsonStore = "[";
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
			jsonStore += json + ",\n";
		}
		// cắt 2 kí tự cuối
		jsonStore = jsonStore.substring(0, jsonStore.length() - 2);
		jsonStore = jsonStore + "]\n";
		return jsonStore;
	}

	@Override
	public List<Store> getListStoreFromDynamoDB()  {
		// TODO Auto-generated method stub
		List<Store> listStore = new ArrayList<>();
		Type listType = new TypeToken<List<Store>>() {}.getType();
		try
		{
			listStore= getGson().fromJson(getListStoreJson(), listType);
			return listStore;
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			return null;
		}	
	}

	@Override
	public Gson getGson() {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		return gson;
	}

	@Override
	public String getListStoreFromDynamoDB_ToString() {
		String s = "";
		for (Store store : getListStoreFromDynamoDB()) {
			s += store + "\n";
		}
		return s;
	}
	/*
	 * public Student get_1_StudentFromDynamoDB(String student_id) {
		// TODO Auto-generated method stub
		Item item = aws_Connect.getTable().getItem("student_id", student_id);
		if (item != null) {
            String name = item.getString("name");
            boolean gender = item.getBoolean("gender");
            int age = item.getInt("age");
            return new Student(student_id, name, gender, age);
            
        } else {
            System.out.println("Không tìm thấy sinh viên ");
            return null;
        }	
	}
	 */
	@Override
	public Store findStoreInDynamoDB(int storeId) throws DynamoDbException, DynamoDBMappingException{
		// TODO Auto-generated method stub
		Item item = aws_Connect.getTable().getItem("storeId", storeId);
		//public Store(String storeName, double income, String address)
		if(item!=null)
		{
			String storeName=item.getString("storeName");
			double income=item.getDouble("income");
			String address=item.getString("address");
			return new Store(storeName, income, address);
		}else
		{
			System.out.println("Không tìm thấy.");
	        return null;
		}
		
	}

	@Override
	public List<Store> getListStoreFromDynamoDBByName(String storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllDynamoDB()throws DynamoDbException,DynamoDBMappingException{
		// TODO Auto-generated method stub
		for (Store store : getListStoreFromDynamoDB()) {
			deleteDynamoDB(store.getStoreId());
			System.out.println("Đã xóa Store với id là: "+store.getStoreId());
		}
	}
}
