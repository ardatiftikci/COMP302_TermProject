package domain.saveLoad;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
public class DatabaseSaveLoadAdapter implements ISaveLoadAdapter {

	Logger mongoLogger;
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	@Override
	public void prepare() {
		mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.	
		mongoClient = MongoClients.create("mongodb+srv://comp302_user:comp302_password@sandbox.v2mqr.mongodb.net/"); // uri connection to the server
		database = mongoClient.getDatabase("Comp302"); // selecting the database 
		collection = database.getCollection("KuvidCollection"); // collection 	
	}

	@Override
	public void save(ArrayList<String> saveList, String username) throws IOException{
		Document doc = new Document();
		Bson filter = eq("username", username);
        collection.deleteMany(filter);
		doc.append("username", username);
		doc.append("timeLeft", saveList.get(0));
		doc.append("health", saveList.get(1));
		doc.append("score", saveList.get(2));
		doc.append("atoms", saveList.get(3));
		doc.append("shotPowerups", saveList.get(4));
		doc.append("shields", saveList.get(5));
		doc.append("molecules", saveList.get(6));
		doc.append("reactionBlockers", saveList.get(7));
		doc.append("fallingPowerups", saveList.get(8));
		doc.append("shooter", saveList.get(9));
		doc.append("level", saveList.get(10));
		for(int i=11;i<saveList.size();i++) {
			doc.append("object"+(i-10), saveList.get(i));
		}
		doc.append("objectNumber", Integer.toString(saveList.size()-11));

		collection.insertOne(doc);
		System.out.println("Game saved to the database for " +username);
	}

	@Override
	public ArrayList<String> load(String username) throws IOException {
		ArrayList<String> loadList = new ArrayList<String>();
		Document my_doc = collection.find(eq("username", username)).first();
		if(my_doc==null) {
			return loadList;
		}
		loadList.add(my_doc.get("timeLeft").toString());
		loadList.add(my_doc.get("health").toString());
		loadList.add(my_doc.get("score").toString());
		loadList.add(my_doc.get("atoms").toString());
		loadList.add(my_doc.get("shotPowerups").toString());
		loadList.add(my_doc.get("shields").toString());
		loadList.add(my_doc.get("molecules").toString());
		loadList.add(my_doc.get("reactionBlockers").toString());
		loadList.add(my_doc.get("fallingPowerups").toString());
		loadList.add(my_doc.get("shooter").toString());
		loadList.add(my_doc.get("level").toString());
		int objectNumber = Integer.parseInt((my_doc.get("objectNumber").toString()));
		for(int i=1;i<=objectNumber;i++) {
			loadList.add(my_doc.get("object"+i).toString());
		}
		System.out.println("Game successfully loaded from the database for "+username);

		return loadList;
	}

}
