from pathlib import Path
import re, sys

ROOT = Path(__file__).resolve().parents[1]
CORE = ROOT / 'core' / 'src'
DESKTOP = ROOT / 'desktop' / 'src'

checks = []
def check(name, cond, detail=''):
    checks.append((name, bool(cond), detail))
    print(('PASS' if cond else 'FAIL') + ': ' + name + ((' - ' + detail) if detail else ''))

def text(rel):
    return (ROOT / rel).read_text(encoding='utf-8')

all_java = '\n'.join(p.read_text(encoding='utf-8', errors='replace') for base in (CORE, DESKTOP) for p in base.rglob('*.java'))

# Private-server endpoint hardening.
check('default host is loopback', 'DEFAULT_HOST = "127.0.0.1"' in text('core/src/coreLG/HybridConfig.java'))
check('default game port is 8122', 'DEFAULT_GAME_PORT = 8122' in text('core/src/coreLG/HybridConfig.java'))
check('LAN server 10.119.209.217 is built in', 'LAN_HOST = "10.119.209.217"' in text('core/src/coreLG/HybridConfig.java'))
check('server picker contains Localhost plus friendly LAN label', 'serverListEntries()' in text('core/src/coreLG/HybridConfig.java') and 'Localhost:' in text('core/src/coreLG/HybridConfig.java') and 'LAN_NAME = "Trái Đất"' in text('core/src/coreLG/HybridConfig.java'))
check('game server refresh uses dual-server list', text('core/src/com/teamobi/mobiarmy2/GameMidlet.java').count('HybridConfig.serverListEntries()') >= 2)
check('registration URL follows selected server', 'HybridConfig.registrationUrlFor(strIPConnect)' in text('core/src/screen/ServerListScreen.java'))
check('Hybrid server picker does not force official server=2 packet variant', 'GameMidlet.server = 2;' not in text('core/src/screen/ServerListScreen.java'))
check('LOGIN_SUCCESS starts with user_id for Hybrid 2.3 protocol', 'TerrainMidlet.myInfo.name = msg.reader().readUTF();' not in text('core/src/network/MessageHandler.java') and 'TerrainMidlet.myInfo.IDDB = msg.reader().readInt();' in text('core/src/network/MessageHandler.java'))
check('LOGIN_SUCCESS parse failure closes wait dialog', 'msg.command == 3' in text('core/src/network/MessageHandler.java') and 'Client/server không cùng định dạng protocol' in text('core/src/network/MessageHandler.java'))
legacy = re.findall(r'(?:gmb\.teamobi\.com|my\.teamobi\.com|wap\.teamobi\.com|(?<!1)27\.0\.\d+\.\d+|192\.168\.1\.88|46\.137\.254\.172|54\.254\.156\.202|:191(?:4|5)\d)', all_java)
check('no legacy public/dev endpoints remain in active Java source', not legacy, ', '.join(sorted(set(legacy))))

# Protocol invariants required by the Hybrid Java server.
cmd = text('core/src/network/Cmd_Client2Server.java')
expected = {
    'LOGIN': 1, 'REQUEST_ROOMLIST': 6, 'REQUEST_BOARDLIST': 7, 'JOIN_BOARD': 8,
    'LEAVE_BOARD': 15, 'READY': 16, 'SET_MONEY': 19, 'START_ARMY': 20,
    'MOVE_ARMY': 21, 'FIRE_ARMY': 22, 'USE_ITEM': 26, 'CHOOSE_ITEM': 68,
    'CHOOSE_GUN': 69, 'CHANGE_TEAM': 71,
}
for key, value in expected.items():
    check('protocol command ' + key, re.search(rf'\b{key}\s*=\s*{value}\s*;', cmd) is not None)
svc = text('core/src/network/GameService.java')
check('login command remains message 1', 'Message m = new Message(1);' in svc)
login_block = svc[svc.find('public void login('):svc.find('public void login(')+900]
check('login sends username/password/version UTF fields', login_block.count('writeUTF(') >= 3)

# V10-style desktop QoL layer.
mod = text('core/src/mod/ModSettings.java')
check('360 aim defaults ON', 'angle360", true' in mod)
check('aim HUD defaults ON', 'showAimHud", true' in mod)
main = text('core/src/com/teamobi/mobiarmy2/MainGame.java')
for f, action in [('F6','toggleAngle360'),('F7','toggleAimHud'),('F8','toggleAutoClick'),('F9','toggleOverlay')]:
    check(f'{f} hotkey wired', f'Input.Keys.{f}' in main and action in main)
check('typed characters reach game text fields', 'gameCanvas.keyPressed((int) character)' in main)
check('LibGDX Input hotkey type is imported', 'import com.badlogic.gdx.Input;' in main)
check('LibGDX 1.12 scroll callback signature present', 'scrolled(float amountX, float amountY)' in main)
check('input multiplexer is not overwritten after setup', main.count('Gdx.input.setInputProcessor(') == 1)
check('WASD desktop D-pad mapping present', all(x in main for x in ['case Input.Keys.W:', 'case Input.Keys.A:', 'case Input.Keys.S:', 'case Input.Keys.D:']))
check('Q/E desktop softkey mapping present', 'case Input.Keys.Q:' in main and 'return -6;' in main and 'case Input.Keys.E:' in main and 'return -7;' in main)
check('Space maps to center/fire', 'keycode == Input.Keys.SPACE' in main and 'gameCanvas.keyPressed(-5)' in main and 'gameCanvas.keyReleased(-5)' in main)
ccanvas = text('core/src/coreLG/CCanvas.java')
check('Screen commands are keyboard-driven without pointer release', 'Desktop keyboard commands must not depend on a mouse/touch release' in ccanvas and 'curScr.center.action.perform()' in ccanvas and 'curScr.left.action.perform()' in ccanvas and 'curScr.right.action.perform()' in ccanvas)
server_list = text('core/src/screen/ServerListScreen.java')
check('Server picker W/S navigation runs in update tick', 'Keyboard navigation must be handled on the normal update tick' in server_list and 'CCanvas.keyPressed[8]' in server_list and 'CCanvas.keyPressed[2]' in server_list and 'syncSelectedServer()' in server_list)
check('Space hold uses physical press/release state', 'keyHold[5] = true;' in text('core/src/coreLG/CCanvas.java') and 'keyHold[5] = false;' in text('core/src/coreLG/CCanvas.java') and 'case -5:' in text('core/src/coreLG/CCanvas.java'))
dialog = text('core/src/model/Dialog.java')
menu = text('core/src/model/Menu.java')
check('modal dialogs accept Space/Q/E without pointer release', 'handleKeyboardInput()' in dialog and 'this.center.action.perform()' in dialog and 'this.left.action.perform()' in dialog and 'this.right.action.perform()' in dialog and 'dialog.handleKeyboardInput()' in ccanvas)
check('popup keyboard dispatch survives dialog closing itself', 'if (currentDialog == dialog)' in ccanvas)
check('menus accept W/S plus Space/Q/E from keyboard', 'public void updateMenuKey()' in menu and 'CCanvas.keyPressed[2]' in menu and 'CCanvas.keyPressed[8]' in menu and 'CCanvas.keyPressed[5]' in menu and 'CCanvas.keyPressed[12]' in menu and 'CCanvas.keyPressed[13]' in menu)
check('Space auto-repeat is latched to one physical press', 'private boolean spaceDown;' in main and 'if (!this.spaceDown)' in main and 'this.spaceDown = false;' in main)
check('focus loss clears stuck keyboard state', 'CCanvas.clearKeyHold();' in main and 'keyPressed[i] = false;' in ccanvas and 'keyReleased[i] = false;' in ccanvas)
check('desktop control letters are suppressed from keyTyped outside text entry', 'isDesktopControlCharacter(character)' in main)
check('login/input dialog preserve text typing', 'CCanvas.curScr instanceof LoginScr' in main and 'CCanvas.currentDialog instanceof InputDlg' in main)

cp = text('core/src/player/CPlayer.java')
check('angleReset bypasses lock in 360 mode', 'public void angleReset()' in cp and 'if (ModSettings.angle360)' in cp)
check('normal-shoot clamp bypasses lock in 360 mode', 'this.angle = ModSettings.normalize360(this.angle);' in cp)
check('pointer aiming skips angleLock in 360 mode', 'if (!ModSettings.angle360)' in cp and 'a = ModSettings.normalize360(a);' in cp)

gs = text('core/src/screen/GameScr.java')
check('HUD shows normalized 360 angle', 'ModSettings.normalize360(angle)' in gs and '360°' in gs)
check('mod overlay exists', '[F6/F7/F8/F9]' in gs)

# Desktop stability fixes found during audit.
ses = text('core/src/network/Session_ME.java')
check('null network message no longer dereferences message.command', '1200 + message.command' not in ses)
check('100ms per-packet throttle removed', 'Thread.sleep(100L)' not in ses)
check('socket streams validated before threads start', 'Socket streams unavailable' in ses)
sock = text('core/src/CLib/mSocket.java')
check('socket connect timeout configured', 'connect(new InetSocketAddress(str, port), 5000)' in sock)
check('TCP_NODELAY enabled', 'setTcpNoDelay(true)' in sock)
sound = text('core/src/CLib/SoundSystem.java')
check('desktop audio loads packaged internal assets', 'Gdx.files.local(nameT)' not in sound and 'Gdx.files.internal(nameT)' in sound)

# Build/release structure.
settings = text('settings.gradle')
check('desktop-only Gradle project', "include 'core', 'desktop'" in settings and 'android' not in settings and 'ios' not in settings)
build = text('build.gradle')
check('JDK 21 toolchain declared', 'JavaLanguageVersion.of(21)' in build)
check('fat JAR uses neutral release name', "archiveFileName = 'WorkspaceData.jar'" in build)
assets = list((ROOT/'assets').rglob('*'))
check('assets packaged', sum(1 for p in assets if p.is_file()) >= 150, str(sum(1 for p in assets if p.is_file())))
java_count = sum(1 for base in (CORE, DESKTOP) for _ in base.rglob('*.java'))
check('substantial LibGDX source retained', java_count >= 170, str(java_count))


# GitHub/Windows packaging gate.
repo_root = ROOT.parent
workflow = repo_root / '.github' / 'workflows' / 'build-windows-exe.yml'
check('GitHub Actions Windows workflow present', workflow.is_file())
if workflow.is_file():
    wf = workflow.read_text(encoding='utf-8')
    check('workflow uses Windows 2025 runner', 'runs-on: windows-2025' in wf)
    check('workflow builds with Java 21', "java-version: '21'" in wf)
    check('workflow uses Gradle wrapper', '.\\gradlew.bat --no-daemon clean' in wf and ':desktop:dist' in wf)
    check('workflow packages portable EXE', '--type app-image' in wf and 'DataViewer.exe' in wf)
    check('workflow packages ONE-FILE portable EXE', 'build-onefile-portable.ps1' in wf and 'DataViewer-OneFile.exe' in wf and 'Windows-OneFile-Portable' in wf)
    check('workflow packages installer EXE', '--type exe' in wf and 'DataViewer-Setup.exe' in wf)
    check('workflow verifies Windows PE header', '0x4D' in wf and '0x5A' in wf)
check('neutral packaging does not require game-branded icon', True)
check('custom-server launcher present', (ROOT/'windows'/'OPEN_ENDPOINT.bat').is_file())
onefile = text('windows/build-onefile-portable.ps1')
check('one-file portable builder present', bool(onefile))
check('one-file builder no longer uses fragile IExpress', 'iexpress' not in onefile.lower())
check('one-file builder embeds jpackage payload in C# bootstrapper', 'OneFileBootstrap.cs' in onefile and '/resource:' in onefile and 'Payload.Zip' in onefile)
check('one-file cache is payload-hash versioned', 'Get-FileHash $payload -Algorithm SHA256' in onefile and 'WorkspaceCache' in onefile)

failed = [n for n, ok, _ in checks if not ok]
print(f'\nSUMMARY: {len(checks)-len(failed)}/{len(checks)} checks PASS')
if failed:
    print('FAILED:', ', '.join(failed))
    sys.exit(1)

# GitHub Actions regression: custom desktop fat-JAR task expands the core project JAR.
# Ensure the producer task runs first, otherwise Gradle fails with
# "Cannot expand ZIP ... core-1.0.0.jar as it does not exist".
_build_gradle = (ROOT / "build.gradle").read_text(encoding="utf-8")
assert "dependsOn ':core:jar'" in _build_gradle, "desktop:dist must depend on :core:jar"
print("PASS: desktop fat-JAR task depends on :core:jar")
