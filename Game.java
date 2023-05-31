import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


// clase que contiene todos los objetos del juego
public class Game extends JPanel{

private boolean gameOver;
private boolean win;

private Image imagenes[];
private int nivelJuego;


private PacmanMain main;
private MapaModelo mapa;

private PacmanVista pacmanvista;
private PacmanModelo pacmanmodelo;
private PacmanControl pacmancontrol;

private GhostModelo fan1;
private GhostVista  fanv1;
private GhostControl fanc1;
private GhostModelo fan2;
private GhostVista  fanv2;
private GhostControl fanc2;
private GhostModelo fan3;
private GhostVista  fanv3;
private GhostControl fanc3;

public Game(PacmanMain main,MapaModelo mapa){
    imagenes=new Image[2];
 try{
    Toolkit tk = Toolkit.getDefaultToolkit();
            
    ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/ImagenesGame/fin.jpg"));
     imagenes[0] = imagenFondo.getImage();
     imagenFondo = new ImageIcon(getClass().getResource("/ImagenesGame/win.jpg"));
     imagenes[1] = imagenFondo.getImage();
    }
    catch (Exception e) {
    System.out.print("No cargo imagenes del fin");
    }

this.main = main;
this.mapa = mapa;
setgameOver(false);
setWin(false);
setFocusable(true);    
setDoubleBuffered(true);

setOpaque(true);
setBounds(0, 0, 24*24, 24*24);
pacmanmodelo = new PacmanModelo(1, mapa);
pacmanvista = new PacmanVista(pacmanmodelo,this);
pacmancontrol = new PacmanControl(pacmanmodelo, pacmanvista);

fan1 = new GhostModelo(1, mapa);
fan2 = new GhostModelo(2, mapa);
fan3 = new GhostModelo(3, mapa);
fan1.agregarPresa(pacmanmodelo);
fan2.agregarPresa(pacmanmodelo);
fan3.agregarPresa(pacmanmodelo);
pacmanmodelo.agrearFantasma(fan1);
pacmanmodelo.agrearFantasma(fan2);
pacmanmodelo.agrearFantasma(fan3);

fanv1 = new GhostVista(fan1, this);
fanc1= new GhostControl(fan1, fanv1);
fanv2 = new GhostVista(fan2, this);
fanc2= new GhostControl(fan2, fanv2);
fanv3 = new GhostVista(fan3, this);
fanc3= new GhostControl(fan3, fanv3);
main.add(this);


}
public boolean getGameOver() {
    return gameOver;
}
public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
}
public int numVidas(int nroPlayer){
    return pacmanmodelo.getNumVidas();
}

public int puntaje(){
    return pacmanmodelo.getPuntaje();
}
public void paint(Graphics g) {
if(getgameOver())
{g.drawImage(imagenes[0],0,0,this);    
}
if(getWin())
{g.drawImage(imagenes[1],0,0,this);}
if(getgameOver() == false && getWin()==false)
{fanv1.paint(g);
fanv2.paint(g);
fanv3.paint(g);
pacmanvista.paint(g);
}
Toolkit.getDefaultToolkit().sync();
}

public void dibujar()
{
main.repaint();    
}
public void restarVida(int nroPlayer)
{
main.setVida(nroPlayer);    
}
public void setgameOver(boolean endGame) 
{this.gameOver = endGame;}

public boolean getgameOver(){return gameOver;}

public void setWin(boolean win){this.win=win;}

public boolean getWin(){return win;}

public void setNivelJuego(int nivelJuego)
{this.nivelJuego = nivelJuego;}

public int getNivelJuego(){return nivelJuego;}


}