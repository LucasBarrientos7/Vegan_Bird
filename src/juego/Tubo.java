package juego;

import java.util.concurrent.ThreadLocalRandom;
import java.awt.Image;

import entorno.Entorno;

// VARIABLES DE INSTANCIA:

public class Tubo {
	private double x;
	private double y;
	private Image tubo;
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	Tubo(double x,double y){
		this.x=x;
		this.y=y;
		this.tubo=entorno.Herramientas.cargarImagen("tubo.png");
	}
	
	// MÉTODOS:
	
	/** Este método se encarga de disminuir la x de la imagen del tubo, dando una sensación 
	 * de movimiento desde la derecha de la pantalla a la izquierda de la misma. 
	 * 
	 */
	void moverIzquierda() {
		this.x-=4;
	}
	
	
	/** Este método consiste en graficar los tubos en la parte superior de la pantalla. 
	 * 
	 */
	void imprimirSuperior(Entorno e) {
		e.dibujarImagen(this.tubo, this.x, this.y, entorno.Herramientas.radianes(180) , 0.4);
	}
	
	
	/** Este método consiste en graficar los tubos en la parte inferior de la pantalla 
	 * 
	 */
	void imprimirInferior(Entorno e) {
		e.dibujarImagen(this.tubo, this.x, this.y, 0 , 0.4);
	}
	
	
	/**
	si la coordenada x del tubo es igual a 0, este metodo lo hace reaparecer en el
	margen derecho de la pantalla y a la vez, cambia su altura (la coordenada y) de manera 
	aletoria
	* 
	*/
	void aparecerDeNuevoSuperior() {
		
		if(this.x<0) {
			this.x=900;
			int yAleatoria=ThreadLocalRandom.current().nextInt(-40, 90);
				this.y=(double)yAleatoria;
		}
	}
	
	
	/**
	si la coordenada x del tubo es igual a 0, este metodo lo hace reaparecer en el
	margen derecho de la pantalla, luego toma la cooredenada Y del tubo superior y la 
	 incrementa lo suficiente como para que no se superpongan los tubos y dejando una
	avertura por la cual pueda pasar el pajaro
	 * @param tuboSuperior
	 */
	void aparecerDeNuevoInferior(Tubo tuboSuperior) {
		
			if(this.x<0) {
				this.x=900;
				this.y=tuboSuperior.y+650;
			}
	}
	
	
	/** Decrementa las adyacentes del tubo superior hasta encontrarse con el margen 
	 del tubo inferior y se detiene para no colisionar con el mismo. 
	 *
	 **/
	void bajar(Tubo tuboInferior) {
		if(this.y+275<=tuboInferior.getY()-275)this.y+=0.5;
	}
	
}
