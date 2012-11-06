package es.ablamed.ea.mde.es;

import es.ablamed.ea.mde.es.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		SetUpListView();
	}

	private void SetUpListView() {
		// TODO Auto-generated method stub
		
		String listItems[]= new String []{"Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 5", "Elemento 6"
	, "Elemento 7", "Elemento 8", "Elemento 9"};
		
		ArrayAdapter<String> listItemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		ListView lv = (ListView)this.findViewById(R.id.list_view_id);
		lv.setAdapter(listItemAdapter);
	}
}
