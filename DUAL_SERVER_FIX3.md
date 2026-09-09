# FIX3 — Dual Server Picker

Màn **Chọn máy chủ** giờ có sẵn:

1. `Localhost` → `127.0.0.1:8122`
2. `10.119.209.217` → `10.119.209.217:8122`

Chi tiết:
- Danh sách được dựng từ code, không phụ thuộc RMS cũ.
- Localhost luôn đứng đầu.
- Server `10.119.209.217` dùng cùng game port `8122`.
- Khi chọn server LAN, link đăng ký cũng đổi sang `http://10.119.209.217:8080/register`.
- `RUN_CUSTOM_SERVER.bat` vẫn dùng được; endpoint custom khác hai server trên sẽ được thêm thành lựa chọn thứ ba.
