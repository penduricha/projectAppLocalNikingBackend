Vào thư mục database.Connect sửa đường dẫn.
import maven từ pom.xml
Cài đặt maria
Tại 3 dòng 
String url = "jdbc:mariadb://localhost:3306/dbsDistribution";
String username = "root";
String password = "123";
Sử cổng localhost, username, password theo data source local của Maria
Vào mục resource -> META-INF -> persistence.xml
Mở thẻ create để cho tạo bảng
"jdbc:mariadb://localhost:3306/dbsDistribution"
tìm đường dẫn trên và sửa tương tự
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="123"/>
Sử tên và mật khẩu theo data src.
Vào Frm để chạy

