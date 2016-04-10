package Plano_B;

public class NoAVL {
    private Word element;                           // Dados do nó
    private NoAVL left, right;                      // Filhos
    private NoAVL parent;                           // Pai
    private int height;                             // Altura do nó
    
    public NoAVL(Word element, NoAVL parent){
        this.element = element;
        this.parent = parent;
    }
    
    public NoAVL(Word element, NoAVL left, NoAVL right, NoAVL parent){
        this.element = element;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.height = 0;
    }

    public Word getElement() {
        return element;
    }

    public void setElement(Word element) {
        this.element = element;
    }

    public NoAVL getLeft() {
        return left;
    }

    public void setLeft(NoAVL left) {
        this.left = left;
    }

    public NoAVL getRight() {
        return right;
    }

    public void setRight(NoAVL right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public NoAVL getParent() {
        return parent;
    }

    public void setParent(NoAVL parent) {
        this.parent = parent;
    }
}
