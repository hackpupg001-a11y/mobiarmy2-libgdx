# FIX7 - Space / Dialog / Menu Keyboard Dispatch

## Bug reproduced
`Space` worked on a normal screen, but after a modal dialog (for example `Kết nối thất bại ... OK`) appeared, pressing Space again did nothing.

Root cause: the legacy LibGDX desktop source dispatched `Dialog` commands only from pointer/touch release. The previous keyboard patch dispatched normal `CScreen` commands but intentionally skipped them while a dialog/menu was active, leaving modal OK/Yes/No keyboard-inert.

## Fixes
- `Dialog.handleKeyboardInput()` handles center/left/right softkeys from keyboard.
- `CCanvas.update()` always dispatches modal keyboard input before `Dialog.update()`.
- Safe against a dialog closing itself while its action executes.
- `Menu.updateMenuKey()` now handles W/S, Space/Q select, E close without mouse input.
- `Space` is physically latched in `MainGame.MyInputProcessor` so Windows key-repeat does not trigger multiple OK actions.
- Holding Space still keeps `CCanvas.keyHold[5]` active until the real key-up, preserving hold/charge/fire semantics.
- Losing window focus clears held/pressed/released keyboard state to avoid stuck movement/fire.

## Controls
- W / Up: up
- S / Down: down
- A / Left: left
- D / Right: right
- Q: left softkey
- E: right softkey / back
- Space: center / OK / fire
- Hold Space: hold center/fire until released

## Verification
`python3 client-libgdx/tools/verify_source.py` => 76/76 PASS plus fat-JAR dependency gate PASS.

The final Windows Gradle/LWJGL compile remains the GitHub Actions gate because the local Linux sandbox does not have the Windows dependency/toolchain cache.
