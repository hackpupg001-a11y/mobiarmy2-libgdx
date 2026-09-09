# FIX6 — PC keyboard navigation actually works

The previous build mapped WASD/Q/E/Space in LibGDX, but the inherited desktop source had two old touch-centric bugs:

1. `ServerListScreen` processed up/down selection only inside `onPointerReleased()`. W/S and arrow keys therefore did not move the server selection unless a pointer release happened.
2. `CScreen` center/left/right commands were executed only through pointer-release input, so Q/E/Space could set key flags without triggering the visible command.

FIX6 changes:

- `W/S` and arrow Up/Down select `Localhost` / `Trái Đất` in the normal update tick.
- `Space` executes the current center/OK command without requiring mouse input.
- `Q` executes the current left softkey command.
- `E` executes the current right softkey command.
- Holding Space still keeps the center/fire hold state until release for battle screens.
- WASD remains D-pad movement outside text-entry screens.
- Text-entry exception remains, so W/A/S/D/Q/E can still be typed in username/password fields.

Controls:

- W / A / S / D = Up / Left / Down / Right
- Q = left soft key
- E = right soft key
- Space = center / OK / fire
- Hold Space = hold center/fire
- Arrow keys / Enter remain supported.
