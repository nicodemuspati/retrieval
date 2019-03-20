package test;

import java.util.ArrayList;
import model.Document;
import model.InvertedIndex;
import model.SearchingResult;

public class testSearching2 {

    public static void main(String[] args) {
        Document doc1 = new Document(1, "Shipment of gold damaged in a fire");
        Document doc2 = new Document(2, "delivery of silver arrived in a silver truck");
        Document doc3 = new Document(3, "shipment of gold arrived in a truck");

        // buat object invertedIndex
        InvertedIndex index = new InvertedIndex();
        // tmbahkan document ke index
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);
        // bikin dictionary
        index.makeDictionaryWithTermNumber();

        // searching
        String query = "silver gold truck";
        System.out.println("Query = " + query);
        ArrayList<SearchingResult> hasilCari1 = index.SearchTFIDF(query);
        for (int i = 0; i < hasilCari1.size(); i++) {
            SearchingResult doc = hasilCari1.get(i);
            System.out.println("IdDoc = " + doc.getDocument().getId() + " : " + doc.getSimilarity());
        }

        // searching
        // searching
        System.out.println("Query = " + query);
        ArrayList<SearchingResult> hasilCari2 = index.SearchCosineSimilarity(query);
        for (int i = 0; i < hasilCari2.size(); i++) {
            SearchingResult doc = hasilCari2.get(i);
            System.out.println("IdDoc = " + doc.getDocument().getId() + " : " + doc.getSimilarity());
        }

    }
}
