package Plano_B;

import java.util.ArrayList;

public class Word implements Comparable<Word>{
    private String word;    //Palavra
    private ArrayList<WordInDoc> quantityByDocs;    //Indice Invertido da palavra
    
    public Word(){
        this.quantityByDocs = new ArrayList<>();
    }
    
    public Word(String word, ArrayList<WordInDoc> quantityByDocs){
        this.word = word;
        this.quantityByDocs = quantityByDocs;
    }
    
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<WordInDoc> getQuantityByDocs() {
        return quantityByDocs;
    }

    public void setQuantityByDocs(WordInDoc wd) {
        this.quantityByDocs.add(wd);
    }

    @Override
    //Função de Comparação
    public int compareTo(Word o) {
        return this.word.compareTo(o.word);
    }
}