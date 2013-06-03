package com.example.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button btTenPercent = (Button)findViewById(R.id.btTenPercent);
		btTenPercent.setOnClickListener(new TipButtonClientListener(10));
		
		final Button btFifteenPercent = (Button)findViewById(R.id.btFifteenPercent);
		btFifteenPercent.setOnClickListener(new TipButtonClientListener(15));
		
		final Button btTwentyPercent = (Button)findViewById(R.id.btTwentyPercent);
		btTwentyPercent.setOnClickListener(new TipButtonClientListener(20));
	}

	private class TipButtonClientListener implements View.OnClickListener {
		
		private float tipPercent;
		public TipButtonClientListener(float tipPercent) {
			this.tipPercent = tipPercent;
		}
		
		@Override
		public void onClick(View v) {
			final TextView tvTipAmount = (TextView)findViewById(R.id.tvTipAmount);
			final EditText etTotalAmount = (EditText)findViewById(R.id.etTotalAmount);
			float totalAmount = 0f;
			try {
				totalAmount = Float.valueOf(etTotalAmount.getText().toString());
			} catch (NumberFormatException nfe) {
				// log and ignore
			}
			float tipAmount = calculateTipAmount(totalAmount, tipPercent);
			DecimalFormat df = new DecimalFormat("0.00");
			tvTipAmount.setText("Tip is:  $" + df.format(tipAmount));
		}	
	}
	
	private float calculateTipAmount(float totalAmount, float tipPercent) {
		return totalAmount * tipPercent / 100;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
