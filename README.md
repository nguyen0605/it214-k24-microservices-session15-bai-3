# Báo cáo: Triển khai Choreography Saga với Apache Kafka

## 1. Mô tả luồng sự kiện Choreography Saga
- **ConcertBookingService**: Nhận yêu cầu đặt vé từ khách hàng qua REST Controller, sau đó khởi tạo sự kiện và phát (publish) lên Kafka topic `concert-events` kèm theo `correlationId`.
- **SeatAssignmentService**: Đóng vai trò là Consumer lắng nghe topic `concert-events`. Khi nhận được thông điệp, dịch vụ này thực hiện giữ chỗ ghế (mô phỏng lưu DB), sau đó đóng gói dữ liệu vào sự kiện `SeatReserved` giữ nguyên `correlationId` và phát lên topic `seat-events`.
- **NotificationService**: Đóng vai trò là Consumer lắng nghe topic `seat-events`. Khi nhận được sự kiện, tiến hành đọc thông tin `correlationId` và email khách hàng để gửi thông báo xác nhận.

## 2. Cách truyền Correlation ID
- `correlationId` được khởi tạo tại `ConcertBookingService` từ thông tin yêu cầu ban đầu.
- Trường này được đưa vào cấu trúc JSON của event và truyền xuyên suốt qua các Kafka message giữa các topic (`concert-events` -> `seat-events`).
- Nhờ vậy, tất cả các service trong chuỗi Saga đều giữ lại được mã giao dịch này để phục vụ việc ghi log truy vết (tracing) mà không cần gọi API đồng bộ trực tiếp giữa các service.

## 3. Hướng dẫn cấu trúc dự án
Dự án bao gồm 3 Spring Boot microservice độc lập:
- `concert-booking-service`
- `seat-assignment-service`
- `notification-service`

## 4. Cấu hình Kafka Topics
- `concert-events`
- `seat-events`