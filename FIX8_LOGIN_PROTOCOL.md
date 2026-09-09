# FIX8 - Hybrid login protocol compatibility

## Root cause
The LibGDX desktop sample is based on a 2.4.1 client variant. `ServerListScreen.syncSelectedServer()` forced `GameMidlet.server = 2` whenever `versionByte >= 240`. In `MessageHandler` command 3 (LOGIN_SUCCESS), server 2 means the client reads an extra UTF username before the numeric user id.

The Hybrid Java server uses the working 2.3 wire format used by the emulator JAR: LOGIN_SUCCESS begins directly with `int user_id`, followed by xu, luong, selected character, clan, equipment/item data. There is no leading username UTF.

That two-byte/UTF misread shifted the whole packet, threw a parser exception, and left the `Đang đăng nhập` wait dialog visible forever.

## Fix
- Stop forcing built-in Hybrid endpoints to `server=2`.
- Parse LOGIN_SUCCESS using Hybrid 2.3 layout (user id first).
- Add a login-success parser fail-safe that closes the wait dialog and shows a protocol error instead of hanging.
- Simplify duplicate connect calls in `LoginScr.doLogin()`.

The server does not need to change. The working emulator JAR and LibGDX client now target the same login-success wire format.
