package Plano_B;

public class ArvoreAVL {
    private NoAVL root;
    
    // Inserção na Árvore AVL
    public void insertAVL(NoAVL x, Word element){
        // Se for o primeiro nó vira Raiz
        if(x == null)
            setRoot(new NoAVL(element, null));
        // Se o nó atual for maior que o novo, vai para esquerda
        else if(x.getElement().getWord().compareTo(element.getWord()) > 0){
            // Se tiver elemento continua
            if(x.getLeft() != null)
                insertAVL(x.getLeft(), element);
            // Se estiver vazio insere, o pai é o nó atual
            else{
                x.setLeft(new NoAVL(element, x));
                balanceAVL(x);
            }
        }
        // Se o nó atual for menor que o novo, vai para esqueda
        else{
            if(x.getRight() != null)
                insertAVL(x.getRight(), element);
            else{
                x.setRight(new NoAVL(element, x));
                balanceAVL(x);
            }
        }
        
    }
    
    // Rotação para Direita
    public NoAVL rotateRight(NoAVL y){
        NoAVL x = y.getLeft();
        y.setLeft(x.getRight());
        x.setRight(y);
        y.setHeight(Math.max(getHeight(y.getLeft()), getHeight(y.getRight()))+1);
        x.setHeight(Math.max(getHeight(x.getLeft()), y.getHeight())+1);
        return x;
    }
    
    // Rotação para Esquerda
    public NoAVL rotateLeft(NoAVL x){
        NoAVL y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);
        x.setHeight(Math.max(getHeight(x.getLeft()), getHeight(x.getRight()))+1);
        y.setHeight(Math.max(getHeight(y.getRight()), x.getHeight())+1);
        return y;
    }
    
    // Rotação Simples Esquerda + Rotação Simples Direita
    public NoAVL rotateDoubleRight(NoAVL x){
        x.setLeft(rotateLeft(x.getLeft()));
        return rotateRight(x);
    }
    
    // Rotação Simples Direita + Rotação Simples Esquerda
    public NoAVL rotateDoubleLeft(NoAVL x){
        x.setRight(rotateRight(x.getRight()));
        return rotateLeft(x);
    }
    
    // Faz o Balanceamento
    public void balanceAVL(NoAVL x){
        
        // Se preciasa balancear, faz as rotações 
        if(balancingFactor(x) == 2){
            if(balancingFactor(x.getLeft()) == -1)
                x = rotateDoubleLeft(x);
            else{
                if(x.getRight() != null)
                    x = rotateLeft(x);
            }
        }
        else if(balancingFactor(x) == -2){
            if(balancingFactor(x.getRight()) == 1)
                x = rotateDoubleRight(x);
            else{
                if(x.getLeft()!= null)
                    x = rotateRight(x);
            }
        }
        
        // Se ainda não chegamos na raiz, continuamos a balancear
        if(x.getParent() != null){
            balanceAVL(x.getParent());
        }
        // Se chegamos, atualiza a nova raiz
        else{
            setRoot(x);
        }
    }
    
    // Fator de Balanceamento
    public int balancingFactor(NoAVL x){
        return getHeight(x.getLeft())-getHeight(x.getRight());
    }
    
    // Retorna a altura
    public int getHeight(NoAVL x){
        if(x == null)
            return -1;
        int h1 = getHeight(x.getLeft());
        int h2 = getHeight(x.getRight());
        
        return (1+Math.max(h1,h2));
    }
    
    // Busca na Árvore AVL
    public Word searchAVL(String element){
        NoAVL cur = getRoot();  //No que ira percorrer
        while (cur != null) {   //Enquanto não for null
            if (!cur.getElement().getWord().equals(element)) {  //Verifica se é igual
                if(cur.getElement().getWord().compareTo(element) > 0){  //Verifica se é menor
                 cur = cur.getLeft();   //Vai para esquerda
                }
                else{
                    cur = cur.getRight();   //Vai para direita
                }
            } else {
                break;
            }
        }            
        if(cur == null) //Verifica se não encontrou
            return null;
        else
            return cur.getElement();    //Retorna o elemento encontrado
    }

    public NoAVL getRoot() {
        return root;
    }

    public void setRoot(NoAVL root) {
        this.root = root;
    }
}
