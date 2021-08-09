package juego;

import java.awt.Color;
import java.awt.Image;
import javax.sound.sampled.Clip;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego; 

// VARIABLES DE INSTANCIA:

public class Juego extends InterfaceJuego
{
	private Entorno entorno;
	int time;
	int puntaje;
	int distanciaParaTubosYaliemetos;
	boolean colicionTubo;
	Pajarraco pajarraco;
	Image fondo;
	Image piso;
	Image fondo2;
	Alimento[] alimento;
	Tubo[] tubosSuperiores;
	Tubo[] tubosInferiores;
	Clip musica;
	Clip MUSICA;
	Disparo disparo[];
	
	
	
	Juego() {
		// Inicializa el objeto entorno:
		//
		this.entorno = new Entorno(this, "Vegan Bird", 900, 600);
		
		// Inicializar lo que haga falta para el juego:
		//
        this.inicializarTodo();
        
		// Inicia el juego!
        //
		this.entorno.iniciar();
			
	}

	

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	// Procesamiento de un instante de tiempo:
	//
	public void tick() {   
		
		//si juegador coliciona con algo que no sea un alimento entra a este if:
		//
		if(colisionBordes()==true || colicionTubo==true) {
			
			// Detiene la musica:
			//
			this.MUSICA.stop();
			 
			// A partir de un determinado tiempo, se pasa al nivel 2 del juego y se  
			// en pantalla otro fondo:
			//
			if(this.time>1000)this.entorno.dibujarImagen(fondo2, 450, 300, 0);
			else this.entorno.dibujarImagen(fondo, 450, 300, 0);
			
			// Se escribe en pantalla "GAME OVER" si el usuario perdió:
			//
			this.entorno.cambiarFont("Showcard Gothic", 50, Color.RED);
			this.entorno.escribirTexto("GAME OVER", 300, 100);
			
			// Se muestra en pantalla el puntaje actual de la partida que se esta ejecutando:
			//
			String puntaje= String.valueOf(this.puntaje);
			this.entorno.escribirTexto("SCORE: "+puntaje,300 ,200);
			
			// Se escribe en pantalla la posibilidad de comenzar una nueva partida:
			//
			this.entorno.escribirTexto("CONTINUE ENTER",300 ,300);
			
			//Reinicia todas la varibles de instancia de el juego:
			//
			if(this.entorno.sePresiono(this.entorno.TECLA_ENTER)) {
				this.inicializarTodo();
			}
											
		}
		
		
		//Recorrido comun del juego:
		//
		else {
			
			// Inicia el tiempo:
			//
			this.time++;
			
			// Se dibuja el Fondo del juego:
			//
			this.entorno.dibujarImagen(fondo, 450, 300, 0);
			if(this.time>1000) {
				this.entorno.dibujarImagen(fondo2, 450, 300, 0);
			}
			
			// Inicializa la Musica:
			//
			this.MUSICA.loop(1);
			
			// Dibuja en pantalla al Pajarraco y :
			//
			this.pajarraco.imprimir(this.entorno);
			if(this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA))this.pajarraco.volar();
			else this.pajarraco.caer();
			
			// Tubo y alimento:
			//
			for(int i = 0; i < this.tubosSuperiores.length; i++)
			{	
			
				// Imprimir tubos
				//
				this.tubosSuperiores[i].imprimirSuperior(this.entorno);
				this.tubosInferiores[i].imprimirInferior(this.entorno);
				
				// Mover tubos superiores
				//
				if(this.time>900 && this.time<1000) {
					this.entorno.cambiarFont("Showcard Gothic", 100, Color.RED);
					this.entorno.escribirTexto("LEVEL 2", 300, 200);
				}
				
				if(this.time>1000 && this.tubosSuperiores[i].getX()<400) {
					this.tubosSuperiores[i].bajar(this.tubosInferiores[i]);
				}
				
				this.tubosSuperiores[i].moverIzquierda();
				this.tubosSuperiores[i].aparecerDeNuevoSuperior();
		
				// Mover tubos inferiores
				//
				this.tubosInferiores[i].moverIzquierda();
				this.tubosInferiores[i].aparecerDeNuevoInferior(this.tubosSuperiores[i]);
				
				// Verificamos si el pajaro coliciona con un tubo
				//
				if(colisionConTubo(this.tubosSuperiores[i]) || colisionConTubo(this.tubosInferiores[i]))
				{	
					this.colicionTubo=true;
				}
				
				// Alimento
				//
				if (this.alimento[i]!=null) {
					this.alimento[i].imprimirse(this.entorno);
					this.alimento[i].moverIzquierda();
					this.alimento[i].aparecerDeNuevo(tubosSuperiores[i]);
				
					// Puntos
					//
					puntos(this.alimento[i]);
				
					// Pregunta si el pajaro colisiona con las verduras:  
					//
					if(colisionVerdura(this.alimento[i])==true ) {
					//si el pajaro come una la verdura sale de la pantalla
					this.alimento[i]=null;
					
				
					}
					// Colisión disparo-hamburguesa
					//
					for(int j=0;j<this.disparo.length;j++) {
						if(this.disparo[j]!=null && this.alimento[i]!=null) {
							if((colisionDisparoHamburguesa(this.alimento[i],this.disparo[j])) && this.alimento[i].getNombreAlimento().equals("hamburguesa.png")) {
								
								this.disparo[j]=null;
								this.alimento[i].newVegetables();
								this.puntaje+=3;
								
							}
							
						}
					}
				}
			}
			
			// Verificamos que si alguna verdura esta en null:
			//
			for(int i=0;i<this.alimento.length;i++) {
				if(this.alimento[i]==null && this.tubosSuperiores[i].getX()>=850) {
					this.alimento[i]=new Alimento(this.tubosSuperiores[i].getX()+100,300,"brocoli.png");
					this.alimento[i].nuevaUbicacion(this.tubosSuperiores[i]);
					this.alimento[i].cambiarAlimento();
				}
			}	
			// Piso
			//
			this.entorno.dibujarImagen(piso, 450, 600, 0);
			
			// Muestra el puntaje en pantalla:
			//
			this.entorno.cambiarFont("Showcard Gothic",30, Color.RED);
			String puntaje= String.valueOf(this.puntaje);
			this.entorno.escribirTexto("Score:"+puntaje,700 , 50);
			
			// Muestra la cantidad tiros:
			//
			String contDisparo= String.valueOf(3-this.cantDisparo());
			this.entorno.escribirTexto("Shots:"+contDisparo,10 , 50);
			
			// Disparo:
			//
			if(this.entorno.sePresiono(this.entorno.TECLA_ESPACIO )&& this.cantDisparo()<=2) {
					this.disparo[this.cantDisparo()]=this.pajarraco.disparar();
					System.out.println("incremento"+ this.cantDisparo());
			}
			
			// Si el disparo llega al limite de la pantalla, el disparo desaparece
			//
			for(int i = 0;i < this.disparo.length;i++){
				if(this.disparo[i]!=null && this.disparo[i].getX()>=900 ) {
					this.disparo[i]=null;	
				}
				
				// Si se ejecuta el disparo, este se mueve hasta el limite de la pantalla
				//
				if(disparo[i]!=null) {
					this.disparo[i].imprimir(this.entorno);
					this.disparo[i].moverse();
				
				}	
			}
		
		}	
	}	
		

	// Nos dice si el pajaro y un alimento colicionan:
	//
		boolean colisionVerdura(Alimento verdura) {
					//me dice si el pajaro come una verdura
			return  this.pajarraco.getX()+20>=verdura.getX()-20 &&
					this.pajarraco.getX()-20<=verdura.getX()+20 &&
					this.pajarraco.getY()+20>=verdura.getY()-20&&
					this.pajarraco.getY()-20<=verdura.getY()+20;
					
		}
		
	// Nos dice si el pajaro esta tocan los de bordes de ventana:
	//
	boolean colisionBordes() {
		return  this.pajarraco.getY()-14<=0||
				this.pajarraco.getY()+14>=575;
	}
	
	// Nos dice si el pajaro coliciona con un tubo
	//
	boolean colisionConTubo(Tubo tubo) {
		if(				this.pajarraco.getX()-14>=tubo.getX()-60&&
						this.pajarraco.getX()+14<=tubo.getX()+60 &&
						this.pajarraco.getY()+14<=tubo.getY()+288 &&
						this.pajarraco.getY()-14>=tubo.getY()-288)return true;
						
		else return false;
		
	}
	// Colision disparo-verdura
	//
	boolean colisionDisparoHamburguesa(Alimento verdura,Disparo disparo) {
		if(		disparo.getX()+disparo.getAncho()/2 >= verdura.getX()-20 && 
				disparo.getX()-disparo.getAncho()/2 <= verdura.getX()+20 &&
				disparo.getY()+disparo.getAlto()/2>=verdura.getY()-20 &&
				disparo.getY()-disparo.getAlto()/2<=verdura.getY()+20)return true;
		
		else return false;
		
	}
	
	// Cuenta los puntos
	void puntos(Alimento verdura) {
		if(colisionVerdura(verdura)) {
			if(		verdura.getNombreAlimento().equals("tomate.png")||
					verdura.getNombreAlimento().equals("brocoli.png")||
					verdura.getNombreAlimento().equals("calabaza.png")) {
				this.puntaje = this.puntaje + 5;
			
			}
			if(verdura.getNombreAlimento().equals("hamburguesa.png"))this.puntaje-=5;	
		}
	}
	
	
	void inicializarTodo() {
	this.MUSICA=Herramientas.cargarSonido("musicaWav.wav");
		// Tiempo:
		//
		this.time=0;
		
		// Puntaje:
		//
		this.puntaje=0;
		
		// Fondo:
		//
		this.fondo=Herramientas.cargarImagen("background.png");
		this.fondo2=Herramientas.cargarImagen("background2.png");
		this.piso=Herramientas.cargarImagen("piso.png");
		
		//pajaro:
		//
        this.pajarraco=new Pajarraco(200,300);
        
        //inicializacion de alimento:
        //
        this.alimento=new Alimento[3];

//        
        
        
        
        
        
        
        
        
        
        
        
       
        this.disparo= new Disparo[3];
        
        // Tubo:
        //
        this.colicionTubo=false;
        this.distanciaParaTubosYaliemetos=800;
        this.tubosSuperiores= new Tubo[3];
        this.tubosInferiores= new Tubo[3];
        
        //inicializacion de tubos-elimentos
        //
        for(int i=0;i<this.tubosSuperiores.length;i++) {
        	
        	//tubos superior
        	this.tubosSuperiores[i]=new Tubo(this.distanciaParaTubosYaliemetos,-100);
        	
        	//tubo Inferior
        	this.tubosInferiores[i]=new Tubo(this.distanciaParaTubosYaliemetos,580);
        	
        	//alimento
        	this.alimento[i]= new Alimento(this.distanciaParaTubosYaliemetos,270,"brocoli.png");
        	
        	this.distanciaParaTubosYaliemetos+=300;
        }
           
	}
	
	
	// Cuenta la cantidad de disparos utilizados:
	//
	int cantDisparo() {
		int cant=0;
		for(int i=0;i<this.disparo.length;i++) {
			if(this.disparo[i]!=null) {
				cant++;
			}
		}
		return cant;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
