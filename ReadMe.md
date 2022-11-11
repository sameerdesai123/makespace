# MakeSpace Challenge
* packages definded
  * com.makespace.controller
  * com.makespace.models
  * com.makespace.views
  * com.makespace.Exceptions

# Pre-requisites
* Java 1.8
* Maven

# How to run the code

We have provided scripts to execute the code. 

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows.

Internally both the scripts run the following commands 

 * `mvn clean install -DskipTests assembly:single -q` - This will create a jar file `geektrust.jar` in the `target` folder.
 * `java -jar target/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

 Use the pom.xml provided along with this project. Please change the main class entry (<mainClass>com.example.geektrust.Main</mainClass>) in the pom.xml if your main class has changed.

 # How to execute the unit tests

 `mvn clean test` will execute the unit test cases.
 
# Code Information

## Few Classes and their responsibility:
<table>
    <thead>
        <th>Class</th>
        <th>Responsibility</th>
        <th>Location</th>
    </thead>
    <tbody>
        <tr>
            <td>Main</td>
            <td>Calls Booking to execute all requests</td>
            <td>com.makespace.controller.Main</td>
        </tr>
        <tr>
            <td>Booking</td>
            <td>Executes requests and prepares results using Request and Room classes</td>
            <td>com.makespace.controller.Booking</td>
        </tr>
        <tr>
            <td>Request</td>
            <td>Holds request data and performs booking and vacancy operations</td>
            <td>com.makespace.models.Request</td>
        </tr>
        <tr>
            <td>Room</td>
            <td>Room bean class with bookings list (tracker)</td>
            <td>com.makespace.models.Room</td>
        </tr>
    </tbody>
</table>