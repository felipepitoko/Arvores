package Plano_B;

public class NoRN {
    private Word element;
    private NoRN dad, left, right;
    private boolean cor;
    
    public NoRN(){}
    
    public NoRN(Word element){
        this.element = element;
    }
    
    public NoRN(Word element, NoRN left, NoRN right){
        this.element = element;
        this.left = left;
        this.right = right;
        this.cor = false;
    }
    
    /**
     * @return the element
     */
    public Word getElement() {
        return element;
    }

    /**
     * @param element the element to set
     */
    public void setElement(Word element) {
        this.element = element;
    }

    /**
     * @return the dad
     */
    public NoRN getDad() {
        return dad;
    }

    /**
     * @param dad the dad to set
     */
    public void setDad(NoRN dad) {
        this.dad = dad;
    }

    /**
     * @return the left
     */
    public NoRN getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(NoRN left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public NoRN getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(NoRN right) {
        this.right = right;
    }

    /**
     * @return the cor
     */
    public boolean isCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(boolean cor) {
        this.cor = cor;
    }
}
