# Chess

[![](https://jitpack.io/v/muscaa/chess.svg)](https://jitpack.io/#muscaa/chess)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A Java-based Chess game that supports both single-player and multiplayer modes.

## Features

- Single-player mode with a BOT opponent.
- Multiplayer mode for local or online play.
- Player chat system with commands.
- Full modding support to customize the game and add new functionalities.
- Cross-platform support for Windows, macOS, and Linux.

## Requirements

- Java 17+
- `chess-updater.jar`

## Download

For an easier installation and update process, use the `chess-updater.jar`,
which can be downloaded from the [Chess Launcher Repository](https://github.com/muscaa/chess-launcher)
or directly from [this link](https://github.com/muscaa/chess-launcher/releases/download/updater/chess-updater.jar).

## Gradle

### Run
```bash
./gradlew client:run
```
or
```bash
./gradlew server:run
```

### Build
```bash
./gradlew client:build
```
or
```bash
./gradlew server:build
```

## Modding

It supports mods to enhance and customize gameplay.
Mods can be added by placing their `.jar` files in the `mods/` folder within the game directory.

### How to develop mods
1. Clone the [Chess Template Mod](https://github.com/muscaa/chess-template-mod) repository for a quick start.
2. Configure and develop your mod.
3. Test your mod by running it in a dev environment with:
    ```bash
    ./gradlew run
    ```
4. Build your mod with:
    ```bash
    ./gradlew build
    ```
5. Add the mod `.jar` file to the `~/.chess/mods/` directory.
6. Done!
