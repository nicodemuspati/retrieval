package model;

/**
 *
 * @author admin
 */
public class Posting implements Comparable<Posting> {

    private Document document;
    private String term;

    public Posting() {
    }

    public Posting(Document document, String term) {
        this.document = document;
        this.term = term;
    }

    public Posting(Document document) {
        this.document = document;
    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public int compareTo(Posting posting) {
        return term.compareToIgnoreCase(posting.getTerm());
    }
}