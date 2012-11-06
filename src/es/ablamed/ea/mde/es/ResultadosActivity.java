package es.ablamed.ea.mde.es;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadosActivity extends Activity {
    
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados);
        
        Intent i= getIntent();
        
        TextView tvD1= (TextView)findViewById(R.id.tvD1);
        TextView tvD2= (TextView)findViewById(R.id.tvD2);
        TextView tvD3= (TextView)findViewById(R.id.tvD3);
        TextView tvT3= (TextView)findViewById(R.id.tvT3);
        TextView tvV3= (TextView)findViewById(R.id.tvV3);
        TextView tvW3= (TextView)findViewById(R.id.tvW3);
        TextView tvDL= (TextView)findViewById(R.id.tvDL);
        TextView tvWDDL= (TextView)findViewById(R.id.tvWinDirDL);
        TextView tvW1= (TextView)findViewById(R.id.tvWinDirDN);
        TextView tvDN= (TextView)findViewById(R.id.tvDN);
        TextView tvDT= (TextView)findViewById(R.id.tvDT);
        TextView tvWT= (TextView)findViewById(R.id.tvWT);
       
        
        tvD1.setText("D1: \n" + String.format("%.2f", (float)(i.getDoubleExtra("D1", 0))));
        tvD1.invalidate();
        tvD2.setText("D2: \n" + String.format("%.2f", (float)(i.getDoubleExtra("D2", 0))));
        tvD2.invalidate();
        tvD3.setText("D3: \n" + String.format("%.2f", (float)(i.getDoubleExtra("D3", 0))));
        tvD3.invalidate();
        tvT3.setText("T3: " + String.format("%d", i.getIntExtra("T3", 0)));
        tvT3.invalidate();
        tvV3.setText("V3: " + String.format("%.2f", (float)(i.getDoubleExtra("V3", 0))));
        tvV3.invalidate();
        tvW3.setText("W3: " + String.format("%d", i.getIntExtra("W3", 0)));
        tvW3.invalidate();
        tvDL.setText("DL: " + String.format("%.2f NM", (float)(i.getDoubleExtra("DL", 0))));
        tvDL.invalidate();
        tvWDDL.setText("W3: " + String.valueOf(i.getIntExtra("W3", 0)));
        tvWDDL.invalidate();
        tvDN.setText("DN: " + String.format("%.2f NM", (float)(i.getDoubleExtra("DN", 0))));
        tvDN.invalidate();
        tvW1.setText("W1: " + String.valueOf(i.getIntExtra("W1", 0)));
        tvW1.invalidate();
        tvDT.setText("DT: " + String.format("%.2f NM", (float)(i.getDoubleExtra("DT", 0))));
        tvDT.invalidate();
        tvWT.setText("WT: " + String.valueOf(i.getIntExtra("WT", 0)));
        tvWT.invalidate();
        
        Toast.makeText(this, "Debe añadir DA: 0,16NM en eje de pasada", Toast.LENGTH_LONG).show();
        
    }
}