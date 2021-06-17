package domain.saveLoad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaveLoadAdapter implements ISaveLoadAdapter {

	@Override
	public void prepare() {

	}
	@Override
	public void save(ArrayList<String> saveList, String username) throws IOException {
		File dir = new File("save/");
		if (!dir.exists()) dir.mkdirs();
		File file = new File("save/"+username+".txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(String saveItem : saveList) {
			writer.write(saveItem+"\n");
		}
		System.out.println("Game saved to file: " +username+".txt");
		writer.close();
	}

	@Override
	public ArrayList<String> load(String username) throws IOException {
		ArrayList<String> loadList = new ArrayList<String>();
		File file = new File("save/"+username+".txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String loadLine = reader.readLine();
		while(loadLine!=null) {
			loadList.add(loadLine);
			loadLine=reader.readLine();
		}
		reader.close();
		System.out.println("Save file for "+username+" successfully loaded.");
		return loadList;
	}

}
