import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class GhostVista implements Observer 
{
    private Game game;
	private GhostModelo modelo;
	private Image[] sprites; 
	private Image image;
	
	public GhostVista(GhostModelo modelo,Game game)
	{
		this.game = game;
		this.modelo = modelo;
	
		sprites = new Image[2];
		
		try{
		Toolkit tk = Toolkit.getDefaultToolkit();
		for(int i=0;i<2;i++)
		{
			ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/ImagenesGhost/fantasma"+i+".jpg"));
		     sprites[i] = imagenFondo.getImage();
		}
		
		}
		catch (Exception e) {
			System.out.print("No cargo imagenes fantasbnvnma");
		}
	}
	
	public void paint(Graphics g)
	{		
		  g.drawImage(sprites[modelo.getFrame()]
		              ,modelo.getY()*24,modelo.getX()*24,24,24,game);    
               
    }
	public void paint2D(Graphics g)
	{
		 g.drawImage(sprites[modelo.getFrame()]
				          ,modelo.getY()*24,modelo.getX()*24,24,24,game);    
	}

	public void update(Observable p, Object arg1) 
	{
		if(modelo.getEstado() == 1) //ghost moviendose
		{
			game.dibujar();
		}
		else
		{
			
		}
	}
}
