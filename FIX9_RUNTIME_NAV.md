# FIX9 - Desktop lifecycle + Hybrid 2.3 room flow + keyboard release

Fixes:
- Minimize/Alt+Tab no longer applies the mobile pause lifecycle on LWJGL3.
- LWJGL3 explicitly keeps running when minimized or focus is lost.
- Writes `~/DataViewer-crash.log` if the desktop launcher terminates with an uncaught exception.
- Room list now uses classic Hybrid 2.3 client command `6`, not unsupported 2.4 command `-28`.
- 2.4 encoded find-room calls are bridged to classic command `8` joinBoard.
- Room/join/map parse failures close the wait dialog and show the failed command instead of spinning forever.
- WASD/Q/E physical key-down state is tracked; key-up is delivered even when Q/E changes screen before release.
- OS key-repeat is suppressed for mapped desktop controls.
