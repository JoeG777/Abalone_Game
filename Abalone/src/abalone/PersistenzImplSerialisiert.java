package abalone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenzImplSerialisiert implements PersistenzInterface{
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	@Override
	public void oeffnen(String dateiName) throws FileNotFoundException, IOException {
		ois = new ObjectInputStream(new FileInputStream(dateiName));
		oos = new ObjectOutputStream(new FileOutputStream(dateiName));
	}

	@Override
	public void schliessen() throws IOException {
		oos.close();
		ois.close();
	}

	@Override
	public Object lesen() throws ClassNotFoundException, IOException {
		Object gelesenesObjekt = ois.readObject();
		return gelesenesObjekt;
	}

	@Override
	public void schreiben(Object zuSchreibendesObjekt) throws IOException {
		oos.writeObject(zuSchreibendesObjekt);
	}

}
