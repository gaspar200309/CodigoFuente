

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileReader;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class MapaModelo extends Observable
{    
	
	private int estado;
	// 1 mapa nuevo
	// 2 mapa actual
	// 3 map  fin
	private int nivel; //existe 3 niveles del juego 	
		//1 , 2 , 3
	
	public final static int vacio 			= 0;
	public final static int bloque 			= 1;
	public final static int puntoNormal 	= 2;
	public final static int puntoMagico 	= 3;
	public final static int fruta 			= 4;
	
	private boolean gameOver ;
	private	boolean win ;
	private int [][] mapa ;
	
	public MapaModelo()
	{
		nivel=0;
		setGameOver(false);
		setWin(false);
		subirNivel();
		setEstado(1);	
		
		
		
	}
	public void cargarMapa(int numMapa){
		/*
		 * 0 vacio
		 * 1 bloque
		 * 2 comida normal
		 * 3 magico (pacman stealth mode)
		 * 4 fruta
		 * 5 bloque donde nacen los fantasmas
		 */
		if(numMapa== 3)
		{	mapa=new int[][]
				          { {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},  
							{1,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,2,3,1},  
							{1,2,1,1,2,1,1,1,2,1,2,1,1,1,2,1,1,2,1},   
							{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},   
							{1,2,1,1,2,1,2,1,1,1,1,1,2,1,2,1,1,2,1},   
							{1,2,2,2,2,1,2,2,2,1,2,2,2,1,2,2,2,2,1},  
							{1,2,1,1,2,1,1,1,2,1,2,1,1,1,2,1,1,2,1},  
							{1,2,1,1,2,1,2,2,2,2,2,2,2,1,2,1,1,2,1},   
							{1,2,1,1,2,1,2,1,1,2,1,1,2,1,2,1,1,2,1},   
							{1,2,2,2,2,2,2,2,2,5,2,2,2,2,2,2,2,2,1},   
							{1,2,1,1,2,1,2,1,1,2,1,1,2,1,2,1,1,3,1},   
							{1,2,1,1,2,1,2,2,2,2,2,2,2,1,2,1,1,2,1},   
							{1,2,1,1,2,1,1,1,2,1,2,1,1,1,2,1,1,2,1},   
							{1,2,1,1,2,1,2,2,2,1,2,2,2,1,2,1,1,2,1},  
							{1,2,1,1,2,1,2,1,1,1,1,1,2,1,2,1,1,2,1},  
							{1,2,1,1,2,1,2,1,1,1,1,1,2,1,2,1,1,2,1},  
							{1,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},   
							{1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},   
							{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},   
							{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1} };
		}
		if(numMapa ==1){
			
			mapa=new int[][]
					          { {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},  
								{1,2,2,2,2,2,2,2,2,5,2,2,2,2,2,2,2,3,1},  
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},   
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},  
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},  
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,2,2,2,2,2,2,2,2,5,2,2,2,2,2,2,2,2,1},   
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},  
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},  
								{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},  
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,3,2,2,2,2,2,2,2,5,2,2,2,2,2,2,2,3,1},   
								{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1} };
			
			
		}
		if(numMapa ==2){
			mapa=new int[][]
					          { {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},  
								{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},  
								{1,2,1,2,1,2,1,1,1,1,1,1,1,1,1,2,1,2,1},   
								{1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1},   
								{1,2,1,2,1,2,1,1,1,2,1,1,1,3,1,2,1,2,1},   
								{1,2,1,2,1,2,2,2,2,2,2,2,2,2,1,2,1,2,1},  
								{1,2,1,2,1,2,1,1,1,2,1,1,1,2,1,2,1,2,1},  
								{1,2,1,2,1,2,1,2,2,2,2,2,1,2,1,2,1,2,1},   
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,2,1,2,1,2,2,2,2,5,2,2,2,2,1,2,1,2,1},   
								{1,2,2,2,1,2,1,2,1,2,1,2,1,2,1,2,2,2,1},   
								{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1},   
								{1,2,1,2,1,2,1,2,2,2,2,2,1,2,1,2,1,2,1},   
								{1,2,1,2,1,2,1,1,1,2,1,1,1,2,1,2,2,2,1},  
								{1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1},  
								{1,2,1,2,1,1,1,1,2,1,1,1,1,1,1,2,1,2,1},  
								{1,2,2,2,2,2,2,2,3,2,2,2,2,2,2,2,2,3,1},   
								{1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},   
								{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},   
								{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1} };
			
			
			
		}
	}
	public int getFilas(){
		return mapa.length;
	}
	public int getColumnas()
	{
		return mapa[0].length;
	}
	public void CreatePunto(int x,int y)
	{
		if(mapa[x][y] != bloque)
		{
			mapa[x][y] = fruta;
		}
	}
	public void removeBonus(int x,int y)
	{
		if(mapa[x][y] == puntoNormal || 
		   mapa[x][y] == puntoMagico || mapa[x][y] == fruta)
			
		{mapa[x][y] = 0;
		}
		if(!hayPuntos())
		{subirNivel();}
	}
	public int getPosicion(int x,int y ){return mapa[x][y];	}
	public void setPosicion(int x,int y){
		mapa[x][y] = vacio;}
	public boolean movientoPosible(int x,int y){
	boolean res;
		if(mapa[x][y] == bloque)
			{res = false;}
		else
			{res = true;}
		return res;
	}
	public void setEstado(int estado)
	{
		
		this.estado = estado;
		
		
		setChanged();
		notifyObservers();
	}
	public int getEstado(){return estado;}
	public void setGameOver(boolean g)
	{
		gameOver = g;
	}
	public boolean getGameOver(){return gameOver;
	} 

	
public void subirNivel() {
	nivel++;
	if(getNivel()== 1)
	{cargarMapa(1);}
	if(getNivel()== 2)
	{cargarMapa(2);}
	if(getNivel()== 3)
	{cargarMapa(3);}
	if(getNivel()>3)
	{
	setEstado(3);
	 setWin(true);};
     }
public int getNivel() {
	return nivel;}
public boolean hayPuntos()
{boolean res=false;
	for(int x=0;x<getFilas();x++)
	for(int y=0;y<getColumnas();y++)
	{
	    if(getPosicion(x, y)==puntoMagico||getPosicion(x, y)==puntoNormal||getPosicion(x, y)==fruta)
	    {res = true;}
	}

	return res;
	}
public void setWin(boolean win) {
	this.win = win;
}
public boolean getWin() {
	return win;
}
}
