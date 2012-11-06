/// <summary>
/// Clase diseñada para trabajar como un vector espacial, contando con una serie de funciones
/// para realizar operaciones entre ellos.
/// </summary>

package es.ablamed.ea.mde.es;

public class Vector {

	double x;					//Valor del vector en el eje X.
	double y;					//valor del vector en el eje Y.
	
	private static final double rad= Math.PI/180;	//Los radianes que corresponden a un grado.
	
	/// <summary>
	/// Constructor de la clase Vector en la que se define mediante un modulo y un angulo.
	/// </summary>
	/// <param name="modulo">Valor del modulo del vector</param>
	/// <param name="angulo">Valor del angulo del vector en grados</param>
	
	public Vector(double modulo, int angulo)
	{

	    x = modulo * Math.cos(angulo * rad);
	    y = modulo * Math.sin(angulo * rad);
	    
	}
	
	public Vector (int x, int y)
	{
		this.x=x;
		this.y=y;		
	}

	/// <summary>
	/// Cálculo del modulo del vector resta de p1 y p2
	/// </summary>
	/// <param name="p1">Vector 1</param>
	/// <param name="p2">Vector 2</param>
	/// <returns></returns>
	public static double ModuloResta(Vector p1, Vector p2)
	{
	    double x=p2.x-p1.x;
	    double y=p2.y-p1.y;
	    return (Math.sqrt((x*x)+(y*y)));
	}

	/// <summary>
	/// Calculo del angulo del vector resta de p1 y p2
	/// </summary>
	/// <param name="p1">Vector 1</param>
	/// <param name="p2">Vector 2</param>
	/// <returns></returns>
	
	public static int AnguloResta(Vector p1, Vector p2)
	{

	    double res=0;

	    double x=p2.x-p1.x;
	    double y=p2.y-p1.y;

	    if (x<0)
		        res=180 + Math.atan (y / x) / rad;

	        else if ((x>0) && (y<0))
	            res = 360 + Math.atan (y / x) / rad;

	        else if ((x>0) && (y>0))
	            res = Math.atan (y / x) / rad;

	    return (int) res;
	}

	/// <summary>
	/// Cálculo del modulo del vector suma de p1 y p2
	/// </summary>
	/// <param name="p1">Vector 1</param>
	/// <param name="p2">Vector 2</param>
	/// <returns></returns>
	
	public static double ModuloSuma(Vector p1, Vector p2)
	{

	    double x=p2.x+p1.x;
	    double y=p2.y+p1.y;
	    return (Math.sqrt((x*x)+(y*y)));
	}
	
	/// <summary>
	/// Calculo del angulo del vector suma de p1 y p2
	/// </summary>
	/// <param name="p1">Vector 1</param>
	/// <param name="p2">Vector 2</param>
	/// <returns></returns>
	
	public static int AnguloSuma(Vector p1, Vector p2)
	{

	    double res=0;

	    double x=p2.x+p1.x;
	    double y=p2.y+p1.y;

	    if (x<0)
	        res = 180 + Math.atan (y / x) / rad;

	        else if ((x>0) && (y<0))
		        res=360 + Math.atan (y / x) / rad;

	        else if ((x>0) && (y>0))
	            res = Math.atan (y / x) / rad;

	    return (int) res;
	}
}





