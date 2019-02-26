package test;

import java.util.ArrayList;
import model.Document;
import model.Posting;

/**
 *
 * @author admin
 */
public class testDocument2 {

    public static void main(String[] args) {
        Document doc1 = new Document(1, "computer information retrieval");
        Document doc2 = new Document(2, "computer oraganization and architecture");

        String tokenDoc1[] = doc1.getListofTerm();
        String tokenDoc2[] = doc2.getListofTerm();

        ArrayList<Posting> list = new ArrayList<Posting>();

        for (int i = 0; i < tokenDoc1.length; i++) {
            Posting tempPost = new Posting(doc1, tokenDoc1[i]);
            list.add(tempPost);
        }

        for (int i = 0; i < tokenDoc2.length; i++) {
            Posting tempPost = new Posting(doc2, tokenDoc2[i]);
            list.add(tempPost);
        }

        System.out.println("Ukuran list = " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTerm() + " , " + list.get(i).getDocument().getId());
        }

        //test git
    }
}