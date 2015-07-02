package edu.uw.tacoma.mmuppa.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.text.NumberFormat;

public class TipCalculatorActivity extends Activity 
implements OnEditorActionListener, OnClickListener {

    // define instance variables for the widgets
    private EditText mBillAmountEditText;
    private TextView mPercentTextView;
    private Button mPercentUpButton;
    private Button mPercentDownButton;
    private TextView mTipTextView;
    private TextView mTotalTextView;
    
    // define an instance variable for tip percent
    private float tipPercent = .15f;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        
        // get references to the widgets
        mBillAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        mPercentTextView = (TextView) findViewById(R.id.percentTextView);
        mPercentUpButton = (Button) findViewById(R.id.percentUpButton);
        mPercentDownButton = (Button) findViewById(R.id.percentDownButton);
        mTipTextView = (TextView) findViewById(R.id.tipTextView);
        mTotalTextView = (TextView) findViewById(R.id.totalTextView);

        // set the listeners
        mBillAmountEditText.setOnEditorActionListener(this);
        mPercentUpButton.setOnClickListener(this);
        mPercentDownButton.setOnClickListener(this);
        
        calculateAndDisplay();
    }
    
    public void calculateAndDisplay() {        

        // get the bill amount
        String billAmountString = mBillAmountEditText.getText().toString();
        float billAmount; 
        if (billAmountString.equals("")) {
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }
        
        // calculate tip and total 
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;
        
        // display the results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        mTipTextView.setText(currency.format(tipAmount));
        mTotalTextView.setText(currency.format(totalAmount));
        
        NumberFormat percent = NumberFormat.getPercentInstance();
        mPercentTextView.setText(percent.format(tipPercent));
    }
    
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateAndDisplay();
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.percentDownButton:
            tipPercent = tipPercent - .01f;
            calculateAndDisplay();
            break;
        case R.id.percentUpButton:
            tipPercent = tipPercent + .01f;
            calculateAndDisplay();
            break;
        }
    }
}