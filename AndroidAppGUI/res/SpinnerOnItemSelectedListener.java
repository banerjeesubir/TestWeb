package comnest.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SpinnerOnItemSelectedListener implements OnItemSelectedListener 
{
	 
	@Override
    public void onItemSelected(AdapterView<?> parent,
        View view, int pos, long id) {
      Toast.makeText(parent.getContext(), "Item is " +
          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
    }
 
	@Override
    public void onNothingSelected(AdapterView parent) {
      // Do nothing.
    }
	
}