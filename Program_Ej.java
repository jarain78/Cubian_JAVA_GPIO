import java.io.IOException;
import CubianGPIOControl.GPIO;
public class Program_Ej {
	static GPIO gpio;
	public static void main(String[] args)  {
		gpio = new GPIO();
		try {
			gpio.pinMode("11","OUTPUT");
			gpio.pinMode("10","INPUT");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			gpio.digitalWrite("HIGH");
			gpio.delayMs(100);
			gpio.digitalWrite("LOW");
			gpio.delayMs(100);
			
			// read state pin
			try {
				gpio.digitalRead();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
