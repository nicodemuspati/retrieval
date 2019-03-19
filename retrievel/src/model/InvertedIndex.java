package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class InvertedIndex {

    private ArrayList<Document> listOfDocument = new ArrayList<Document>();
    private ArrayList<Term> dictionary = new ArrayList<Term>();

    public InvertedIndex() {
        this.dictionary = new ArrayList<>();
        this.listOfDocument = new ArrayList<>();
    }

    public void addNewDocument(Document document) {
        getListOfDocument().add(document);
    }

    public ArrayList<Posting> getUnsortedPostingList() {
        // cek untuk term yang muncul lebih dari 1 kali
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // buat node Posting utk listofdocument
        for (int i = 0; i < getListOfDocument().size(); i++) {
            // buat listOfTerm dari document ke -i
            String[] termResult = getListOfDocument().get(i).getListofTerm();
            // loop sebanyak term dari document ke i
            for (int j = 0; j < termResult.length; j++) {
                // buat object tempPosting
                Posting tempPosting = new Posting(termResult[j],
                        getListOfDocument().get(i));
                // cek kemunculan term
                list.add(tempPosting);
            }
        }
        return list;
    }

    public ArrayList<Posting> getUnsortedPostingListWithTermNumber() {
        // cek untuk term yang muncul lebih dari 1 kali
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // buat node Posting utk listofdocument
        for (int i = 0; i < getListOfDocument().size(); i++) {
            // buat listOfTerm dari document ke -i
            //String[] termResult = getListOfDocument().get(i).getListofTerm();
            ArrayList<Posting> postingDocument = getListOfDocument().get(i).getListOfPosting();
            // loop sebanyak term dari document ke i
            for (int j = 0; j < postingDocument.size(); j++) {
                // ambil objek posting
                Posting tempPosting = postingDocument.get(j);
                // cek kemunculan term
                list.add(tempPosting);
            }
        }
        return list;
    }

    public ArrayList<Posting> getSortedPostingList() {
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // panggil list yang belum terurut
        list = this.getUnsortedPostingList();
        // urutkan
        Collections.sort(list);
        return list;
    }

    public ArrayList<Posting> getSortedPostingListWithTermNumber() {
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // panggil list yang belum terurut
        list = this.getUnsortedPostingListWithTermNumber();
        // urutkan
        Collections.sort(list);
        return list;
    }

    public ArrayList<Posting> Search(String query) {
        // buat index/dictionary
//        makeDictionary();
        String tempQuery[] = query.split(" ");
        ArrayList<Posting> result = new ArrayList<Posting>();
        for (int i = 0; i < tempQuery.length; i++) {
            String string = tempQuery[i];
            if (i == 0) {
                result = SearchOneWord(string);
            } else {
                ArrayList<Posting> result1 = SearchOneWord(string);
                result = Intersection(result, result1);
            }
        }
        return result;
    }

    public ArrayList<Posting> Intersection(ArrayList<Posting> p1,
            ArrayList<Posting> p2) {
        if (p1 == null || p2 == null) {
            return new ArrayList<>();
        }

        ArrayList<Posting> posting = new ArrayList<>();
        int p1Index = 0;
        int p2Index = 0;

        Posting post1 = p1.get(p1Index);
        Posting post2 = p2.get(p2Index);

        while (true) {
            if (post1.getDocument().getId() == post2.getDocument().getId()) {
                try {
                    posting.add(post1);
                    p1Index++;
                    p2Index++;
                    post1 = p1.get(p1Index);
                    post2 = p2.get(p2Index);
                } catch (Exception e) {
                    break;
                }

            } else if (post1.getDocument().getId() < post2.getDocument().getId()) {
                try {
                    p1Index++;
                    post1 = p1.get(p1Index);
                } catch (Exception e) {
                    break;
                }

            } else {
                try {
                    p2Index++;
                    post2 = p2.get(p2Index);
                } catch (Exception e) {
                    break;
                }
            }
        }
        return posting;
    }

    public ArrayList<Posting> SearchOneWord(String word) {
        Term tempTerm = new Term(word);
        if (getDictionary().isEmpty()) {
            // dictionary kosong
            return null;
        } else {
            int positionTerm = Collections.binarySearch(dictionary, tempTerm);
            if (positionTerm < 0) {
                // tidak ditemukan
                return null;
            } else {
                return dictionary.get(positionTerm).getPostingList();
            }
        }
    }

    public void MakeDictionary() {
        ArrayList<Posting> list = getSortedPostingList();
        for (int i = 0; i < list.size(); i++) {
            if (getDictionary().isEmpty()) {
                Term term = new Term(list.get(i).getTerm());
                term.getPostingList().add(list.get(i));
                getDictionary().add(term);
            } else {
                Term tempTerm = new Term(list.get(i).getTerm());
                int position = Collections.binarySearch(getDictionary(), tempTerm);
                if (position < 0) {
                    tempTerm.getPostingList().add(list.get(i));
                    getDictionary().add(tempTerm);
                } else {
                    getDictionary().get(position).
                            getPostingList().add(list.get(i));
                    Collections.sort(getDictionary().get(position)
                            .getPostingList());
                }
                Collections.sort(getDictionary());
            }
        }
    }

    public void makeDictionaryWithTermNumber() {
        // cek deteksi ada term yang frekuensinya lebih dari 
        // 1 pada sebuah dokumen
        // buat posting list term terurut
        ArrayList<Posting> list = getSortedPostingListWithTermNumber();
        // looping buat list of term (dictionary)
        for (int i = 0; i < list.size(); i++) {
            // cek dictionary kosong?
            if (getDictionary().isEmpty()) {
                // buat term
                Term term = new Term(list.get(i).getTerm());
                // tambah posting ke posting list utk term ini
                term.getPostingList().add(list.get(i));
                // tambah ke dictionary
                getDictionary().add(term);
            } else {
                // dictionary sudah ada isinya
                Term tempTerm = new Term(list.get(i).getTerm());
                // pembandingan apakah term sudah ada atau belum
                // luaran dari binarysearch adalah posisi
                int position = Collections.binarySearch(getDictionary(), tempTerm);
                if (position < 0) {
                    // term baru
                    // tambah postinglist ke term
                    tempTerm.getPostingList().add(list.get(i));
                    // tambahkan term ke dictionary
                    getDictionary().add(tempTerm);
                } else {
                    // term ada
                    // tambahkan postinglist saja dari existing term
                    getDictionary().get(position).
                            getPostingList().add(list.get(i));
                    // urutkan posting list
                    Collections.sort(getDictionary().get(position)
                            .getPostingList());
                }
                // urutkan term dictionary
                Collections.sort(getDictionary());
            }

        }

    }

    public ArrayList<Document> getListOfDocument() {
        return listOfDocument;
    }

    public void setListOfDocument(ArrayList<Document> listOfDocument) {
        this.listOfDocument = listOfDocument;
    }

    public ArrayList<Term> getDictionary() {
        return dictionary;
    }

    public void setDictionary(ArrayList<Term> dictionary) {
        this.dictionary = dictionary;
    }

    public int getDocumentFrequency(String term) {
        Term tempTerm = new Term(term);
        // cek apakah term ada di dictionary
        int index = Collections.binarySearch(dictionary, tempTerm);
        if (index > 0) {
            // term ada
            // ambil ArrayList<Posting> dari object term
            ArrayList<Posting> tempPosting = dictionary.get(index)
                    .getPostingList();
            // return ukuran posting list
            return tempPosting.size();
        } else {
            // term tidak ada
            return -1;
        }
    }

    public double getInverseDocumentFrequency(String term) {
        Term tempTerm = new Term(term);
        // cek apakah term ada di dictionary
        int index = Collections.binarySearch(dictionary, tempTerm);
        if (index > 0) {
            // term ada
            // jumlah total dokumen
            int N = listOfDocument.size();
            // jumlah dokumen dengan term i
            int ni = getDocumentFrequency(term);
            // idf = log10(N/ni)
            double Nni = (double) N / ni;
            return Math.log10(Nni);
        } else {
            // term tidak ada
            // nilai idf = 0
            return 0.0;
        }
    }

    public int getTermFrequency(String term, int idDoc) {
        Document doc = new Document();
        doc.setId(idDoc);

        int Posting = Collections.binarySearch(listOfDocument, doc);
        if (Posting >= 0) {
            ArrayList<Posting> tempPosting = listOfDocument.get(Posting).getListOfPosting();
            Posting posting = new Posting();
            posting.setTerm(term);
            int postingIndex = Collections.binarySearch(tempPosting, posting);
            if (postingIndex >= 0) {
                return tempPosting.get(postingIndex).getNumberOfTerm();
            }
            return 0;
        }

        return 0;
    }

    public ArrayList<Posting> MakeTFIDF(int idDoc) {
        ArrayList<Posting> result = new ArrayList<Posting>();
        Document temp = new Document(idDoc);
        int cari = Collections.binarySearch(listOfDocument, temp);
        if (cari >= 0) {
            temp = listOfDocument.get(cari);
            result = temp.getListOfPosting();
            for (int i = 0; i < result.size(); i++) {
                String tempTerm = result.get(i).getTerm();
                double idf = getInverseDocumentFrequency(tempTerm);
                int tf = result.get(i).getNumberOfTerm();
                double bobot = tf * idf;
                result.get(i).setWeight(bobot);
            }
            Collections.sort(result);
        } else {

        }
        return result;
    }

    public ArrayList<Posting> MakeQueryTFIDF(String query) {
        Document doc = new Document();
        doc.setContent(query);
        ArrayList<Posting> result = doc.getListOfPosting();
        for (int i = 0; i < result.size(); i++) {
            // weight = tf * idf
            double weight = result.get(i).getNumberOfTerm() * getInverseDocumentFrequency(result.get(i).getTerm());
            result.get(i).setWeight(weight);
        }
        return result;
    }

    public Double getInnerProduct(ArrayList<Posting> p1, ArrayList<Posting> p2) {

        Collections.sort(p2);
        Collections.sort(p1);

        double result = 0;
        for (int i = 0; i < p1.size(); i++) {
            Posting temp = p1.get(i);
            boolean found = false;
            for (int j = 0; j < p2.size() && found == false; j++) {
                Posting temp1 = p2.get(j);
                if (temp1.getTerm().equalsIgnoreCase(temp.getTerm())) {
                    found = true;
                    result = result + p1.get(i).getWeight() * temp.getWeight();
                }
            }
        }
        return result;
    }

    public ArrayList<Posting> getQueryPosting(String query) {
        Document temp = new Document(-1, query);
        // buat posting list
        ArrayList<Posting> result = temp.getListOfPosting();
        // hitung bobot
        // isi bobot dari posting list
        for (int i = 0; i < result.size(); i++) {
            // ambil term
            String tempTerm = result.get(i).getTerm();
            // cari idf
            double idf = getInverseDocumentFrequency(tempTerm);
            // cari tf
            int tf = result.get(i).getNumberOfTerm();
            // hitung bobot
            double bobot = tf * idf;
            // set bobot pada posting
            result.get(i).setWeight(bobot);
        }
        Collections.sort(result);
        return result;
    }

   public double getLengthOfPosting(ArrayList<Posting> posting) {
        double length = 0;
        for (int i = 0; i < posting.size(); i++) {
            length += posting.get(i).getWeight() * posting.get(i).getWeight();
        }

        return Math.sqrt(length);
    }

    public double getCosineSimilarity(ArrayList<Posting> posting, ArrayList<Posting> posting1) {
        double innerProduct = getInnerProduct(posting, posting1);
        double length = getLengthOfPosting(posting)* getLengthOfPosting(posting1);
        double cosineSimilarity = innerProduct / length;
        return cosineSimilarity;
    }

    public ArrayList<SearchingResult> SearchTFIDF(String query) {
        ArrayList<SearchingResult> SearchingResult = new ArrayList<>();
        ArrayList<Posting> queryTFIDF = MakeQueryTFIDF(query);
        for (int i = 0; i < getDocumentFrequency(query); i++) {
            ArrayList<Posting> documentTFIDF = MakeTFIDF(listOfDocument.get(i).getId());
            double sim = getInnerProduct(queryTFIDF, documentTFIDF);
            SearchingResult.add(new SearchingResult(sim, listOfDocument.get(i)));
        }
        Collections.sort(SearchingResult, Collections.reverseOrder());
        
        return SearchingResult;
    }

    public ArrayList<SearchingResult> SearchCosineSimilarity(String query) {
        ArrayList<SearchingResult> SearchingResult = new ArrayList<>();
        ArrayList<Posting> queryTFIDF = MakeQueryTFIDF(query);
        for (int i = 0; i < getDocumentFrequency(query); i++) {
            ArrayList<Posting> documentTFIDF = MakeTFIDF(listOfDocument.get(i).getId());
            double similarity = getCosineSimilarity(queryTFIDF, documentTFIDF);
            SearchingResult.add(new SearchingResult(similarity, listOfDocument.get(i)));
        }
        Collections.sort(SearchingResult, Collections.reverseOrder());
        return SearchingResult;
    }
}
