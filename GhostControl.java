
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import sun.awt.RepaintArea;


    public class GhostControl extends Thread{

	private GhostModelo modelo;
	private GhostVista vista;
	
	public GhostControl(GhostModelo modelo,GhostVista vista)
	{   super();
		this.modelo = modelo;
		this.vista  = vista;
        modelo.addObserver(vista);
        start();
	}
	
	public void run()
	{
		while(true)
		{
			
			modelo.moverse();
			modelo.setEstado(1);
			try{
				sleep(modelo.getVelocidad());}
	 
			catch(InterruptedException e){}
		}	
	}	
	}
	
	
	

