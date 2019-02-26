package model;

/**
 *
 * @author admin
 */
public class Document {
    private int id;
    private String content;

    public Document() {
    }

    public Document(String content) {
        this.content = content;
    }

    public Document(int id, String content) {
        this.id = id;
        this.content = content;
    } 

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public String[] getListofTerm(){
        String[] term = content.split("\\s+");
        for (int i = 0; i < term.length; i++) {
            term[i] = term[i].replaceAll("[^\\w]", "");
        }
        return term;
    }
    
}