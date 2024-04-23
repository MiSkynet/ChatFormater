# ChatFormater
A plugin to easily set up the chat. 

## Installation
In order to use the plugin you must first download the plugin. Once it's downloaded you
just have to drag it into your server's plugins order and restart the server.

## Configuration
The config.yml should be easy to understand. If something no longer works, the config.yml can be deleted and the server restarted.

This is what the config.yml looks like in the latest plugin version:
``` 
# <- Information ->

# The plugin was created to set the format of the chat.
# The whole thing is open source on my github (https://github.com/MiSkynet)
# If something breaks, the config.yml can simply be deleted and the plugin reloaded.

# <- Configurations ->

# The appearance of the chat can be determined here
# These placeholders can be used here:
# %player% returns the player name
# %message% returns the message
# %prefix% returns the players prefix (LuckPerms related)
chat-format: "%player% §8» §f%message%"

# With this option you can visually customize your message in the chat 
# with '&' + <color code>
# (If this setting is activated, everyone can visually customize their message)
allow-chat-color: true
```
