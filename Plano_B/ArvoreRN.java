package Plano_B;

public class ArvoreRN {
    private NoRN root;  //Raiz da arvore
    private NoRN nil;   //No nulo
    
    public ArvoreRN(){
        this.nil = new NoRN();
        this.root = this.nil;
    }
    
    //Função que rotaciona o no para esquerda
    private void leftRotate(NoRN x){
        NoRN y = x.getRight();  //Pega o elemento a direita do no x
        x.setRight(y.getLeft());    //Insere o elemento à esquerda de y a direita de x
        y.getLeft().setDad(x);  //O elemento a esquerda de y sera filho de x
        y.setDad(x.getDad());   //O y vira filho do pai de x
        if(x.getDad().equals(getNil())){    //Verifica se x é raiz
            setRoot(y); //O y vira raiz
        }
        else{
            if(x.equals(x.getDad().getLeft())){ //Verifica se x é filho a esquerda
                x.getDad().setLeft(y);  //O y vira filho do pai de x a esquerda
            }
            else{
                x.getDad().setRight(y); //O y vira filho do pai de x a direita
            }
        }
        y.setLeft(x);   //Insere o elemento x à esquerda de y
        x.setDad(y);    //O x vira filho de y
    }
    
    //Função que rotaciona o no para direita
    private void rightRotate(NoRN x){ 
        NoRN y = x.getLeft();   //Pega o elemento a esquerda do no x
        x.setLeft(y.getRight());    //Insere o elemento à direita de y a esquerda de x
        y.getRight().setDad(x); //O elemento a direita de y sera filho de x
        y.setDad(x.getDad());   //O y vira filho do pai de x
        if(x.getDad().equals(getNil())){    //Verifica se x é raiz
            setRoot(y); //O y vira raiz
        }
        else{
            if(x.equals(x.getDad().getRight())){    //Verifica se x é filho a direita
                x.getDad().setRight(y); //O y vira filho do pai de x a direita
            }
            else{
                x.getDad().setLeft(y);  //O y vira filho do pai de x a esquerda
            }
        }
        y.setRight(x);  //Insere o elemento x à direita de y
        x.setDad(y);    //O x vira filho de y
    }
    
    //Função que insere o Word na arvore
    public void insertRN(Word word){
        NoRN z = new NoRN(word);    //Novo no a ser inserido
        NoRN y = getNil();  //No que guadar a folha que ira ser pai do novo no
        NoRN x = getRoot(); //No que ira percorrer a arvore
        while(!x.equals(getNil())){ //Enquanto não terminar a arvore
            y = x;  //Guarda o no valido
            if(z.getElement().getWord().compareTo(x.getElement().getWord()) < 0){   //Verifica se o novo no é menor que o no atual
                x = x.getLeft();    //Vai para esquerda
            }
            else{
                x = x.getRight();   //Vai para direita
            }
        }
        z.setDad(y);    //O novo no vira filho da folha
        if(y.equals(getNil())){ //Verifica se a arvore estava vazia
            setRoot(z); //O novo no passa a ser raiz
        }
        else{
            if(z.getElement().compareTo(y.getElement()) < 0){   //Verifica se o novo no é menor que a folha
                y.setLeft(z);   //Insere o novo no a esquerda da folha
            }
            else{
                y.setRight(z);  //Insere o novo no a direita da folha
            }
        }
        z.setLeft(getNil());    //O novo no não tem filhos a esquerda
        z.setRight(getNil());   //O novo no não tem filhos a direita
        z.setCor(true); //O novo no é vermelho
        insertFixupRN(z);   //Chamada a função que normaliza a arvore
    }
    
    //Função que normaliza a arvore na regiao onde o no foi inserido
    private void insertFixupRN(NoRN z){
        NoRN y;
        while(z.getDad().isCor()){  //Enquanto o pai do no z for vermelho
            if(z.getDad().equals(z.getDad().getDad().getLeft())){   //Verifica se o pai de z é filho a esquerda
                y = z.getDad().getDad().getRight(); //Pega o tio de z
                if(y.isCor()){  //Verifica se o tio é vermelho
                    z.getDad().setCor(false);   //Pai de z fica preto
                    y.setCor(false);    //O tio fica preto
                    z.getDad().getDad().setCor(true);   //O avo fica vermelho
                    z = z.getDad().getDad();    //Z recebe o avo
                }
                else{ 
                    if(z.equals(z.getDad().getRight())){   //Verifica se z é filho a direita
                        z = z.getDad(); //Z recebe o pai
                        leftRotate(z);  //Rotaciona z para esquerda
                    }
                    z.getDad().setCor(false);   //O pai de z vira preto
                    z.getDad().getDad().setCor(true);   //O avo de z vira vermelho
                    rightRotate(z.getDad().getDad());   //Rotaciona o avo de z para direita
                }
            }
            else if(z.getDad().equals(z.getDad().getDad().getRight())){ //Verifica se o pai de z é filho a direita
                y = z.getDad().getDad().getLeft();  //Pega o tio de z
                if(y.isCor()){  //Verifica se o tio é vermelho
                    z.getDad().setCor(false);   //Pai de z fica preto
                    y.setCor(false);    //O tio fica preto
                    z.getDad().getDad().setCor(true);   //O avo fica vermelho
                    z = z.getDad().getDad();    //Z recebe o avo
                }
                else {
                    if(z.equals(z.getDad().getLeft())){    //Verifica se z é filho a esquerda
                        z = z.getDad(); //Z recebe o pai
                        rightRotate(z); //Rotaciona z para direita
                    }
                    z.getDad().setCor(false);   //O pai de z vira preto
                    z.getDad().getDad().setCor(true);   //O avo de z vira vermelho
                    leftRotate(z.getDad().getDad());    //Rotaciona o avo de z para direita
                }
            }
        }
        getRoot().setCor(false);    //A raiz vira preta
    }

    public Word searchRN(String element){
        NoRN atual = getRoot(); //No que ira percorrer
        while (!atual.equals(getNil())) {   //Enquanto não for null
            if (!atual.getElement().getWord().equals(element)) {    //Verifica se é igual
                if(atual.getElement().getWord().compareTo(element) > 0){    //Verifica se é menor
                 atual = atual.getLeft();   //Vai para esquerda
                }
                else{
                    atual = atual.getRight();   //Vai para direita
                }
            } else {
                break;
            }
        }            
        if(atual.equals(getNil()))  //Verifica se não encontrou
            return null;
        else
            return atual.getElement();  //Retorna o elemento encontrado
    }
    
    /**
     * @return the root
     */
    public NoRN getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(NoRN root) {
        this.root = root;
    }

    /**
     * @return the nil
     */
    public NoRN getNil() {
        return nil;
    }

    /**
     * @param nil the nil to set
     */
    public void setNil(NoRN nil) {
        this.nil = nil;
    }
}
