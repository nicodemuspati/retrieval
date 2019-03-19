package model;

import java.util.ArrayList;

public class Term implements Comparable<Term> {

    private String term;
    private ArrayList<Posting> PostingList = new ArrayList<Posting>();
    private PostingList TermList;

    public Term() {
        TermList = new PostingList();
    }

    public Term(String term, PostingList termList) {
        this.term = term;
        this.TermList = termList;
    }

    public Term(String term) {
        this.term = term;
        this.TermList = new PostingList();
    }

    public ArrayList<Posting> getPostingList() {
        return PostingList;
    }

    public void setPostingList(ArrayList<Posting> PostingList) {
        this.PostingList = PostingList;
    }

    public int getNumberOfDocument() {
        return PostingList.size();
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public PostingList getTermList() {
        return TermList;
    }

    public void setTermList(PostingList TermList) {
        this.TermList = TermList;
    }

    @Override
    public int compareTo(Term o) {
        return term.compareToIgnoreCase(o.getTerm());
    }
}
