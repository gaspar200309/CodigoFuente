
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileReader;

import java.util.Observable;
import java.util.Observer;


import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class MapaVista extends JPanel implements Observer
{    
	
	private PacmanMain main;
	private MapaModelo mapa;
	private Game game;
	public static int tamano= 29;
	
	public int height;
	public int width;
	
	
	private Image[]mapPunto;
	private Image[] mapImages;
	private FileReader leer;
	private String nombre="mapa.txt";
	public MapaVista(MapaModelo mapa,PacmanMain main,Game game)
	{
		this.main = main;
		this.mapa = mapa;
		this.game = game;
		main.add(this);
		
		
		setDoubleBuffered(true);
		setOpaque(true);
		
		setBounds(0, 0, 24*24, 24*24);
		
	
	    mapImages = new Image[6];
		  
		  try{
				Toolkit tk = Toolkit.getDefaultToolkit();
				for(int i=0;i<6;i++)
				{
					ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/ImaMapa/mapa"+i+".png"));
				     mapImages[i] = imagenFondo.getImage();
				}
				
				
				}
				catch (Exception e) {
					System.out.print("No cargo imagenes");
				}
	
				
	
				
				
	height = mapImages[1].getHeight(null);
	width = mapImages[1].getWidth(null);
	
	}
	public int height(){return height;}
	public int width(){return width;
	}
	
	
	
	
	
	
	
		
	public void paint(Graphics g)
	{
		if(mapa.getWin())
		{   g.setColor(Color.red);
		    game.setWin(true);}
		
		if(mapa.getGameOver() && mapa.getEstado()==3)
		{
			g.setColor(Color.blue);
			game.setgameOver(true);
			
		}
		if(mapa.getEstado() == 2 || mapa.getEstado()==1 )
		
		{
			for(int x=0;x<mapa.getFilas();x++)
			for(int y=0;y<mapa.getColumnas();y++)
			{
				
				g.drawImage(mapImages[mapa.getPosicion(x, y)],y*height,x*height,this);
			
			}
			
			
		}
		
	}
	
	public void  paint2D()
	{
		
	}

	public void update(Observable arg0, Object arg1)
	{
		
		repaint();
		//paintImmediately(, arg1, arg2, arg3)
		main.setDatos();
		}
		
		
		
	}


