
public class MapaControl 
{
	private MapaVista vista;
	private MapaModelo modelo;
	
	public MapaControl(MapaModelo modelo,MapaVista vista)
	{
		this.modelo = modelo;
		this.vista = vista;
		modelo.addObserver(vista);
	}

}
