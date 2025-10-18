package com.pavamana;

public class Main {
    public static void main(String[] args) {
        MongoDBConnector.connect();
        PostgresConnector.connect();
    }
}