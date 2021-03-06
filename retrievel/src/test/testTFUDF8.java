package test;

import model.Document;
import model.InvertedIndex;
import model.Posting;
import model.Term;
import java.util.ArrayList;

public class testTFUDF8 {

    public static void main(String[] args) {
        // seting dokumen
        Document doc1 = new Document(1, "Shipment of gold damaged in a fire");
        Document doc2 = new Document(2, "delivery of silver arrived in a silver truck");
        Document doc3 = new Document(3, "shipment of gold arrived in a truck");

        // membuat object invertedIndex
        InvertedIndex index = new InvertedIndex();
        // tmbahkan document ke index
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);

        // mamanggil fungsi search
        index.makeDictionaryWithTermNumber();
        ArrayList<Term> result = index.getDictionary();
        // tampilkan isi document dan id-nya
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Term = " + result.get(i).getTerm()
                    + ",numberOfDocument = " + result.get(i).getNumberOfDocument());
            ArrayList<Posting> tempPosting = result.get(i).getPostingList();
            for (int j = 0; j < tempPosting.size(); j++) {
                System.out.println("id_doc = " + tempPosting.get(j).getDocument().getId()
                        + ", numberofTerm = " + tempPosting.get(j).getNumberOfTerm());
            }
        }

        // number of document
        String tempString = "silver";
        int result2 = index.getDocumentFrequency(tempString);
        System.out.println("Number of Doc with " + tempString + " is " + result2);

        // idf
        String tempString1 = "silver";
        double result3 = index.getInverseDocumentFrequency(tempString1);
        System.out.println("IDF of " + tempString1 + " is " + result3);

        // tf dan  idf
        String tempString2 = "truck";
        int idDoc = 2;
        int result4 = index.getTermFrequency(tempString2, idDoc);
        System.out.println("TF of " + tempString2 + " in idDoc = " + idDoc + " is " + result4);

        // make arraylist of TFIDF
        idDoc = 1;
        ArrayList<Posting> tempDocWeight = index.MakeTFIDF(idDoc);
        for (int i = 0; i < tempDocWeight.size(); i++) {
            Posting tempPost = tempDocWeight.get(i);
            System.out.println("term= " + tempPost.getTerm()
                    + ", tf = " + tempPost.getNumberOfTerm()
                    + ", weight= " + tempPost.getWeight());
        }

        idDoc = 2;
        ArrayList<Posting> temp1DocWeight = index.MakeTFIDF(idDoc);
        for (int i = 0; i < temp1DocWeight.size(); i++) {
            Posting tempPost = temp1DocWeight.get(i);
            System.out.println("term= " + tempPost.getTerm()
                    + ", tf = " + tempPost.getNumberOfTerm()
                    + ", weight= " + tempPost.getWeight());
        }
        // memanggil fungsi inner product
       
        double resultProduct = index.getInnerProduct(temp1DocWeight, tempDocWeight);
        System.out.println("Hasil Inner Product doc1 dan doc2 = " + resultProduct);
       
        //panggil fungsi buat postinglist dar sebuah query
        ArrayList<Posting> queryPostingList = index.getQueryPosting("silver gold truck");
        for (int i = 0; i < queryPostingList.size(); i++) {
            Posting tempPost = queryPostingList.get(i);
            System.out.println("term= " + tempPost.getTerm()
                    + ", tf = " + tempPost.getNumberOfTerm()
                    + ", weight= " + tempPost.getWeight());
        }
        // memanggil fungsi inner product
      
        double result1Product = index.getInnerProduct(queryPostingList, tempDocWeight);
        System.out.println("Hasil  dari Inner Product Query dan doc1= " + result1Product);
        

    }
}
