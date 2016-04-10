package ArvoreRubroNegra;

public class Nodo {
    int info;
	Nodo pai;
	Nodo esq, dir;
	boolean cor; //true = vermelho, false = preto
	
	public Nodo(){
		pai = dir = esq = null;
		cor = false;
	}
	
	public Nodo(int k){
		info = k;
		pai = dir = esq = Arvore.nil;
		cor = false;
	}

	public Nodo(int k, Nodo p){
		this(k);
		pai = p;
		cor = true;
	}
	
	public Nodo minimo(){
		if(esq != Arvore.nil) return esq.minimo();
		return this;
	}
	
	public Nodo busca(int k){
		if(k > info && dir != Arvore.nil) return dir.busca(k);
		else if(k < info && esq != Arvore.nil) return esq.busca(k);
		else return this;
	}
	
	public void print() {
		System.out.println(this + ": " + this.esq + " <- (" + this.info + " + " + (this.cor ? "V" : "P") + ") -> " + this.dir);
		if (this.esq != Arvore.nil) this.esq.print();
		if (this.dir != Arvore.nil) this.dir.print();
	}

	public void graph() {
		if (this.cor) {
			System.out.println("\t" + this.info + " [style = filled, fillcolor = red];");
		} else {
			System.out.println("\t" + this.info + " [style = filled, fillcolor = black, fontcolor = white];");
		}
		if (this.esq != Arvore.nil) {
			System.out.println("\t" + this.info + " -> " + this.esq.info + " [label = \" left\"];");
			this.esq.graph();
		} else {
			System.out.println("\t" + this.info + " -> nil [label = \" left\"];");
		}
		if (this.dir != Arvore.nil) {
			System.out.println("\t" + this.info + " -> " + this.dir.info + " [label = \" right\"];");
			this.dir.graph();
		} else {
			System.out.println("\t" + this.info + " -> nil [label = \" right\"];");
		}
	}
}
