package juego;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

// VARIABLES DE INSTANCIA:

public class Alimento {
	 private double x;
	 private double y;
	 private String nombreAlimento;
	 private Image verdura;
	 
	 Alimento(double x,double y,String nombreAlimento){
		this.x=x;
		this.y=y;
		this.nombreAlimento=nombreAlimento;
		this.verdura=Herramientas.cargarImagen(this.nombreAlimento);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getNombreAlimento() {
		return nombreAlimento;
	}

	//MÉTODOS:

	/**Decrece en x para dar la sensación de movimiento.
	 * 
	 */
	void moverIzquierda() {
		this.x-=4;
	}
	
	
	/** Dibuja en Pantalla la imagen de la Verdura.
	 * 
	 */
	void imprimirse(Entorno e) {
		e.dibujarImagen(verdura, x, y, 0, 0.50);
	}
	
	
	/** Se elige aleatorio entre todos los alimentos
	 (Brocoli, Tomete, Calabaza y Hamburguesa).
	 *
	 */
	void cambiarAlimento() {
		Random r = new Random();
		
		String [] verduras= {"tomate.png","brocoli.png","calabaza.png","hamburguesa.png","hamburguesa.png"};
		int verduraAleatoria=r.nextInt(4);
		this.nombreAlimento=verduras[verduraAleatoria];
		this.verdura=Herramientas.cargarImagen(verduras[verduraAleatoria]);
	}
	
	
	/** Reemplaza la Hamburguesa por un Vegetal.
	 * Este metodo se utiliza cuando el disparo colisiona con una hamburguesa.
	 * 
	 */
	void newVegetables() {
		Random r = new Random();
		String [] verduras= {"tomate.png","brocoli.png","calabaza.png"};
		int verduraAleatoria=r.nextInt(3);
		this.nombreAlimento=verduras[verduraAleatoria];
		this.verdura=Herramientas.cargarImagen(verduras[verduraAleatoria]);
	}
	
	
	/** Le da a la verdura recién creada un posicion, teniendo
	 en cuenta los parametros del tuboSuperior @param Tubo.
	 * Nuestro ramdon solo toma valores de tipo int, pero los (x,y) del tubo son 
		de tipo double, para solucionar esto creamos varibles ("xtubo" e "ytubo") 
		que tomas los (x,y) del tubo pero transformados en int. 
		*
		*/
	void nuevaUbicacion(Tubo tubo) {
		int xtubo=(int)tubo.getX();
		int randomX=ThreadLocalRandom.current().nextInt(xtubo+70, xtubo+200);
		this.x=randomX;
		int ytubo=(int)tubo.getY();
		int randomY=ThreadLocalRandom.current().nextInt(ytubo+288, ytubo+328);
		this.y= randomY;
	}

	
	/** Si la X del alimento es igual a 0, la verdura vuelve a aparecer en el margen 
	 derecho de la pantalla. 
	 * @param tubo 
	 * Nuestro ramdon solo toma valores de tipo int, pero los (x,y) del tubo son 
		de tipo double, para solucionar esto creamos varibles ("xtubo" e "ytubo") 
		que tomas los (x,y) del tubo pero transformados en int. 
		*
		**/
	void aparecerDeNuevo(Tubo tubo) {
		if(this.x<=0) {
			int xtubo=(int)tubo.getX();
			int randomX=ThreadLocalRandom.current().nextInt(xtubo+70, xtubo+200);
			this.x=randomX;
			int ytubo=(int)tubo.getY();
			int randomY=ThreadLocalRandom.current().nextInt(ytubo+288, ytubo+328);
			this.y= randomY;
			
		}			
	}
	
}
