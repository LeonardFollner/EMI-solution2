package de.davidgollasch.emiexercise1;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DigitSumActivity extends AppCompatActivity {

    private TextView tvOutput;
    private EditText etNumber;
    private Button btnFire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_sum);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.tuAkzentfarbe1BlauHell)));

        // Initialises the views and event listeners
        InitializeActivity();
    }

    /**
     * Construct the Interactive Structure
     */
    private void InitializeActivity() {

        /*
         *
         *
         *  NUMBER 2: LINK VIEWS
         *
         *
         */
        tvOutput = (TextView) findViewById(R.id.textViewOutput);
        etNumber = (EditText) findViewById(R.id.editTextNumber);
        btnFire = (Button) findViewById(R.id.buttonFire);

        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculateDigitSum();
            }
        });
    }

    /**
     * Trigger the Digit Sum Calculation
     */
    private void CalculateDigitSum() {
        /*
         *
         *
         *  NUMBER 3: CALCULATE AND PRINT DIGIT SUM
         *
         *
         */

        /*
         * a) Get entered number
         */

        int num = 0;
            try {
                num = Integer.decode(etNumber.getText().toString());
            } catch (Exception e) {
                // do nothing, keep num = 0
            }

        /*
         * b) Calculate digit sum
         *    (Hint: This can be done recursively using an additional function/method
         *    private int getDigitSum(int n))
         */

            int digitsum = getDigitSum(num);

        /*
         * c) Print result
         */

            tvOutput.setText(Integer.toString(digitsum));

        }

    private int getDigitSum(int n) {
        if(n < 0) {
            return getDigitSum(-n);
        }
        else if(n >= 0 && n < 10) {
            return n;
        }
        else {
            return getDigitSum(n / 10) + getDigitSum(n % 10);
        }
    }
}
