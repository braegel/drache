package de.braegel.drache;

import android.app.Activity;
import android.os.Bundle;
//import android.util.Log;
import android.widget.TextView;


public class DracheActivity extends Activity {
	   /** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
	       TextView tv = new TextView(this);
//	       final String TAG = "drache.onCreate";
//	       Log.d(TAG,"testlog");
	       boolean[] sequence = {true};     
	       //	       1: left 0: right
	       sequence[0]=true;
	       int iteration, iterations = 8;
	       
	       boolean[] sequence_new = iterate(sequence);
	       
	       for(iteration=1;iteration<=iterations;iteration++){
	       sequence = sequence_new.clone();
	       sequence_new = iterate(sequence);
	       }
	       
	       tv.setText(sequence_to_string(sequence_new));
	       
	       setContentView(tv);
	   }
	   public boolean[] iterate(boolean[] sequence) {
//	       final String TAG = "drache.iterate";
//	       Log.d(TAG+" iterating sequence",sequence_to_string(sequence));

	       int position;

		   // iterate
/*	       1
	       110
	       1101100
	       110110011100100
	       ...
*/	       
	       // create new bigger array
	       boolean[] sequence_new = new boolean[sequence.length*2+1];
	       
	       // copy old sequence in new sequce
	       System.arraycopy(sequence, 0, sequence_new, 0, sequence.length);
	       
	       // add a "true"
	       sequence_new[sequence.length]=true;
//	       Log.d(TAG+" after adding a true",sequence_to_string(sequence_new));

	       // create back half of new array by xor mirroring first half
	       for(position=sequence.length-1;position >= 0;position=position-1) {
//	    	   Log.d(TAG+" get",Integer.toString(position)+":"+Boolean.toString(sequence[position]));
	    	   if (sequence[position] == true){
	    		   sequence_new[sequence.length-position+sequence.length] = false;
//		    	   Log.d(TAG+" set",Integer.toString(sequence.length-position+sequence.length)+":false");
//			       Log.d(TAG+" sequence_new",sequence_to_string(sequence_new));
	    	   }
	    	   else{
	    		   sequence_new[sequence.length-position+sequence.length] = true;
//		    	   Log.d(TAG+" set",Integer.toString(sequence.length-position+sequence.length)+":true");
//			       Log.d(TAG+" sequence_new",sequence_to_string(sequence_new));
	    	   }
	    		   
	       }
    	   return sequence_new;

	}
	private String sequence_to_string(boolean[] sequence_in){
	   String sequence ="";
	   Integer position = 0;
	   
	   for(position=0;position<sequence_in.length;position++){
		   if (sequence_in[position] == true){
		   sequence+='1';
		   }
		   else{
			   sequence+='0';
		   }
	   }
	   
	   return sequence;
	   }
}