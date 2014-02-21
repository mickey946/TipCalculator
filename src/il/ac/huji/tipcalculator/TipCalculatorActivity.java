package il.ac.huji.tipcalculator;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {

    private static final double TIP_FACTOR = 10;
    private static final double ROUND_PRECISION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
    }

    public void calculateTip(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_bill_amount);

        Double amount = null;
        try {
            amount = Double.parseDouble(editText.getText().toString());
        } catch (NumberFormatException e) { // empty string or non-numeric
            // ignore
        }

        if (amount != null) {
            CheckBox checkRound = (CheckBox) findViewById(R.id.checkbox_round);
            boolean round = checkRound.isChecked();

            Double tip = Math.round(amount / TIP_FACTOR * ROUND_PRECISION) / ROUND_PRECISION;

            if (round)
                tip = Math.ceil(tip);

            TextView textTipResult = (TextView) findViewById(R.id.text_tip_result);
            Resources res = getResources();
            String tipResultInfo = res.getString(R.string.text_tip_result_info);
            textTipResult.setText(tipResultInfo + tip.toString());
        }
    }
}
