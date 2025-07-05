# LightningMod

If you are looking for the Paper version of this mod, you can find it [here](https://github.com/plngvln/lightingmodPaper).

LightningMod is a Minecraft mod for Fabric that adds dynamic and realistic lightning strikes during thunderstorms, with features such as lightning rod attraction and configurable lightning behavior. The mod enhances the experience of thunderstorms by making lightning strikes more interactive and customizable.

## Features

- **Configurable Lightning Frequency**: Adjust the chance of lightning strikes during a thunderstorm. Make them incredibly frequent or extremely rare using a simple slider. The lower the value, the more often lightning will strike.
- **Skeleton Horse Control**: Modify the spawn chance of the "Skeleton Trap" horses that appear from lightning strikes. You can reduce the chance or disable them completely.
- **All-Biome Lightning**: Enable an option to allow lightning to strike in any biome during a thunderstorm, including deserts, savannas, and mesas. No more safe havens from the storm!
- **In-Game Configuration**: All settings can be easily changed in-game through the ModMenu settings screen, powered by Cloth Config.

## Requirements

To use this mod, you need to have the following mods installed:

- [Fabric API](https://modrinth.com/mod/fabric-api) (Required)
- [Mod Menu](https://modrinth.com/mod/modmenu) (Required to access the config screen in-game)
- [Cloth Config API](https://modrinth.com/mod/cloth-config) (Required for the configuration screen)

## Installation

1.  Download the latest `.jar` file for LightningMod.
2.  Download the required dependencies: Fabric API, Mod Menu, and Cloth Config.
3.  Place all the downloaded `.jar` files into your `mods` folder.
4.  Launch the game. The mod is now installed.

## Configuration

You can configure the mod in-game by clicking the "Mods" button on the main menu, selecting "LightningMod", and clicking the config button.

The configuration settings are saved in `config/lightningmod.json` and include the following options:

-   `lightningChance` (integer):
    -   Controls the frequency of lightning strikes. A lower number means more frequent lightning.
    -   **Default**: `100000` (Vanilla value)
    -   **Range**: `100` to `1,000,000`

-   `skeletonHorseChanceMultiplier` (double):
    -   A multiplier for the spawn chance of Skeleton Horse traps from lightning.
    -   **Default**: `1.0` (100% of vanilla chance)
    -   **Range**: `0.0` (0% chance) to `1.0` (100% chance)

-   `lightningInAllBiomes` (boolean):
    -   If `true`, lightning can strike in all biomes during a thunderstorm, ignoring biome-specific rainfall rules.
    -   **Default**: `false`

## Troubleshooting

-   **Mod Not Working / No Changes**: Ensure you have installed all the required dependencies (Fabric API, Mod Menu, Cloth Config). Also, make sure you have saved your changes in the config screen.
-   **Can't Find Config Screen**: You must have [Mod Menu](https://modrinth.com/mod/modmenu) installed to access the configuration screen from the main menu.

## License

This mod is licensed under the MIT License. See the `LICENSE` file for more details.

## Contributing

Contributions are welcome! If you have ideas for new features, find a bug, or want to improve the code, feel free to open an issue or submit a pull request on GitHub.
