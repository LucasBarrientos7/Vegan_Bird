package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

// VARIABLES DE INSTANCIA:

public class Pajarraco {
	 private double x;
	 private double y;
	 private Image pajaro;
	
	
	public double getX() { 
		return x;
	}

	public double getY() {
		return y;
	}

	Pajarraco(int  x, int y){
		this.x=x;
		this.y=y;
		this.pajaro=Herramientas.cargarImagen("bird.png");	
	}
	
	// METODOS:
	
	/** Utiliza un for para disminuir la elevaci�n del p�jaro en el eje y, para 
	 * que de esta manera se tenga un mejor control en el manejo del p�jaro. 
	 * 
	 */
	void volar(){
		this.y-=5;
		
	}
	
	
	/** Disminuye la posici�n del p�jaro en pantalla constantemente. 
	 * 
	 */
	void caer() {
		this.y+=1.9;
	}
	
	
	/** Se encarga de dibujar en pantalla la imagen del p�jaro. 
	 * 
	 */
	void imprimir(Entorno e) {
		e.dibujarImagen(pajaro, x, y, 0, 0.20);
	}
	
	
	/** Este m�todo realiza la creaci�n de un objeto tipo disparo. 
	 * 
	 */
	public Disparo disparar() {
		return new Disparo(this.x,this.y);	
	}
	
}
