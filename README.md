Bandit Tweaker - Forge 1.12.2
-----------------------------------
What this is
- A small Forge mod that lets you adjust Bandit (vein-mining) parameters at runtime.
- Adds a server command `/bt` (ops only) and a client GUI you can open with a keybind that sends those commands as chat messages.

How to build
1. Install JDK 8 and Gradle.
2. Place this project in a workspace and run `gradlew build` (you may need to accept ForgeGradle downloads).
3. The built jar will appear in `build/libs/`.

How to use
- Put Bandit.jar and the built BanditTweaker.jar into your `mods/` folder for Minecraft 1.12.2.
- In-game, press the configured key (default: K) to open the GUI and adjust values. The GUI will send `/bt` chat commands to apply settings (server requires op permissions to actually change values).
