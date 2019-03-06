/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.List;
import model.Document;
import model.InvertedIndex;
import model.Posting;
import model.Term;

/**
 *
 * @author admin
 */
public class testDocument9 {

     public static void main(String[] args) {
        Document doc1 = new Document(1, "saya pergi memancing");
        Document doc2 = new Document(2, "pemancingan itu sangat ramai");
        Document doc3 = new Document(3, "saya dapat ikan di pemancingan itu");
        Document doc4 = new Document(4, "memancing itu hobi adik saya");

        InvertedIndex index = new InvertedIndex();
        // tmbahkan document ke index
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);

        // panggil fungsi search
        index.makeDictionaryWithTermNumber();
        ArrayList<Term> result = index.getDictionary();
        // tampilkan isi document dan id-nya
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Term = " + result.get(i).getTerm()
                    + ",numberOfDocument = " + result.get(i).getNumberOfDocument());
            ArrayList<Posting> tempPosting = result.get(i).getTermList().getPosting();
            for (int j = 0; j < tempPosting.size(); j++) {
                System.out.println("id_doc = " + tempPosting.get(j).getDocument().getId()
                        + ", numberofTerm = " + tempPosting.get(j).getNumberOfTerm());
            }
        }

        // number of document
        String tempString = "saya";
        int result2 = index.getDocumentFrequency(tempString);
        System.out.println("Number of Doc with " + tempString + " is " + result2);

        // idf
        String tempString1 = "memancing";
        double result3 = index.getInverseDocumentFrequency(tempString1);
        System.out.println("IDF of " + tempString1 + " is " + result3);

        // tf
        // idf
        String tempString2 = "adik";
        int idDoc = 1;
        int result4 = index.getTermFrequency(tempString2, idDoc);
        System.out.println("TF of " + tempString2 + " in idDoc =" + idDoc + " is " + result4);

    }
}
