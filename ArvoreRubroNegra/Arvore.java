package ArvoreRubroNegra;

public class Arvore {
    Nodo raiz;
	static Nodo nil = new Nodo();
	
	public Arvore(){
		raiz = Arvore.nil;
	}

	public Arvore(int k){
		raiz = new Nodo(k);
	}
	
	public void add(int k){
		if(raiz == Arvore.nil) raiz = new Nodo(k);
		else{
			Nodo aux = raiz.busca(k);
			if(k > aux.info){
				aux.dir = new Nodo(k, aux);
				addFix(aux.dir);
			} else if(k < aux.info){
				aux.esq = new Nodo(k, aux);
				addFix(aux.esq);
			}
		}
	}
	
	public void left_rotate(Nodo x){
		Nodo y = x.dir;
		x.dir = y.esq;
		if(y.esq != Arvore.nil) y.esq.pai = x;
		y.pai = x.pai;
		if(x.pai == Arvore.nil) raiz = y;
		else if(x == x.pai.esq) x.pai.esq = y;
		else x.pai.dir = y;
		y.esq = x;
		x.pai = y;	
	}
	
	public void right_rotate(Nodo x){
		Nodo y = x.esq;
		x.esq = y.dir;
		if(y.dir != Arvore.nil) y.dir.pai = x;
		y.pai = x.pai;
		if(x.pai == Arvore.nil) raiz = y;
		else if(x == x.pai.esq) x.pai.esq = y;
		else x.pai.dir = y;
		y.dir = x;
		x.pai = y;	
	}

	public void addFix(Nodo z){
		Nodo y;
		while(z.pai.cor){
			if(z.pai == z.pai.pai.esq){
				y = z.pai.pai.dir;
				if(y.cor){
					z.pai.cor = false;
					y.cor = false;
					z.pai.pai.cor = true;
					z = z.pai.pai;
				} else {
					if(z == z.pai.dir){
						z = z.pai;
						left_rotate(z);
					}
					z.pai.cor = false;
					z.pai.pai.cor = true;
					right_rotate(z.pai.pai);
				}
			} else {
				y = z.pai.pai.esq;
				if(y.cor){
					z.pai.cor = false;
					y.cor = false;
					z.pai.pai.cor = true;
					z = z.pai.pai;
				} else {
					if(z == z.pai.esq){
						z = z.pai;
						right_rotate(z);
					}
					z.pai.cor = false;
					z.pai.pai.cor = true;
					left_rotate(z.pai.pai);
				}
			}			
		}
		raiz.cor = false;		
	}
	
	public void transplant(Nodo u, Nodo v){
		if(u.pai == Arvore.nil) raiz = v;
		else if(u == u.pai.esq) u.pai.esq = v;
		else u.pai.dir = v;
		v.pai = u.pai;
	}
	
	public void delete(Nodo z){
		Nodo y = z, x;
		boolean yCorOriginal = y.cor;
		if(z.esq == Arvore.nil){
			x = z.dir;
			transplant(z, z.dir);
		} else if(z.dir == Arvore.nil){
			x = z.esq;
			transplant(z, z.esq);
		} else {
			y = z.dir.minimo();
			yCorOriginal = y.cor;
			x = y.dir;
			if(y.pai == z) x.pai = y;
			else {
				transplant(y, y.dir);
				y.dir = z.dir;
				y.dir.pai = y;
			}
			transplant(z, y);
			y.esq = z.esq;
			y.esq.pai = y;
			y.cor = z.cor;
		}
		if(yCorOriginal == false) delFix(x);
	}
	
	public void delFix(Nodo x){
		Nodo w;
		while(x != raiz && x.cor == false){
			if(x == x.pai.esq){
				w = x.pai.dir;
				if(w.cor){
					w.cor = false;
					x.pai.cor = true;
					left_rotate(x.pai);
					w = x.pai.dir;
				}
				if(w.esq.cor == false && w.dir.cor == false){
					w.cor = true;
					x = x.pai;
				} else {
					if(w.dir.cor == false){
						w.esq.cor = false;
						w.cor = true;
						right_rotate(w);
						w = x.pai.dir;
					}
					w.cor = x.pai.cor;
					x.pai.cor = false;
					w.dir.cor = false;
					left_rotate(x.pai);
					x = raiz;
				}
			} else {
				w = x.pai.esq;
				if(w.cor){
					w.cor = false;
					x.pai.cor = true;
					right_rotate(x.pai);
					w = x.pai.esq;
				}
				if(w.dir.cor == false && w.esq.cor == false){
					w.cor = true;
					x = x.pai;
				} else {
					if(w.esq.cor == false){
						w.dir.cor = false;
						w.cor = true;
						left_rotate(w);
						w = x.pai.esq;
					}
					w.cor = x.pai.cor;
					x.pai.cor = false;
					w.esq.cor = false;
					right_rotate(x.pai);
					x = raiz;
				}
			}
		}
		x.cor = false;
	}
	
	public Nodo encontra(int k){
		Nodo a = raiz.busca(k);
		return a;
	}
	
	public void graph() {
		System.out.println("digraph RBTree {");
		raiz.graph();
		System.out.println("\tnil [style = filled, fillcolor = black, fontcolor = white];");
		System.out.println("}");
	}
}
