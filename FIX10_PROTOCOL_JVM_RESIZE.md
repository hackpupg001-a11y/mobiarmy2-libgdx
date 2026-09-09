# FIX10 - Hybrid 2.3 protocol lock + JVM cache repair + resizable desktop window

## Why this exists
The LibGDX source originated from a 2.4.1 desktop branch while the Hybrid server speaks the classic Army2 2.3 protocol. Previous fixes corrected login and room-list hot spots, but additional 2.4-only client commands were still present.

## Protocol lock
- Client now advertises `2.3.0` (`versionByte=230`) instead of `2.4.1`.
- Every statically-sent numeric client command now belongs to the actual Hybrid V2.3 `MessageHandler` command set.
- Active 2.4-only provider/agent packets (`58`, `127`, `-26`), ping `42`, room-name `-19`, terrain-hole `-92` are not sent.
- Desktop 2.4 map command `70` is bridged to Hybrid 2.3 map command `75`.
- Classic room flow remains `6 -> 7 -> 8`; quick play remains command `28`.

Static cross-check against the supplied Hybrid V2.3 server source:
- client unique numeric send commands: 67
- server client-command cases: 67
- unsupported client send command IDs: 0
- statically-sent server response IDs missing in client handler: 0 (handshake `-27` is handled in the session layer)

## One-file `Failed to launch JVM` hardening
The one-file bootstrap now validates the extracted jpackage runtime, not only `DataViewer.exe`:
- `DataViewer.exe`
- `app/DataViewer.cfg`
- `runtime/bin/server/jvm.dll`
- payload-hash cache marker

If the jpackage child exits non-zero within 8 seconds (typical broken-runtime launch), the bootstrap deletes/re-extracts its hash cache once and retries automatically.

## Desktop minimize/focus
FIX9 behavior is retained:
- `setPauseWhenMinimized(false)`
- `setPauseWhenLostFocus(false)`
- PC pause state cannot latch after taskbar minimize/Alt+Tab.

## Window sizing
The desktop window is now resizable. Initial size defaults to 960x540 and can be overridden without rebuilding:
- `ARMY2_WIDTH=1280`
- `ARMY2_HEIGHT=720`
- or JVM properties `-Darmy2.width=... -Darmy2.height=...`

16:9 sizes are recommended (800x450, 960x540, 1280x720, 1600x900).
