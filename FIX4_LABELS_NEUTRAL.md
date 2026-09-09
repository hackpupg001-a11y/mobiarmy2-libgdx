# FIX4 — Friendly server label + neutral Windows packaging

- Server `10.119.209.217:8122` is displayed as **Trái Đất** in the picker.
- Raw IP remains the actual endpoint internally; it is no longer used as the visible server name.
- Windows packaging uses neutral names:
  - `DataViewer.exe`
  - `DataViewer-OneFile.exe`
  - `WorkspaceData.jar`
  - cache: `%LOCALAPPDATA%\WorkspaceCache\1.0.2`
- Window title is `Data Viewer`.
- Helper names in portable output are neutral (`OPEN_ENDPOINT.bat`, `INFO.txt`).

This intentionally does **not** impersonate Microsoft Excel/Word or use their names/icons. It only uses generic neutral naming.
