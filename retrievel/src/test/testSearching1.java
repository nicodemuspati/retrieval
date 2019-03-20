package test;

import java.util.ArrayList;
import model.Document;
import model.InvertedIndex;
import model.Posting;

public class testSearching1 {

    public static void main(String[] args) {

        // seting dokumen
        Document doc1 = new Document(1, "Shipment of gold damaged in a fire");
        Document doc2 = new Document(2, "delivery of silver arrived in a silver truck");
        Document doc3 = new Document(3, "shipment of gold arrived in a truck");

        // buat object invertedIndex
        InvertedIndex index = new InvertedIndex();
        // menambahkan document ke index
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);
        // bikin dictionary
        index.makeDictionaryWithTermNumber();

        // id dokumen
        int idDoc = 1;
        System.out.println("IdDoc = " + idDoc);
        ArrayList<Posting> tempDocWeight = index.MakeTFIDF(idDoc);
        for (int i = 0; i < tempDocWeight.size(); i++) {
            Posting tempPost = tempDocWeight.get(i);
            System.out.println("Term= " + tempPost.getTerm()
                    + ", tf = " + tempPost.getNumberOfTerm()
                    + ", weight= " + tempPost.getWeight());
        }

        System.out.println("Length of Document = " + index.getLengthOfPosting(tempDocWeight));

        String query = "silver gold truck";
        System.out.println("Query = " + query);
        ArrayList<Posting> queryPostingList = index.getQueryPosting(query);
        for (int i = 0; i < queryPostingList.size(); i++) {
            Posting tempPosting = queryPostingList.get(i);
            System.out.println("Term= " + tempPosting.getTerm()
                    + ", tf = " + tempPosting.getNumberOfTerm()
                    + ", weight= " + tempPosting.getWeight());
        }

        // test cosine similarity
        System.out.println("Cosine Similarity = " + index.getCosineSimilarity(queryPostingList,
                tempDocWeight));
    }

}
