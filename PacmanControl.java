import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import sun.awt.RepaintArea;

public class PacmanControl extends Thread {

	private PacmanModelo modelo;
	private PacmanVista vista;
	
	public PacmanControl(PacmanModelo modelo,PacmanVista vista) 
	{
		this.modelo = modelo;
		this.vista  = vista;
		if(modelo.getPlayerNo() == 1)
		{
		vista.addTeclado(new TecladoListener());
		}
		else{vista.addTeclado(new TecladoListener2());
		}
		modelo.addObserver(vista);
		}
	//Evento de teclas para jugador uno 
	class TecladoListener extends KeyAdapter 
	{int teclaPresionada;
		public void keyPressed( KeyEvent evt)
		{
			int teclaPresionada=  evt.getKeyCode();
			switch (teclaPresionada)
						{
						case KeyEvent.VK_RIGHT:
							modelo.setdireccion(PacmanModelo.right); 
							actualizar(modelo.getX(),modelo.getY()+1,true);
							break;
						case KeyEvent.VK_LEFT:
							modelo.setdireccion(PacmanModelo.left);
							actualizar(modelo.getX(),modelo.getY()-1,true);
							break;
						case KeyEvent.VK_UP:
							modelo.setdireccion(PacmanModelo.up);
							actualizar(modelo.getX()-1,modelo.getY(),true);
							 break;
						case KeyEvent.VK_DOWN:
							modelo.setdireccion(PacmanModelo.down);
							actualizar(modelo.getX()+1,modelo.getY(),true);
							break;
						}
		}
			
		
		public void actualizar(int x,int y,boolean activo)
		{
			modelo.setEstado(1);
			if(activo){
			modelo.moverse(x, y);
			if(modelo.getFantasmas()[0].getX()==modelo.getX() && modelo.getFantasmas()[0].getY()==modelo.getY() && !modelo.getFantasmas()[0].getPuedeMorir())
			{modelo.dead();}
			}
			modelo.setEstado(0);
		    interrupt();
		}
			
		public void keyReleased(KeyEvent evt)
		{
			int teclaPresionada = evt.getKeyCode();
			switch (teclaPresionada)
			{
			case KeyEvent.VK_RIGHT:
				actualizar(modelo.getX(),modelo.getY(),false);
				break;
			case KeyEvent.VK_LEFT:
				actualizar(modelo.getX(),modelo.getY(),false);
				break;
			case KeyEvent.VK_UP:
				actualizar(modelo.getX(),modelo.getY(),false);
				 break;
			case KeyEvent.VK_DOWN:
				actualizar(modelo.getX(),modelo.getY(),false);
				break;
			}
		}
		
	
	}
	
	class TecladoListener2 extends KeyAdapter
	{
		
		public void keyPressed( KeyEvent evt)
		{
			int teclaPresionada = evt.getKeyCode();
					
					switch (teclaPresionada)
					{
					case KeyEvent.VK_D:
						modelo.setdireccion(PacmanModelo.right); 
						actualizar(modelo.getX(),modelo.getY()+1,true);
						break;
					case KeyEvent.VK_A:
						
						modelo.setdireccion(PacmanModelo.left);
						actualizar(modelo.getX(),modelo.getY()-1,true);
						break;
					case KeyEvent.VK_W:
						
						modelo.setdireccion(PacmanModelo.up);
						actualizar(modelo.getX()-1,modelo.getY(),true);
						
						 break;
					case KeyEvent.VK_S:
						
						modelo.setdireccion(PacmanModelo.down);
						actualizar(modelo.getX()+1,modelo.getY(),true);
						break;
					}
					
					
				
			

		}
		public void actualizar(int x,int y,boolean activo)
		{
			modelo.setEstado(1);
			if(activo){
			modelo.moverse(x, y);
			
			}
			modelo.setEstado(0);
		 }
			
			
			
		
		public void keyReleased(KeyEvent evt)
		{
			int teclaPresionada = evt.getKeyCode();
			switch (teclaPresionada)
			{
			case KeyEvent.VK_D:
				modelo.setEstado(0);
				actualizar(modelo.getX(),modelo.getY(),false);
				break;
			case KeyEvent.VK_A:
				modelo.setEstado(0);
				actualizar(modelo.getX(),modelo.getY(),false);
				break;
			case KeyEvent.VK_W:
				modelo.setEstado(0);
				actualizar(modelo.getX(),modelo.getY(),false);
				 break;
			case KeyEvent.VK_S:
				modelo.setEstado(0);
				actualizar(modelo.getX(),modelo.getY(),false);
				break;
			}
			
		}
			
		}
	
	}
	
	
	
	
	
	
	

