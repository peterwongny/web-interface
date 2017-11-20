# Web Interface
The web interface is a part of the CS Sevens' (Group 17) project for COMP3111H (2017 Fall). As part of a request from Clint Kudo, this web interface was built with the Vaadin and Spring frameworks to allow staff members to view, edit, sort, and filter the records that have been migrated from the company's Z drive Excel files. It is deployed to Heroku where all the tables are stored. It is also capable of broadcasting promotional content, such as the Double 11 Campaign, notify customers of any changes to tour offering statuses, and generating a weekly report based on trends observed with the customers and bookings.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
It is recommended to have the following software installed before proceeding further.
```
Java SDK 1.8 64-bits
Eclipse STS 3.8.4 or above -- On Windows the executable is located at L:\apps\COMP3111\STS_64\sts-bundle\sts-3.8.4.RELEASE\STS.exe
Gradle (STS) 3.8.x+1.0.x -- You should install this in Eclipse STS by clicking Help -> Eclipse Marketplace... -> Type Gradle IDE Pack in the search box and install. STS needs to reboot after installation.
git command line tools (Latest) -- build in on macOS and most Linux Distribution
Heroku CLI
```

### Dependencies
All the dependencies can be seen in `build.gradle`.

### Building a package
The project can be built by running `./gradlew build` in the project root.

### Eclipse setup
1. Open the terminal run the command `git clone https://gmsdelmundo@bitbucket.org/cs7s/web-interface.git`, or clone the repository with any other `Git` tool.
2. Launch `Eclipse STS`.
3. Select a workspace which is preferably the parent directory of the project's location.
4. Click `File` from the menu -> `Open Projects from File System...`, and a dialog titled `Import Projects from File System or Archive` will be prompted.
5. Click `Directory` and select the cloned project folder, then click `Finish`.
6. In the `Package Explorer` or `Project Explorer` panel, there will initially be errors shown by Eclipse. Right click and select `Configure` -> `Convert to Gradle (STS) Project`. The errors should go away.
7. Right click the project in `Package Explorer` or `Project Explorer` panel and select `Gradle (STS)` -> `Task Quick Launcher`, then type `build` and press `Enter`. This will attempt to build and test the project locally. Please be aware that the first build may take a longer time to complete as Gradle will install all the required dependencies. During this time, all the test cases of the web interface will run, all of which should pass.

**Note**: In case `Package Explorer` or `Project Explorer` is not displayed, it can be found from `View` in the menu. Moreover, if typing `build` in the `Task Quick Launcher` does not work, simply open a terminal, navigate to the project root, and type `./gradlew build`.

### Running tests on the project
When running `build` in the `Task Quick Launcher`, or `./gradlew build` from the terminal, all the test cases should run.

## Deployment

### Deploy to localhost:8080
To deploy the web interface locally, right click the project root on `Package Explorer` or `Project Explorer` -> `Run as...` -> `Java Application`. The project will be ready once `Started WebpageApplication` is shown in the console.

To access the web interface, open any web browser, and navigate to `localhost:8080`.

### Deploy to Heroku
A `Heroku` account is needed to deploy the application to `Heroku`.  

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

## Pages

### Login Screen
[![https://gyazo.com/051829ba59af3d6ea4943d02e52832fb](https://i.gyazo.com/051829ba59af3d6ea4943d02e52832fb.png)](https://gyazo.com/051829ba59af3d6ea4943d02e52832fb)

Upon opening the web interface, the login page will be the first page visited. Simply input the correct credentials to log in.

### Tour List
[![https://gyazo.com/3ceba4af665d7abe79f4b986b0062d41](https://i.gyazo.com/3ceba4af665d7abe79f4b986b0062d41.png)](https://gyazo.com/3ceba4af665d7abe79f4b986b0062d41)

### Booking Table
[![https://gyazo.com/afdc706d9cbf65078dc9812f11070f83](https://i.gyazo.com/afdc706d9cbf65078dc9812f11070f83.png)](https://gyazo.com/afdc706d9cbf65078dc9812f11070f83)

### Customer Table
[![https://gyazo.com/032d28bd3576ff3300abc9b982dd748f](https://i.gyazo.com/032d28bd3576ff3300abc9b982dd748f.png)](https://gyazo.com/032d28bd3576ff3300abc9b982dd748f)

### Unanswered Enquiries Table
[![https://gyazo.com/7439a321ed3bda696948733325c74af7](https://i.gyazo.com/7439a321ed3bda696948733325c74af7.png)](https://gyazo.com/7439a321ed3bda696948733325c74af7)

### Promotion

### Weekly Report
[![https://gyazo.com/373b0a9395f323e387aa794a49e22781](https://i.gyazo.com/373b0a9395f323e387aa794a49e22781.png)](https://gyazo.com/373b0a9395f323e387aa794a49e22781)

## Features

### Adding records

### Sorting records
[![https://gyazo.com/684b564cef4222dd5534fbec6b20fb59](https://i.gyazo.com/684b564cef4222dd5534fbec6b20fb59.png)](https://gyazo.com/684b564cef4222dd5534fbec6b20fb59)

Sorting Tour List by name.

### Filtering records
[![https://gyazo.com/e15effae6ef71612f1dd572aec902a31](https://i.gyazo.com/e15effae6ef71612f1dd572aec902a31.png)](https://gyazo.com/e15effae6ef71612f1dd572aec902a31)

Filtering Tour List by ID.

## Authors
* **DEL MUNDO, Gian Miguel Sero** (gmsdelmundo, 20342844)
* **GOBINDRAM, Tavish** (tgobindram, 20317576)
* **SAMBAMURTHY, Vikram** (vsambamurthy, 20259528)
* **WONG, Ngo Yin** (nywongac, 20355097)