package es.ablamed.ea.mde.es;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InicioActivity extends Activity implements OnClickListener {
    
	private Spinner coeficiente;
	private Spinner paracas;
	
	//TODO
	//Programar los tiempos de apertura y lanzamientos y hacer el calculo con el propio telefono.
	//Se autocalcula la inclinación y rumbo a los tiempos programados.
	//Mas tarde posibilidad de calculos intermedios.
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filldatarelativelayout);
        
        paracas = (Spinner) findViewById(R.id.spParachute);
        
        ArrayAdapter<CharSequence> aaparacas = ArrayAdapter.createFromResource(this, R.array.parachutes_array, android.R.layout.simple_spinner_item);
        aaparacas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        paracas.setAdapter(aaparacas);
        
        coeficiente = (Spinner) findViewById(R.id.spCoefficient);
        
        ArrayAdapter<CharSequence> aacoeficiente = ArrayAdapter.createFromResource(this, R.array.coeficientes_array, android.R.layout.simple_spinner_item);
        aacoeficiente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        coeficiente.setAdapter(aacoeficiente);
        
        Button btnSolve = (Button) findViewById(R.id.btnSolve);
        btnSolve.setOnClickListener(this);


    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		menu.add(0, Menu.FIRST, 0, R.string.menu_preferences);
		menu.add(0, Menu.FIRST + 1, 0, R.string.menu_about);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case Menu.FIRST:
			startActivity(new Intent(this, CCTPreferenceActivity.class));
			break;

		case (Menu.FIRST + 1):
			startActivity(new Intent(this, about.class));
			break;
			
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);

	}

	public void onClick(View v) {
			
		if (v.getId()==R.id.btnSolve) {
						
		EditText etOpenAlt = (EditText) findViewById(R.id.etOpenAlt);
		EditText etOpenTime = (EditText) findViewById(R.id.etOpenTime);
		EditText etDropAlt = (EditText) findViewById(R.id.etDropAlt);
		EditText etDropTime = (EditText) findViewById(R.id.etDropTime);
		EditText etOpenWindDir = (EditText) findViewById(R.id.etOpenWindDir);
		EditText etOpenWindInt = (EditText) findViewById(R.id.etOpenWindInt);
		EditText etDropWindDir = (EditText) findViewById(R.id.etDropWindDir);
		EditText etDropWindInt = (EditText) findViewById(R.id.etDropWindInt);
		
		final Vector v1, v2;
		final Vector v3, v4;
		final double D1, D2, D3, D4;
		final double V3;
		final double C, K;
		final double DN, DL, DT;
		final int A1, A3, V1, T3, W1, W3, WT;
		final int COEFSEGUR;
		
 //		Comprobar que se han introducido valores para todos los campos y si no --> notificar.	
//		if (t1.getText().toString()==null || t2.getText().toString()=="" || t3==null || t4.getText().toString()=="")
//			
//			{
//				Toast.makeText(this, "Debe introducir valores para todos los campos de los vectores", Toast.LENGTH_LONG).show();
//			}
		
		D1=Integer.valueOf(etOpenWindInt.getText().toString()) * Integer.valueOf(etOpenTime.getText().toString());
		D2=Integer.valueOf(etDropWindInt.getText().toString()) * Integer.valueOf(etDropTime.getText().toString());
		
//		Toast.makeText(this, "D1: " + String.valueOf(D1), Toast.LENGTH_LONG).show();
		
		v1=new Vector(D1, Integer.valueOf(etOpenWindDir.getText().toString()));
		v2=new Vector(D2, Integer.valueOf(etDropWindDir.getText().toString()));
		
		A1 = Integer.valueOf(etOpenAlt.getText().toString());
		A3 = Integer.valueOf(etDropAlt.getText().toString()) - Integer.valueOf(etOpenAlt.getText().toString());
		V1 = Integer.valueOf(etOpenWindInt.getText().toString());
		W1 = Integer.valueOf(etOpenWindDir.getText().toString());
		D3 = Vector.ModuloSuma(v1, v2);
		W3 = Vector.AnguloSuma(v1, v2);
		T3 = Integer.valueOf(etDropTime.getText().toString()) - Integer.valueOf(etOpenTime.getText().toString());
		V3 = D3 / T3;
		COEFSEGUR = Integer.valueOf(coeficiente.getSelectedItem().toString());
		
		DL = (3 * (A3 / 1000) * V3) / 1852;
		
		C = GetC(A1, paracas.getSelectedItemPosition());
		K = GetK(A1, paracas.getSelectedItemPosition());
		
		Toast.makeText(this, "C:" + String.valueOf(C) + " K:" + String.valueOf(K) , Toast.LENGTH_SHORT).show();
		
		DN = ((A1/1000) - COEFSEGUR) * (V1 + C) / K;
		
		v3 = new Vector(DL, W3);
		v4 = new Vector(DN, W1);
		
		DT = Vector.ModuloSuma(v3, v4);
		WT = Vector.AnguloSuma(v3, v4);
		
		Intent intencion= new Intent(this, ResultadosActivity.class);
		intencion.putExtra("A3", A3);
		intencion.putExtra("D1", D1);
		intencion.putExtra("D2", D2);
		intencion.putExtra("D3", D3);
		intencion.putExtra("T3", T3);
		intencion.putExtra("V3", V3);
		intencion.putExtra("W3", W3);
		intencion.putExtra("DL", DL);
		intencion.putExtra("DN", DN);
		intencion.putExtra("W1", W1);
		intencion.putExtra("DT", DT);
		intencion.putExtra("WT", WT);
		
		startActivity(intencion);
		}
	}
	
	private double GetC (int Altitud, int paraca){
		
		String [] PARAM_C;
		
		switch (paraca) {
		case (0):
			PARAM_C = getResources().getStringArray(R.array.MT1X_C);
			break;
		
		case (1):
			PARAM_C = getResources().getStringArray(R.array.MACHIIIA_C);
			break;
			
		case (2):
			PARAM_C = getResources().getStringArray(R.array.TPMPLUS_C);
			break;
			
		default:
			PARAM_C = getResources().getStringArray(R.array.MT1X_C);
			break;
		}
				
		if (Altitud < 5001) return Double.valueOf(PARAM_C[0].toString());
		else if (5000 < Altitud || Altitud < 10001) return Double.valueOf(PARAM_C[1].toString());
		else if (10000 < Altitud || Altitud < 15001) return Double.valueOf(PARAM_C[2].toString());
		else if (15000 < Altitud || Altitud < 20001) return Double.valueOf(PARAM_C[3].toString());
		else if (20000 < Altitud) return Double.valueOf(PARAM_C[4].toString());
		else return -1;
	}
	
	private double GetK (int Altitud, int paraca){
		
		String [] PARAM_K;
		
		switch (paraca) {
		case (0):
			PARAM_K = getResources().getStringArray(R.array.MT1X_K);
			break;
		
		case (1):
			PARAM_K = getResources().getStringArray(R.array.MACHIIIA_K);
			break;
			
		case (2):
			PARAM_K = getResources().getStringArray(R.array.TPMPLUS_K);
			break;
			
		default:
			PARAM_K = getResources().getStringArray(R.array.MT1X_K);
			break;
		}
		
		if (Altitud < 5001) return Double.valueOf(PARAM_K[0].toString());
		else if (5000 < Altitud || Altitud < 10001) return Double.valueOf(PARAM_K[1].toString());
		else if (10000 < Altitud || Altitud < 15001) return Double.valueOf(PARAM_K[2].toString());
		else if (15000 < Altitud || Altitud < 20001) return Double.valueOf(PARAM_K[3].toString());
		else if (20000 < Altitud) return Double.valueOf(PARAM_K[4].toString());
		else return -1;
	}
}