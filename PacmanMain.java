import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PacmanMain extends JFrame{

	private JLabel puntaje1;
	private JLabel puntaje2;
	private JLabel numPlayer1;
	private JLabel numPlayer2;
	private JLabel numVida1;
	private JLabel numVida2;
	private JPanel panelDatos;
	
	private MapaModelo mapa;
	private MapaVista mapavista;
	private MapaControl mapacontrol;
	private Game nuevoGame;

	private Image principal;
public PacmanMain()
{
	super("Juego Pacman");
	
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(null);
	getContentPane().setBackground(Color.black);
	
	newGame();
    panelDatos = new JPanel();
    panelDatos.setSize(100,100);
    panelDatos.setLocation(500,50);
    panelDatos.setLayout(new BoxLayout(panelDatos,BoxLayout.Y_AXIS));
    numPlayer1 = new JLabel("Player Nro 1");
    //numPlayer2 = new JLabel("Player Nro 2");
    puntaje1 = new JLabel("Puntos 0");
    //puntaje2 = new JLabel("Puntos");
    numVida1 = new JLabel("Vidas # " +nuevoGame.numVidas(1));
    //numVida2 = new JLabel("Vidas #");
    panelDatos.setOpaque(true);
    panelDatos.add(numPlayer1);
	panelDatos.add(numVida1);
	panelDatos.add(puntaje1);
	//panelDatos.add(numPlayer2);
//	panelDatos.add(numVida2);
	//panelDatos.add(puntaje2);
	
	add(panelDatos);
	
	setSize(700, 600);
	setVisible(true);
	
}

public void newGame(){
   
    mapa = new MapaModelo();
    nuevoGame =new Game(this,mapa);
    nuevoGame.setNivelJuego(2);
    
    mapavista = new MapaVista(mapa,this,nuevoGame);
    mapacontrol = new MapaControl(mapa,mapavista);
    mapavista.setLocation(0,0);
    nuevoGame.setLocation(0,0);
    add(mapavista);
    add(nuevoGame);
}
public void setDatos(){
	puntaje1.setText("Puntos "+nuevoGame.puntaje());}

public void setVida(int nroPlayer)
{numVida1.setText("Vidas # "+nuevoGame.numVidas(1));	}

}