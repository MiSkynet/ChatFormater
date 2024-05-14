# ChatFormater
A plugin to easily set up the chat. 

## Information
I always try to update my plugin and add new things. I would be happy to receive feedback in order to continually improve this 
and other plugins. The plugin was written by me alone, so I would be happy to receive feedback. Both positive and 
negative comments are welcome.

## Installation and setup
In order to use the plugin you must first download the plugin [here](https://hangar.papermc.io/MiSkynet/ChatFormater/versions). 
Once it's downloaded you just have to drag it into your server's plugins order and restart the server.

## Configuration (config.yml)
The config.yml should be easy to understand. If something no longer works, the config.yml can be deleted and the server restarted.

This is what the config.yml looks like in the latest plugin version:
``` 
# <- Information ->

# Made by: MiSkynet
# Github: https://github.com/MiSkynet
# PaperMC: https://hangar.papermc.io/MiSkynet

# The plugin was created to set the format of the chat.
# The whole thing is open source on my github
# If something breaks, the config.yml can simply be deleted and the plugin reloaded.

# <- Configurations ->

# The appearance of the chat can be determined here
# These placeholders can be used here:
# %player% returns the player name
# %message% returns the message
# %prefix% returns the players prefix (LuckPerms related)
# %time% represents the real time the message was sent
# If this option is set to -default or -d, Minecraft's chat format will be used
chat-format: "%player% §8» §f%message%"

# The time-format option can be used to set the format for the %time% placeholder.
# The time is taken based on the server region.
# There are also special placeholders:
# %HH% represents the hours
# %MM% represents the minutes
# %SS% represents the seconds
time-format: "%HH%:%MM%:%SS%"

# With this option you can visually customize your message in the chat 
# with '&' + <color code>
# (If this setting is activated, everyone can visually customize their message)
allow-chat-color: true

# With this permission you can edit your message in the chat with '&' + <color code>
# If this is set to -all or -a, as long as it is enabled, anyone can use it
chat-color-permission: chatformater.chatcolor

# Banned words can be entered in this list and will be censored in the chat.
# Please note that the format is always as in the example:
# example: blacklisted-words: [word1,word2,...]
blacklisted-words: []
```
