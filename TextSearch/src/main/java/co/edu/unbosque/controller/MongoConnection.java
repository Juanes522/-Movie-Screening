package co.edu.unbosque.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class MongoConnection {

	private static final String CONNECTION_STRING = "mongodb://localhost:27017";
	private static final String DATABASE_NAME = "BD_PELICULAS";

	private Datastore datastore;

	public MongoConnection() {

		MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);

		datastore = Morphia.createDatastore(mongoClient, DATABASE_NAME);

	}

//	 public String getParticipacionesYPremios(String personaId) {
//	        StringBuilder resultBuilder = new StringBuilder();
//	        MongoCollection<Document> personasCollection = datastore.getCollection("Personas");
//
//	        List<Bson> pipeline = Arrays.asList(
//	            Aggregates.match(Filters.eq("_id", personaId)),
//	            Aggregates.lookup("Peliculas", "_id", "tareas.nombre_persona", "participaciones"),
//	            Aggregates.unwind("$reconocimientos", new LookupOptions().preserveNullAndEmptyArrays(true)),
//	            Aggregates.lookup("Festivales", "reconocimientos.festival", "_id", "festivalDetalles"),
//	            Aggregates.project(Projections.fields(
//	                Projections.include("_id"),
//	                Projections.include("participaciones"),
//	                Projections.include("reconocimientos.premio"),
//	                Projections.include("reconocimientos.certamen"),
//	                Projections.computed("festival", new Document("$arrayElemAt", Arrays.asList("$festivalDetalles._id", 0)))
//	            )),
//
//	            Aggregates.group("$_id",
//	                new Document("participaciones", new Document("$first", "$participaciones")),
//	                new Document("premios", new Document("$push", new Document()
//	                    .append("premio", "$reconocimientos.premio")
//	                    .append("festival", "$festival")
//	                    .append("certamen", "$reconocimientos.certamen"))))
//	        );
//
//	        for (Document doc : personasCollection.aggregate(pipeline)) {
//	            resultBuilder.append(doc.toJson()).append("\n");
//	        }
//
//	        return resultBuilder.toString();
//	    }

	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

}
