# BACnet

This sample program uses BACnet Java library called bacnet4j to create a BACnet Server application. BAcnetServer is a simulator application works as a BACnet slave. That means, it provides services to its master devices. 


# How to Run

* This is eclipse project and can be directly imported as a gradle project. Just import root project. Now, project is ready to be build in eclipse.
* We need JavaFX installed in system. For Oracle java, it comes bundled with Sun JDK. Note: Java should be at least 1.8.
* For ubuntu or other linux based operating systems which uses openjdk, we have to install opnjfx library. 
 sudo apt install openjfx
* SimulatorApplication is the startup program which launches a GUI application where we can see the server status, and also start/stop and create/edit/remove BACnet objects.
