# LightningMod

If you are looking for the Paper version of this mod, you can find it [here](https://github.com/plngvln/lightingmodPaper).

LightningMod is a Minecraft mod for Fabric that adds dynamic and realistic lightning strikes during thunderstorms, with features such as lightning rod attraction and configurable lightning behavior. The mod enhances the experience of thunderstorms by making lightning strikes more interactive and customizable.

## Features

- **Random Lightning Strikes**: Lightning can randomly strike near players during thunderstorms in the overworld.
- **Lightning Rod Attraction**: When lightning rods are enabled, the mod will check for nearby lightning rods within a specified radius and strike them if found.
- **Configurable Settings**: The chance for lightning strikes, radius, and other settings can be adjusted in the configuration.

## Requirements

To use this mod, you need to install the following dependencies:

- [Fabric API](https://modrinth.com/mod/fabric-api) (required)
- [Cloth Config](https://modrinth.com/mod/cloth-config) (required for configuration management)


## Installation

1. Download the latest `.jar` file for the mod.
2. Download the [Fabric API](https://modrinth.com/mod/fabric-api) and [Cloth Config](https://modrinth.com/mod/cloth-config) mods.
3. Place all three `.jar` files (LightningMod, Fabric API, and Cloth Config) in your Fabric server's `mods` folder.
4. Start or restart the Fabric server to load the mods.
5. A configuration file will be generated in the `config` folder where settings can be customized.

## Configuration

The configuration file (`lightningmod.json`) includes the following settings:

- `modEnabled` (boolean): Enables or disables the mod (`true` or `false`).
- `lightningChance` (double): The chance for lightning to strike each tick during a thunderstorm (range 0.0 - 1.0).
- `lightningRadius` (integer): The radius around players where lightning can randomly strike.
- `lightningRodEnabled` (boolean): Enables lightning rod attraction behavior.

## Troubleshooting

- **Mod Not Working**: Make sure `modEnabled` is set to `true` in the configuration file, and that thunderstorms are occurring in the overworld.
- **Performance Issues**: Reduce the `lightningRadius` or lightning chance to decrease the number of checks per tick.

## License

This mod is licensed under the MIT License. See the `LICENSE` file for details.

## Contributing

Feel free to contribute by opening issues, submitting pull requests, or providing feedback. All contributions are welcome!

## Contact

For any questions or issues, you can reach out via GitHub Issues.

