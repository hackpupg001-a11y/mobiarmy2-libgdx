# FIX11 — Minimize / JVM launcher hardening

- LWJGL now pauses rendering while the window is actually minimized (`setPauseWhenMinimized(true)`).
- Alt+Tab alone does not pause the app (`setPauseWhenLostFocus(false)`).
- MainGame skips transient 0x0/1x1 framebuffers during minimize transitions.
- Desktop default uncaught exceptions are written to `~/DataViewer-crash.log`.
- The one-file bootstrap no longer deletes the runtime cache and relaunches the app just because the running child exits. This avoids turning an application crash into a misleading `Failed to launch JVM` retry.
