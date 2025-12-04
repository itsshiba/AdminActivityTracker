![Available for Paper](https://raw.githubusercontent.com/intergrav/devins-badges/v2/assets/compact/supported/paper_46h.png)
![Available for Purpur](https://raw.githubusercontent.com/intergrav/devins-badges/v2/assets/compact/supported/purpur_46h.png)

# AdminActivityTracker

A Minecraft plugin for tracking and logging administrator activity on your server.

## Description

AdminActivityTracker is a lightweight plugin that automatically tracks and saves all activities of players with administrator permissions to text files. Perfect for monitoring staff activity and preventing abuse.

## Features

- **Command logging** - Records all commands executed by administrators
- **Creative inventory tracking** - Logs items taken from creative inventory
- **Dropped items monitoring** - Tracks items dropped by admins
- **Item movement tracking** - Records item transfers from admin inventory to chests or other players
- **Individual log files** - Each administrator has their own activity history file
- **Configurable system** - Ability to enable/disable individual features

## Requirements

- **Minecraft**: 1.20+
- **Java**: 21
- **Server**: Paper/Purpur

## Installation

1. Download the latest version from Releases
2. Place the `.jar` file in your server's `plugins/` folder
3. Start/restart the server
4. Plugin will automatically create `plugins/AdminActivityTracker/admin/` folder and config file

## Configuration

After first launch, the plugin generates `config.yml`:

```yaml
# Admin Activity Tracker Configuration

# Should the plugin log commands executed by administrators?
logAdminCommands: true

# Should the plugin log items dropped by the administrators?
logDroppedItems: true

# Should the plugin log items, which administators got from creative inventory?
logCreativeItems: true

# Should the plugin log items moved from the adminstrators inventory?
# For example, from administator inventory to chest/other player inventory
logMovedItems: true
```

## Permissions

```aat.admin - Player with this permission is tracked by the plugin```



## Example Logs

All activities are saved to `plugins/AdminActivityTracker/admin/<player_name>.txt`:

```
[04-12-2025 14:23:45] Admin issued server command: /give @s diamond 64
[04-12-2025 14:24:12] Admin got from creative inventory: DIAMOND_BLOCK x64
[04-12-2025 14:25:33] Admin dropped the item: DIAMOND x32
[04-12-2025 14:26:01] Admin moved the item from inventory: GOLDEN_APPLE x16
```
