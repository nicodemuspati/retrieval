package test;

import java.util.ArrayList;
import java.util.List;
import model.Document;
import model.Posting;

/**
 *
 * @author admin
 */
public class testDocument3 {

    public static void main(String[] args) {
        Document doc1 = new Document(1, "computer information retrieval");
        Document doc2 = new Document(2, "computer oraganization and architecture");

        ArrayList<Document> listofDocument = new ArrayList<Document>();
        listofDocument.add(doc1);
        listofDocument.add(doc2);

        ArrayList<Posting> list = new ArrayList<Posting>();

        for (int i = 0; i < listofDocument.size(); i++) {
            String[] termResult = listofDocument.get(i).getListofTerm();
            for (int j = 0; j < termResult.length; j++) {
                Posting tempPosting = new Posting(listofDocument.get(i), termResult[j]);
                list.add(tempPosting);
            }
        }

        System.out.println("Ukuran list = " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTerm() + " , " + list.get(i).getDocument().getId());
        }

        //test git
    }
}