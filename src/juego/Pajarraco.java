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
		this.pajaro=Herramientas.cargarImagen("bird(1).png");	
	}
	
	// METODOS:
	
	/** Utiliza un for para disminuir la elevación del pájaro en el eje y, para 
	 * que de esta manera se tenga un mejor control en el manejo del pájaro. 
	 * 
	 */
	void volar(){
		this.y-=5;
		
	}
	
	
	/** Disminuye la posición del pájaro en pantalla constantemente. 
	 * 
	 */
	void caer() {
		this.y+=1.9;
	}
	
	
	/** Se encarga de dibujar en pantalla la imagen del pájaro. 
	 * 
	 */
	void imprimir(Entorno e) {
		e.dibujarImagen(pajaro, x, y, 0, 0.10);
	}
	
	
	/** Este método realiza la creación de un objeto tipo disparo. 
	 * 
	 */
	public Disparo disparar() {
		return new Disparo(this.x,this.y);	
	}
	
}
