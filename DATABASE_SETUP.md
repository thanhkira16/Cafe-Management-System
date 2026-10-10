# Hướng dẫn cài đặt database

File SQL của dự án: [`database/cafe_management.sql`](database/cafe_management.sql).

Script tạo database `cms`, các bảng `user`, `category`, `product`, `bill` và dữ liệu mẫu gồm 5 danh mục, 25 món uống, 18 hóa đơn cùng tài khoản quản trị demo. Có thể chạy lại script: dữ liệu mẫu không bị nhân đôi và script không xóa dữ liệu hiện có.

## Yêu cầu

- MySQL Server 8.x đã cài đặt và đang chạy.
- Tài khoản MySQL có quyền tạo database và bảng (ví dụ `root`).
- Dùng MySQL Command Line Client (`mysql`) hoặc MySQL Workbench.

## Cách 1: Import bằng PowerShell

Mở PowerShell tại thư mục dự án, chạy:

```powershell
Get-Content .\database\cafe_management.sql | mysql -u root -p
```

Nhập mật khẩu MySQL khi được hỏi. Nếu lệnh `mysql` không được nhận diện, thêm thư mục `bin` của MySQL vào `PATH`, hoặc chạy bằng đường dẫn đầy đủ, ví dụ:

```powershell
Get-Content .\database\cafe_management.sql | & 'C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe' -u root -p
```

## Cách 2: Import bằng MySQL Workbench

1. Khởi động MySQL Server và kết nối tới server trong Workbench.
2. Chọn **File → Open SQL Script**, mở `database/cafe_management.sql` trong thư mục dự án.
3. Nhấn nút tia sét **Execute** để chạy script.
4. Làm mới mục **Schemas**. Database `cms` sẽ có các bảng `user`, `category`, `product`, `bill`.

## Kiểm tra dữ liệu

Chạy các câu lệnh sau trong Workbench hoặc MySQL Client:

```sql
USE cms;
SELECT COUNT(*) AS categories FROM category;
SELECT COUNT(*) AS products FROM product;
SELECT COUNT(*) AS sample_bills FROM bill;
SELECT email, status FROM `user` WHERE email = 'admin@cafe.local';
```

Với database mới, số lượng dự kiến là 5 danh mục, 25 sản phẩm và 18 hóa đơn. Tài khoản đăng nhập demo:

- Email: `admin@cafe.local`
- Mật khẩu: `admin123`

Ứng dụng phiên bản cũ lưu mật khẩu bằng MD5; tài khoản mẫu đã dùng hash tương thích.

## Cấu hình kết nối cho ứng dụng

Mặc định ứng dụng kết nối tới `localhost:3306`, database `cms`, user `root`, mật khẩu rỗng. Nếu MySQL của bạn có mật khẩu hoặc tài khoản khác, trong PowerShell đặt biến môi trường trước khi chạy:

```powershell
$env:CAFE_DB_URL = 'jdbc:mysql://localhost:3306/cms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Ho_Chi_Minh'
$env:CAFE_DB_USER = 'root'
$env:CAFE_DB_PASSWORD = 'mat-khau-mysql'
.\run.cmd
```

Trong CMD:

```bat
set CAFE_DB_URL=jdbc:mysql://localhost:3306/cms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Ho_Chi_Minh
set CAFE_DB_USER=root
set CAFE_DB_PASSWORD=mat-khau-mysql
run.cmd
```

Trong VS Code, sửa `CAFE_DB_USER` và `CAFE_DB_PASSWORD` trong phần `env` của `.vscode/launch.json`, rồi chọn **Run and Debug → Cafe Management System**. Không commit mật khẩu database thật lên Git.

## Lỗi thường gặp

- **Connection refused / communications link failure:** kiểm tra MySQL Server đã chạy và URL có đúng host, port không.
- **Access denied:** kiểm tra username, password và quyền kết nối từ `localhost`.
- **Unknown database `cms`:** import file SQL trước khi chạy ứng dụng.
- **`mysql` is not recognized:** cài MySQL Command Line Client, thêm thư mục `bin` vào `PATH`, dùng đường dẫn đầy đủ như ví dụ, hoặc import bằng Workbench.
