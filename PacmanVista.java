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

public class PacmanVista implements Observer
{
    private Game game;
		
	private PacmanModelo modelo;
	private Image[] sprites; 
	private Image image;
	
	public PacmanVista(PacmanModelo modelo,Game game)
	{
		
		this.game = game;
		this.modelo = modelo;
		
		sprites = new Image[4];
		String path= "./Imagenes/pacman";
		try{
		Toolkit tk = Toolkit.getDefaultToolkit();
		for(int i=0;i<=3;i++)
		{
			ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/ImagenesPlayers/pacman"+i+".gif"));
		     sprites[i] = imagenFondo.getImage();
		}
		
		}
		catch (Exception e) {
			System.out.print("No cargo imagenes");
		}

	}
	
	public void addTeclado(KeyAdapter keys)
	{
	game.addKeyListener(keys);	
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(sprites[modelo.getDireccion()]
             ,modelo.getY()*24,modelo.getX()*24,24,24,game);    
    }
	public void paint2D(){}

	
	public void update(Observable m, Object arg1) 
	{
		switch(modelo.getEstado())
		{
		case 0: // Pacman sin moverse:
			break;
			
		case 1: // Pacman moviendose
			game.dibujar();
		    break;
		case 2: 
			game.dibujar();
			break;
		case 3: // Pacman muerto
			game.restarVida(1);
			break;
		}
		
	}
}
