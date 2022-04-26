# ENSF409FinalProject-Group-50

This is our ENSF 409 final project (Group-50) repository.

## Contributors

- Labib Afsar Ahmed, email: <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
- Jannine Osman, email: <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
- Sukriti Sharma, email: <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
- Caroline Basta, email: <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>

## The Folder Structure:

- All .java files are in .edu/ucalgary/ensf409.
- The UML diagram is in root.
- All the library packages are in lib.
- The original database (Inventory.sql) is in root.

## To Compile on Windows

Assuming in the working directory

```
javac -cp .;lib/mysql-connector-java-8.0.23.jar;. edu/ucalgary/ensf409/GUI.java
```

## To Run on Windows

Assuming in working directory

```
java -cp .;lib/mysql-connector-java-8.0.23.jar;. edu/ucalgary/ensf409/GUI
```

## To Compile Tests on Windows

Assuming in working directory

```
javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar;edu/ucalgary/ensf409 edu/ucalgary/ensf409/*.java
```

## To Run Tests on Windows

Assuming in working directory

```
java -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.TestFiles
```

<br>
Note: The database username is "student" and password is "ensf".
<br>
<br>
The Unit tests have been designed to work with the original given database (inventory.sql), if you want to make changes to the tests, please make sure you put the correct values into the expected values.
<br>
