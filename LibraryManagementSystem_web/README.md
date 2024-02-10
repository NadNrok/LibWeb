# Library Management System (web version)<br>

### What is it?<br>
The Library Management System is a system where You can: <br>
- add, edit or remove a book to / in / from list;
- find a book by author;
- ask the library to order this book in the range.<br>

### What You Need<br>
+ JDK (Java Development Kit);<br>
+ Maven;<br>
+ You can also import the code straight into your IDE and start.<br>

***If what - you can download*** <br>
***[JDK](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html)*** <br>
***[Maven](https://apache.ip-connect.vn.ua/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip)*** <br>

### Getting Start<br>
To install the project, please follow next recommendation:<br>

Make git clone by this command:
```
git clone https://github.com/MelOmaH/LibraryManagementSystem_web.git
```
now you should change directory:
```
cd LibraryManagementSystem_web
```
and then you have to compile the project: <br>

```
mvn compile
```
after you should write next command to start the project: 
```
mvn exec:java -Dexec.mainClass="com.example.weblibrary.WebLibraryApplication"
```
now open this link using Your browser: http://localhost:8080

You can register new user or **Sign in** using next login details <br>

| Permission | Login | Password |
|------------|-------|----------|
| User       | u     |  u       |
| Admin      | admin | admin    |

