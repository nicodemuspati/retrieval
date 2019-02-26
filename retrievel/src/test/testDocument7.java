package test;

import java.util.ArrayList;
import model.Document;
import model.InvertedIndex;
import model.Posting;
import model.Term;

/**
 *
 * @author admin
 */
public class testDocument7 {

    public static void main(String[] args) {
        Document doc1 = new Document(1, "computer information retrieval");
        Document doc2 = new Document(2, "computer organization and architecture");
        Document doc3 = new Document(3, "machine learning architecture");

        InvertedIndex index = new InvertedIndex();
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);

        index.makeDictionary();
        ArrayList<Posting> result = index.searchOneWord("computer");

        for (int i = 0; i < result.size(); i++) {
            System.out.println("idDoc = " + result.get(i).getDocument().getId());
            System.out.println(result.get(i).getDocument().getContent());
            System.out.println("");
        }

//        ArrayList<Posting> result1 = index.searchOneWord("machine organization");
//
//        for (int i = 0; i < result1.size(); i++) {
//            System.out.println("idDoc = " + result1.get(i).getDocument().getId());
//            System.out.println(result1.get(i).getDocument().getContent());
//        }
        //test git
    }
}
