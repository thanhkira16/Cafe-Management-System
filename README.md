# Cafe Management System

Ứng dụng desktop Java Swing quản lý danh mục, sản phẩm, người dùng và hóa đơn. Dự án có thể mở trực tiếp bằng VS Code; cấu hình NetBeans cũ được giữ lại để tham khảo.

## Yêu cầu

- JDK 17 trở lên
- Apache Maven 3.8 trở lên
- MySQL 8.x đang chạy
- VS Code với Extension Pack for Java

## Cài đặt cơ sở dữ liệu và dữ liệu mẫu

Xem hướng dẫn đầy đủ tại [`DATABASE_SETUP.md`](DATABASE_SETUP.md). File SQL để tạo schema và import dữ liệu mẫu nằm tại [`database/cafe_management.sql`](database/cafe_management.sql).

Tóm tắt: mở PowerShell ở thư mục dự án và chạy `Get-Content .\database\cafe_management.sql | mysql -u root -p`, hoặc mở file SQL bằng MySQL Workbench và thực thi. Script tạo database `cms` cùng 5 danh mục, 25 món uống và 18 hóa đơn mẫu.

Tài khoản demo: `admin@cafe.local` / `admin123`.

## Chạy trong VS Code

1. Mở thư mục dự án trong VS Code và cài **Extension Pack for Java**.
2. Chờ Java/Maven tải các thư viện ở `pom.xml`.
3. Chọn **Run and Debug** → **Cafe Management System**.

Hoặc chạy từ terminal:

```powershell
mvn clean compile exec:java
```

Trong CMD hoặc PowerShell, chạy `run.cmd` ở thư mục dự án (PowerShell dùng `.\run.cmd`). File ưu tiên JDK 21 ở `C:\Program Files\Java\jdk-21`, kể cả khi `JAVA_HOME` đang trỏ tới phiên bản cũ, sau đó gọi Maven.

## Cấu hình kết nối MySQL

Cấu hình mặc định: `localhost:3306`, database `cms`, user `root`, mật khẩu trống. Có thể đặt biến môi trường trước khi khởi chạy:

```powershell
$env:CAFE_DB_URL = 'jdbc:mysql://localhost:3306/cms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Ho_Chi_Minh'
$env:CAFE_DB_USER = 'root'
$env:CAFE_DB_PASSWORD = 'mat-khau-mysql'
mvn clean compile exec:java
```

VS Code launch config nằm trong `.vscode/launch.json`; sửa các giá trị `env` ở đó nếu cần.

## Ghi chú khôi phục

Các lớp DAO đã được phục hồi từ lịch sử Git của dự án. Script SQL tái tạo schema theo các cột mà DAO/giao diện đang sử dụng. Đăng nhập và đăng ký dùng database trực tiếp; tài khoản đăng ký mới ở trạng thái chờ duyệt. Các tính năng chat/server thuộc source cũ vẫn cần server riêng nếu muốn sử dụng.
