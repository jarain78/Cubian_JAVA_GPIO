import java.io.File;  
import java.io.FileWriter;  
   
public class LedOnOff {  
	
	static String gpioChannel = "11";
	static String cubiePort = "ph15";
	static String cubiePinMode = "out";
	static String gpioStateHigh = "1";
	static String gpioStateLow = "0";

	public static void main(String[] args) throws InterruptedException {  
		try {  
		       // Open file handles to GPIO port unexport and export controls  
		       FileWriter unexportFile =   
			   new FileWriter("/sys/class/gpio/unexport");  
		       FileWriter exportFile =   
		           new FileWriter("/sys/class/gpio/export"); 		

         		// Reset the port, if needed  
         	       File exportFileCheck =   
                     		new File("/sys/class/gpio/gpio"+gpioChannel+"_"+cubiePort);  

         	       if (exportFileCheck.exists()) {    
           	       		unexportFile.write(gpioChannel);  
				unexportFile.flush();
	               }
  			
		       // Set the port for use  
         	       exportFile.write(gpioChannel);    
		       exportFile.flush();

		      // Open file handle to port input/output control  
		      FileWriter directionFile =  
	                 new FileWriter("/sys/class/gpio/gpio"+gpioChannel+"_"+cubiePort+  
             		"/direction");  
         
		     // Set port for output  
		     directionFile.write(cubiePinMode);
		     directionFile.flush();
		
  		    // Set up a GPIO port as a command channel  
		    FileWriter commandChannel = new   
                	FileWriter("/sys/class/gpio/gpio" +  
             		gpioChannel+"_"+cubiePort + "/value");  

		
		    int period = 100; // Sleep time in milliseconds  
         
		    while (true) {  
		           // HIGH: Set GPIO port ON  
		           commandChannel.write(gpioStateHigh);  
			   commandChannel.flush();
		           java.lang.Thread.sleep(period);  
       
		           // LOW: Set GPIO port OFF  
		           commandChannel.write(gpioStateLow);  
			   commandChannel.flush();
		           java.lang.Thread.sleep(period);  
	            }    
		} catch (Exception exception) {  
		       exception.printStackTrace();  
		}      
	}
 }  
   
