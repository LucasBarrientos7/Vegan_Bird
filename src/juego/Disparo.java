package juego;

import java.awt.Color;

import entorno.Entorno;

//VARIABLES DE INSTANCIA:

public class Disparo {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	
	public double getX() {
		return x;
	}

	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public double getY() {
		return y;
	}
	Disparo(double x,double  y){
		this.x = x;
		this.y=y;
		this.alto=15;
		this.ancho=15;
	}
	
	//METODOS:
	
	/**Incrementa la posición de la x en donde está situada la imagen 
	 * del disparo para originar un efecto de movimiento desde el la posición 
	 * del pájaro hasta el límite derecho de la pantalla. 
	 * 
	 */
	void moverse()
	{
		this.x+=5;	
	}
	
	/** Se encarga de dibujar en pantalla el disparo. 
	 * 
	 */
	void imprimir(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.RED);
	}

}
