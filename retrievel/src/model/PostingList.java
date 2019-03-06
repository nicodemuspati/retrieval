/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PostingList {

    private ArrayList<Posting> Posting;

    public PostingList(ArrayList<Posting> Posting) {
        this.Posting = Posting;
    }

    public PostingList() {
        this.Posting = new ArrayList<>();
    }

    public ArrayList<Posting> getPosting() {
        return Posting;
    }

    public void setPostings(ArrayList<Posting> Posting) {
        this.Posting = Posting;
    }
}
