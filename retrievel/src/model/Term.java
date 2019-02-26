package model;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Term implements Comparable<Term>{

    private String term;
    private ArrayList<Posting> postingList = new ArrayList<Posting>();

    public Term() {
    }

    public Term(String term) {
        this.term = term;
    }

    
    public int getNumberofDocument() {
        return postingList.size();
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

    /**
     * @return the postingList
     */
    public ArrayList<Posting> getPostingList() {
        return postingList;
    }

    /**
     * @param postingList the postingList to set
     */
    public void setPostingList(ArrayList<Posting> postingList) {
        this.postingList = postingList;
    }

    @Override
    public int compareTo(Term o) {
        return term.compareToIgnoreCase(o.getTerm());
    }
}