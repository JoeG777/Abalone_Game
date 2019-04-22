package abalone;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PersistenzInterface {
	
	public void oeffnen(String dateiName) throws FileNotFoundException, IOException;
	public void schliessen() throws IOException;
	public Object lesen() throws ClassNotFoundException, IOException;
	public void schreiben(Object zuSchreibendesObjekt) throws IOException;
	
}
