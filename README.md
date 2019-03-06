# Markfed-CCMS
Markfed Court Case Monitoring System
<br>
Default user in database:<br>
user name: test, password: test

For detailed working, check the file M-CCMS_UserManual.txt

To use:
- Setup MySQL database locally on the system
- Create a new database named 'markfed' and import data from the file markfed.sql
- Download /dist folder
- Run CCMSPB.jar
- If you need to use a remote database, update the IP and password in line 46 and 47 in /src/ccmspb/DatabaseHandling.java, compile the entire code and generate the JAR file again
