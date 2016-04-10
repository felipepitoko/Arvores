package Plano_B;

public class WordInDoc {
    private String idDoc;   //Id do documento
    private int quantity;   //Quantidade de vezes de uma palavra

    public WordInDoc(String idDoc, int quantity){
        this.idDoc = idDoc;
        this.quantity = quantity;
    }
    
    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
