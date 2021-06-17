package domain.saveLoad;

import java.io.IOException;
import java.util.ArrayList;

public interface ISaveLoadAdapter {

	public void prepare();
	public void save(ArrayList<String> saveList, String username) throws IOException;
	public ArrayList<String> load(String username) throws IOException;
}
