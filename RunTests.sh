#!/bin/bash

kill $(ps aux | grep 'IntelliJ' | awk '{print $2}')
kill $(ps aux | grep 'Xcode' | awk '{print $2}')

sleep 3s

### Part 1 (IntellJ IDEA)
echo "Starting IntelliJ IDEA"

/Applications/IntelliJ\ IDEA.app/Contents/MacOS/idea &

sleep 40s

osascript <<EOD
    tell application "System Events"
        key code 36 #Enter (if the project does not open)
    end tell
EOD

echo "IntelliJ IDEA has been opened"

### Part 2 (Appium Session)
echo "Starting Appium Session"

appium --session-override &

### Part 3 (Xcode)
echo "Starting Xcode"

open /Users/admin/Downloads/WebDriverAgent-master/WebDriverAgent.xcodeproj

sleep 10s

osascript <<EOD

  tell application "Xcode"
      activate
  end tell

  tell application "System Events"
    click menu bar item "Product" of menu bar 1 of application process "Xcode"
    click menu item "Destination" of menu "Product" of menu bar item "Product" of menu bar 1 of application process "Xcode"
    key code 125 #Down Key
    key code 125
    key code 36

	click menu bar item "Product" of menu bar 1 of application process "Xcode"
    click menu item "Test" of menu "Product" of menu bar item "Product" of menu bar 1 of application process "Xcode"
  end tell

EOD

echo "Xcode has been opened"

### Part 4 (Focus to IntelliJ IDEA -> Start tests)
sleep 10s

osascript <<EOD

    tell application "IntelliJ IDEA"
        activate
    end tell

    tell application "System Events"
        click menu bar item "Run" of menu bar 1 of application process "IntelliJ IDEA"
        key code 125
        key code 36
    end tell

EOD

