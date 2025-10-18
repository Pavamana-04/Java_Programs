package com.pavamana;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnector {
    public static MongoDatabase connect() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("bootcampDB");
        System.out.println("Connected to MongoDB");
        return database;
    }
}