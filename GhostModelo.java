import java.util.ArrayList;
import java.util.Observable;

import org.omg.CORBA.portable.IndirectionException;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import sun.net.www.content.audio.basic;


public class GhostModelo extends Observable 
{
	
	private MapaModelo mapa;
	private Thread hilo;
	private PacmanModelo cazar; // pacman a cazar
	
	private int estadoPersonaje;
	// 0 quieto
	// 1 ghost moviendose y no puede morir 
	// 2 ghost moviendose lento y puede morir
	// 3 ghost muerto
	private boolean puedeMorir;
	private int frame;
		

	private int tipoGhost;
	private int velocidad;
	private int tiempoEspera;
	
	// posicion actual de fantasma
	private int x,dx;
	private int y,dy;
	
	private int inicialX;
	private int inicialY;
	
	/*direccion del fantasma*/
	private int direccion;
	
	private int down    = 0;
	private int up 		= 1;
	private int left    = 2;
	private int right   = 3;
	
	int caso;
	boolean izq,der,ari,aba;
	
	//posicion donde inician los fantasmas
	public GhostModelo(int tipoG,MapaModelo mapa)
	{
		
		this.mapa = mapa;
		this.setTipoGhost(tipoG);
		direccion = 2;
		velocidad = 500;
		frame = 0;
		switch (tipoG) { //iniciamos al fantasma segun su tipo 
		case 1:
			x = inicialX = 9;
			y = inicialY = 9;
			break;

		case 2:
			
			x = inicialX = 1;
			y = inicialY = 17;
		
			break;
		case 3:
			x = inicialX = 17;
			y = inicialY = 17;
			break;
		case 4:
			break;
		
		}
		
		reset(); //iniciar personaje en estado inicial
		
		
	}
	
	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velo)
	{this.velocidad = velo;}
	public void agregarPresa(PacmanModelo presa)
	{
		this.cazar = presa;
	}
	public void setEstado(int estado)
	{
		if(estado== 2 || estado == 3)
		{switch (estado)
		{
		case 2:
			puedeMorir = true;
			setVelocidad(1500);break;
		case 3:
			reset();
			break;
		}
		}
		estadoPersonaje = estado;
		setChanged();
		notifyObservers();}
	
	public void setPuedeMorir(boolean puedeMorir) {
		this.puedeMorir = puedeMorir;
	}

	public int getEstado(){return estadoPersonaje;}
	public boolean getPuedeMorir(){return puedeMorir;}
	public int getDireccion(){return direccion;}
	public void setdireccion(int d){direccion = d;}
	public  synchronized int getX(){return x;}
	public  synchronized int getY(){return y;}
	public  synchronized void setX(int x)
	{this.x = x;}
	public  synchronized void setY(int y)
	{this.y = y;}
	public MapaModelo getMapa(){return mapa;}
	public void moverse()
	{ 
		boolean[] opciones = determinarOpciones();
		double[] distacias = determinarDistancias();
		mejorOpcion(opciones,distacias);
		moverGhost();
		if(cazar.getX()==getX()&&cazar.getY()==getY())
		{
			if(getPuedeMorir())
			{			
			dead();}
			else{cazar.dead();}
		}
		
		
	}
	public boolean[] determinarOpciones()
	{
		boolean[]res = new boolean[4];
		if(mapa.movientoPosible(this.x+1,this.y)&& direccion != up)
		{res[0] = true;}
		else{res[0] = false;}
		if(mapa.movientoPosible(this.x-1,this.y)&& direccion !=down)
		{res[1] = true;}
		else{res[1] = false;}
		if(mapa.movientoPosible(this.x,this.y-1)&& direccion !=right)
		{res[2] = true;}
		else{res[2] = false;}
		if(mapa.movientoPosible(this.x,this.y+1)&& direccion !=left)
		{res[3] = true;}
		else{res[3] = false;}
		
		return res;	
	}
	public double[]determinarDistancias()
	{ 
		double res[] = new double[4];
		res[0] = calcularDistancia(cazar.getX(),cazar.getY(),this.x+1,this.y);
		res[1] = calcularDistancia(cazar.getX(),cazar.getY(),this.x-1,this.y);
		res[2] = calcularDistancia(cazar.getX(),cazar.getY(),this.x,this.y-1);
		res[3] = calcularDistancia(cazar.getX(),cazar.getY(),this.x,this.y+1);
		
		   return res;                       
		
	}
	
	public void mejorOpcion(boolean[]opciones,double[]distancias)
	{int indice;boolean flag=true;
    while(flag)
    {
	 indice = menor(distancias);
	if(opciones[indice] == true)
	{
		flag=false;
		setdireccion(indice);
	}
	else
	{
		distancias[indice] = 1000000000000000.0;
	}
    }	
	}
	public void moverGhost()
	{
		switch (this.direccion) {
		case 0: // down
			setX(x+1);
			setY(y);
			break;
		case 1: // up
			setX(x-1);
			setY(y);
			break;
		case 2: // leght
			setX(x);
			setY(y-1);
			break;
		case 3: // right
			setX(x);
			setY(y+1);
			break;
		default:
			break;
		}
	}
    private double calcularDistancia(int x2,int y2,int x1,int y1)
    {
    	return Math.sqrt((Math.pow(x2-x1, 2))+(Math.pow(y2-y1,2)));
    	
    }
    
     public int menor(double[] distancias)
     {int indice=0;double numero;int cont=0;
    	        numero =distancias[0];indice = 0;
    	    for(int i=0;i<distancias.length;i++)
    	    {   
    	    	if(distancias[i]<numero)
    	    		{
    	    		indice = i;
    	    		numero = distancias[i];
    	    		}
    	   	}
    	    return indice;
    }
    public void ini()
    {
    	hilo.start();
    }
    public void killPacman()
    {
     cazar.dead();
    //agregar velocidad o otro algoritmocada ves q mata un pacman 
    }
	public void dead()
	{
		reset();
	}
	
	private void reset() 
	{	
		puedeMorir = false;
		setEstado(1);
		setFrame(0);
		velocidad = 500;
		this.x = inicialX;
		this.y = inicialY;
	}

	public void setTipoGhost(int tipoGhost) {
		this.tipoGhost = tipoGhost;
	}

	public int getTipoGhost() {
		return tipoGhost;
	}
}
	
	
	
	
	
	

