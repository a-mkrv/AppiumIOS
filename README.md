# Autotests iOS applications (Appium / iOS / Java).

The steps below guarantee the successful launch of autotests for your phone and application. 

But I do not exclude that some steps may be superfluous, maybe I myself installed something in surplus :)

### Installation

##### Device (iPhone)
1.  Install the application server on the phone [WebDriverAgent](https://github.com/facebook/WebDriverAgent)
2.  Add a valid distribution certificate
3.  Select the WebDriverAgentRunner scheme -> Real Device -> Build (⌘B)
4.  Connect the phone and run the tests. Open Product -> Test (⌘U)

##### MacBook (terminal):
```sh
$ brew install node
$ npm install -g appium@1.7.2
$ npm install npm@latest -g
$ brew install ideviceinstaller
$ brew install carthage
$ npm install -g ios-deploy
```

##### To check the correct installation  
```sh
$ appium-doctor
```

All components must be successfully installed and marked with a green mark, except for Android components.

* Build the project in Java / Python or other languages that support Appium / iOS (do not forget to install the libraries: apache, selenium, appium)
     * In the project add information about the connected phone (name, udid, version, platform) and the path to the assembled application that will be tested.


##### Everything is ready to test your application:
1. On the phone, a test (active session) must be started. WebDriverAgent
2. In the console execute: appium --session-override
3. Build and run the project
4. Done, enjoy autoscrolls and autoclicking :)

 


#### Supporting resources

| Resource | Website |
| ------ | ------ |
| WebDriverAgent -  official repository| [plugins/googledrive/README.md][WebDriverAgent] |
| WebDriverAgent - article instruction | [Getting Started with Automated iOS Testing][WebDAgent] |
| Good video tutorial | [How to inspect iOS element using Xcode][Youtube1] |

 [WebDAgent]: <https://www.mutuallyhuman.com/blog/2017/04/20/webdriveragent-getting-started-with-automated-ios-testing>
 [Youtube1]: <https://www.youtube.com/watch?v=4BgkVp0v3BM>
 [WebDriverAgent]: <https://github.com/facebook/WebDriverAgent>
   
