# TVT Sanasto

The **TVT Sanasto** Java/Swing app aims to help in learning the basic terms in the 
area of Information and Communication Technologies (ICT) in Finnish and English. 
TVT (Finnish) means Tieto- ja viestintäteknologiat (ICT in English).

![App screenshot](screenshot.png)

The terms are arranged in categories. For example, terms in the category of 
"Basic computing" list terms such as *bit*, *byte*, *CPU*, etc. For each term, 
there are link(s) to further information about the term. Another category on 
"Data structures and algorithms" could describe terms like *linked lists*, *graphs*, 
and *heap sort*, for example.

The app fetches an index of term categories from a server. This index file is in JSON format. 
The list of categories in this index file contain links to the terms for each category, 
a file in JSON format.

App fetches each of the categories and terms listed in the index file, parses the JSON 
files and stores the categories and terms in the user's computer in a SQLite database. 
The terms can then be studied without network connection or consuming the network bandwidth. 
User can later fetch updates (if any) both to the index and the categories of terms by 
using the menus in the app.

User may also sort the terms by language and search the terms. App then lists only those 
terms containing the searched word.

## Installation

If you just want to use the app, go to Releases and download the `.jar` file to your computer.
Make sure you have the latest Java Runtime Environment (JRE) installed. It is required to run 
Java apps. If you are into development, you may also install the Java Development Kit (JDK), 
which includes the runtime required to run Java apps.

You have to have also the following installed on the machine running the app using the jar:

1. SQLite runtime https://www.sqlite.org
2. JRE/JDK 20 or newer.
3. *Optionally* if you wish to use the feature that generates a graph image showing 
   dependencies between the terms, you need to also install https://graphviz.org/ so 
   that it can be executed from the command line. The app works without it, but the 
   feature that generates the graph does not.

## Dependencies

For building the app you need the following Java version 20 features and components:

* Java SE JDK version 20 or newer from https://openjdk.org/install/ 
* Maven for project configuration from https://maven.apache.org/install.html 
* SQLite runtime. If not already installed, get it from https://www.sqlite.org,
* AWT and Swing for the user interface (included in the Java JDK),
* org.json for parsing JSON content (installed by Maven automatically),
* org.xerial JDBC driver for SQLite (installed by Maven automatically),
* Apache log4j for logging (latest version with fixes to the recent vulnerabilities found, 
  installed by Maven automatically),
* com.github.rjeschke txtmark for converting markdown text formatting to HTML in Swing 
  JEditorPane (installed by Maven automatically).

Project is managed using Maven, so the dependencies that are configured in the `pom.xml` 
are automatically installed and compiled into the app. Your Java development IDE should 
be able to use the Maven `pom.xml` to open the project. 

![architecture diagram](architecture.png)

You can find a rough class diagram of the app in [structure.png](structure.png) file.

## Building and running

After making sure all the dependent tools are installed, build the app from the command line 
in the project root directory:

```console
mvn package
```
and then run it:

```console
java -jar target/sanasto-1.1-SNAPSHOT-jar-with-dependencies.jar
```

Obviously you can also run the app from your IDE.

The first time the app is executed, it will download the index and term JSON files 
from a remote server and saves the categories and terms in the local database it 
creates. This may take some seconds, depending on the speed of your PC and network. 
Later, the app launches much quicker since it only needs to read the categories and 
terms from the local database.

Use the app menus to check if there are new term categories or terms in a category in the
remote server.

## Terms and categories

As mentioned, terms and categories are server hosted JSON files. If you are interested in 
contributing to the existing terms JSON files or creating new ones, please contact me.

If you wish to support other languages than Finnish/English, feel free to fork the project
(it is MIT licensed) and modify it to your needs and create the necessary JSON files for 
your preferred languages.

## Configuration

The app reads configuration options from `settings.properties` file. Some of these
are managed from the app itself, like the GUI language and sort order (`sortorder`
property) and when the terms index file has been updated from remote (`indexupdated`,
milliseconds from the epoch of 1970-01-01T00:00:00Z).

> If you cannot find the configuration file, launch the app and either change the
> language or try to fetch the index file. This will create the settings file,
> if it didn't exist before.

By default, the app fetches the term category index file from 
`https://gitlab.com/sanasto/index/-/raw/main/index.json`.
You may change this by adding a `indexURL` property to the settings file, e.g.:

```
indexURL=https://your.server.net/your-term-category-index-file.json
```

And then launch the app. The app will then read the index file from your url and attempts
to fetch term categories described there. See the JSON structure in gitlab and
build the index file based on that example, as well as separate term category files
for different types of terms.

See `settings.properties` and `Settings.java` for more details.

## Learning topics

For students, there are some things you might learn from this project:

* Model-View-Controller architectural style.
* Using the Observer design pattern.
* Using a SQLite database from your app.
* Fetching remote content using HTTPS.
* Parsing JSON to objects.
* Implementing a GUI using Java Swing/AWT.
  * Menus, handling menu commands.
  * Structuring the GUI using `JPanel`s and `Layout`s.
  * Separate list data and list view to ListModel and ListView classes.
  * Implementing list row renderers.
  * Localization using resource bundles.
  * Change GUI language on the fly without needing to restart the app.
  * Using image resources in menus and panels.
* Handling app settings using a properties file and `Properties` class.
* Logging using Log4J.
* Using external components in the app with Maven dependencies.
* Generate a GraphViz dot file to be able to generate graphs from app data.
* Run external apps and commands using `Process`.

## License

MIT License, (c) Antti Juustila, 2022-2026.

See the `LICENSE` file included.

The copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

Dependent components are copyright of the respective license holders.
For details, see components' documentation.