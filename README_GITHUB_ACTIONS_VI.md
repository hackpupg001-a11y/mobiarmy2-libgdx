# MobiArmy2 LibGDX Hybrid — FIX2 PC Controls + One-File Portable

Bản này dùng source Army2 Desktop LibGDX làm nền, đã patch cho server Hybrid và bổ sung kiểu điều khiển PC dễ chơi.

## Phím PC

- **W / A / S / D** = lên / trái / xuống / phải.
- **Q** = softkey trái (menu/chức năng bên trái như điện thoại Java).
- **E** = softkey phải (back/chức năng bên phải).
- **Space** = phím giữa / OK / Fire.
- **Giữ Space** = giữ nguyên phím giữa/fire cho tới lúc nhả Space; không phải spam click giả.
- Arrow keys + Enter vẫn dùng được.
- Khi đang ở màn Login/Register, W/A/S/D/Q/E vẫn được phép nhập thành chữ, không bị cướp làm phím điều khiển.

Mod V10-style:

- `F6` căn góc 360.
- `F7` HUD góc/gió.
- `F8` AutoClick.
- `F9` overlay mod.

## EXE GitHub Actions xuất ra

Workflow `Build MobiArmy2 Windows EXE` tạo 3 kiểu:

1. `MobiArmy2-Windows-OneFile-Portable` — **khuyên dùng**. Artifact chỉ có `MobiArmy2-Portable-OneFile.exe`, double-click chạy, **không có wizard cài đặt**.
2. `MobiArmy2-Windows-Portable` — app-image chuẩn của `jpackage`; cần giữ cả folder `app/` và `runtime/` cạnh EXE.
3. `MobiArmy2-Windows-Setup` — installer `.exe`, chỉ giữ lại nếu muốn cài Start Menu/Desktop shortcut.

### One-file hoạt động thế nào?

Java/LWJGL native cần nhiều DLL + runtime nên không thể biến `jpackage` app-image thành đúng một PE thuần mà không có dữ liệu phụ. Workflow đóng cả app-image vào **một self-extracting Windows EXE**. Lần đầu chạy nó bung runtime vào:

```text
%LOCALAPPDATA%\MobiArmy2Portable\1.0.1
```

rồi mở game. Không hỏi cài đặt, không cần cài Java. Các lần sau dùng lại cache nên mở nhanh hơn.

## Push lên GitHub

Chép đè bản này vào repo local nhưng giữ `.git`, rồi:

```powershell
git add -A
git commit -m "Add PC controls and one-file portable EXE"
git push
```

Vào **Actions** chờ job xanh. Artifact nên tải là `MobiArmy2-Windows-OneFile-Portable`.


## FIX4: tên hiển thị & packaging trung tính

- Server `10.119.209.217:8122` hiển thị trong game là **Trái Đất**; không hiện IP ở danh sách máy chủ.
- Bản Windows dùng tên trung tính **Data Viewer** thay vì tên game ở title/EXE.
- Portable one-file: `DataViewer-OneFile.exe`.
- Dữ liệu ứng dụng bên trong: `WorkspaceData.jar`.
- Cache: `%LOCALAPPDATA%\WorkspaceCache\1.0.2`.
- Không giả mạo Microsoft Excel/Word hoặc dùng tên/icon của Microsoft; chỉ đổi sang tên trung tính.

## FIX5: one-file portable không còn dùng bộ tự giải nén cũ

Artifact `Windows-OneFile-Portable` vẫn chỉ có một file `DataViewer-OneFile.exe`. Bản FIX5 nhúng toàn bộ app-image vào một bootstrap EXE nhỏ viết bằng C#/.NET Framework, rồi bung vào cache theo SHA-256 ở `%LOCALAPPDATA%\WorkspaceCache\...` trong lần chạy đầu. Không có wizard cài đặt và không còn phụ thuộc vào công cụ self-extractor cũ trên GitHub runner.

## FIX6 — bàn phím PC

FIX6 sửa bug của source desktop cũ: màn chọn server trước đây chỉ đổi dòng khi `onPointerReleased()` chạy, nên W/S dù đã map ở LibGDX vẫn không tác dụng.

Phím desktop hiện tại:

- `W` / `↑`: lên
- `S` / `↓`: xuống
- `A` / `←`: trái
- `D` / `→`: phải
- `Q`: softkey trái
- `E`: softkey phải / back theo màn hình
- `Space`: softkey giữa / OK / fire
- Giữ `Space`: giữ fire/center tới khi nhả

Ở ô nhập username/password, W/A/S/D/Q/E vẫn được nhập thành chữ bình thường.

## FIX7 - Space trên popup/dialog
FIX7 sửa lỗi Space chỉ hoạt động ở screen nhưng không bấm được nút `OK` của popup/dialog. Dialog và menu giờ nhận keyboard trực tiếp; Space được latch theo physical key để giữ phím vẫn hoạt động mà không bị Windows auto-repeat spam lệnh.
