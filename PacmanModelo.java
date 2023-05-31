import java.awt.Frame;
import java.sql.Time;
import java.util.Observable;

import sun.awt.windows.ThemeReader;


public class PacmanModelo extends Observable implements Runnable
{

	private MapaModelo mapa;
	private Thread hilo;
	
	//enemigos del personaje 
	private GhostModelo[] fantasmas;
	
	private int estadoPersonaje;
	// 0 sin moverse
	// 1 moviendose
	// 2 personaje muerto reset
	
	
	//posicion del pacman en X y Y
	private int playerNo;
    private int x;
	private int y;
	private int inicialX;
	private int inicialY;
	
	
	//direcciones equivalentes en numeros 
	private int direccion;
	public static int down   = 0;
	public static int up = 1;
	public static int left = 2;
	public static int right= 3;
	
	// puntajes del pacman y numr de vidas 
	private int puntaje;
	private int puntajeTotal;
	private int numVidas;
	private boolean finVidas;
	
	public PacmanModelo(int numPlayer,MapaModelo mapa)
	
	{
		this.mapa = mapa;
		finVidas=false;
		numVidas = 3;
		puntaje =0;
		puntajeTotal = 0;
		estadoPersonaje = 0;
		playerNo = numPlayer;
		direccion = 0;
		
	    if(numPlayer == 1)// posicion inicial en el mapa pacman1
		{x = inicialX = 1;
		 y = inicialY = 1;
		}else if(numPlayer == 2) //posicion inicial Pacman2
		{
		 x = inicialX = 1;
		 y = inicialY = 8;
		}
	    fantasmas = new GhostModelo[4];
	    hilo = new Thread(this);
		reset();
	}
	public void run()
	{
		
	}		
	public void ini()
	{
		hilo.start();
	}
	public int getEstado(){return estadoPersonaje;}
	
	public void setEstado(int estado)
	{	estadoPersonaje = estado;
		setChanged();
		notifyObservers();}
	public void dead()
	{
		restarVida();
		setEstado(3);
		reset();
	}
	public void restarVida() 
	{
		if(getNumVidas() == 0)
		{setfinVidas(true);
		mapa.setEstado(3);
		mapa.setGameOver(true);
		}
		else
		{
		this.numVidas--;
		}
	}
	private void reset() 
	{
		this.x = inicialX;
		this.y = inicialY;
	}
	
	public int getDireccion(){return direccion;}
	
	public  synchronized int getX(){return x;}
	public  synchronized int getY(){return y;}
	
	public  synchronized void setX(int x){this.x = x;}
	public  synchronized void setY(int y){this.y = y;}
	
	public void setdireccion(int d){direccion = d;}
	
	public void moverse(int dx,int dy)
	{
		if(mapa.movientoPosible(dx, dy))
		{
		    setPuntaje(dx, dy);
		    mapa.removeBonus(dx, dy);
		    if(!getfinVidas())
		    {mapa.setEstado(1);};
			setX(dx);
			setY(dy);
			for(int i=0;i<=2;i++)
			{if(fantasmas[i].getX() == getX() && fantasmas[i].getY() == getY())
			{if(fantasmas[i].getPuedeMorir())
				{deadGhost(i);}
			else{dead();}
			}
		    } 
			
		}
	}
	
	
	
	public void setPuntaje(int x,int y)
	{
		switch (mapa.getPosicion(x, y)) {
		case 2:
			puntaje = puntaje + 10;   //valor de los puntos normales
			break;
		case 3:
			conPoder();
			puntaje = puntaje + 20;	  //valor de punto magico
		    break;	
		case 6:
			puntaje = puntaje + 100;  //valor de fruta
			break;}
	}
	public int getPuntaje(){return puntaje;}
	public int getPuntajeTotal(){return puntajeTotal;}
	public void setPuntajeTotal()
	{puntajeTotal = puntajeTotal+ puntaje;}
	public int getPlayerNo(){return playerNo;}
	public int getNumVidas() {return numVidas;}
	
	public void conPoder()
	{
		Runnable runable = new Runnable() {
			
			
			public void run() {
				
				for(int i=0;i<=2;i++){
				fantasmas[i].setFrame(1);
				fantasmas[i].setEstado(2);
				}
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					
					
				}
				for(int i=0;i<=2;i++){
					fantasmas[i].setVelocidad(500);
					fantasmas[i].setPuedeMorir(false);
					fantasmas[i].setFrame(0);
					}
				
			}
		}; 
		Thread retraso = new Thread(runable);
		retraso.start();
	}
	
	public void agrearFantasma(GhostModelo ghost)
	{   
		if(ghost.getTipoGhost()==1)
			{fantasmas[0] = ghost;}
		if(ghost.getTipoGhost()==2)
		{fantasmas[1] = ghost;}
		if(ghost.getTipoGhost()==3)
		{fantasmas[2] = ghost;}
	}
	public GhostModelo[] getFantasmas() {
		return fantasmas;
	}
	
	public void deadGhost(int i)
	{fantasmas[i].dead();}
	public void setfinVidas(boolean finVidas) {
		this.finVidas = finVidas;
	}
	public boolean getfinVidas() {
		return finVidas;
	}
}
