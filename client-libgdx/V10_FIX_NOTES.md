# LibGDX V10 compatibility fixes

Reference: user-supplied `99999999.jar` (MobiArmy 2.3.0 V10).

## Fixed

- Equipment definition/player equipment parsing hardened for the 2.3/V10 wire layout.
- Five equipment IDs are read before resolving `PlayerEquip`; invalid gun slots no longer corrupt parsing.
- Async LibGDX bullet-image decoding now preserves the packet bullet index.
- Equipment rendering tolerates missing/late images and missing equipment slots instead of crashing the battle screen.
- Map metadata parser no longer reuses the outer map index while reading the five map values.
- Full map metadata refresh clears stale entries before replacing the table.
- V10 normal aiming limits restored: left side >= 91 degrees, right side <= 89 degrees, plus per-gun angle lock.
- The 360-degree option is display-only and no longer mutates the gameplay/network FIRE angle.
- V10 trajectory preview added for guns 0..9, including spread and special gun 6/7/8/9 preview behavior.
- FIRE packet layout remains the original 2.3/V10 layout; preview rendering never writes the network angle.
- Battle player creation validates packet array/list bounds and isolates bad player/equipment resources.
- Post-start consumable bookkeeping and equipment DB-key updates are null-safe.

## Verification

- `python3 tools/verify_source.py`: 117/117 PASS.
- `javac` parse/type smoke check on all modified Java files: no syntax/type-like diagnostics from the patches; compilation then stops on unavailable external LibGDX packages.
- Full Gradle compilation was not possible in the current container because Gradle/LibGDX dependencies are not cached and external dependency download is unavailable.
