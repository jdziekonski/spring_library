# BookLibraryApp
## Description:

Book Library Application is a Java Web Application that allows users to browse the given book library (loaded from a .json file located inside resources catalog). It provides such functions as for example: showing a book by given ISBN number or displaying all books from a given category.  
This app was built using Spring Boot framework and Maven build tool. Jackson library was used to handle JSON documents operations.  
There was one design pattern used, called DAO (Data Access Object) - its purpose is to separate a data resource from the mechanisms accessing it. Although the data isn’t changing in this particular case, it enables us to easily expand the project henceforth.
## Building:
To build the project use the following command:
*'mvn clean package'*

If you want to change the book library json file, you have to add the new file into the resources folder, and change the file name inside BookDao.java (line: 28).

## Running:
After building the application run the following command:

*'java -jar target/BookLibraryApp-1.0.jar'*

Application works on port:8080, with the following addresses:

*localhost:8080/books/*  
*/books/{isbn}*  
*/books/category/{category}*  
*/books/ratings*
