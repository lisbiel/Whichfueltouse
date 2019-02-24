package br.com.bossini.whichfueltouse;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

import static br.com.bossini.whichfueltouse.R.id.gasPriceLabel;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private double gasPrice = 4.00;
    private double ethanolPrice = 3.00;
    private TextView gasolineTextView;
    private TextView gasPriceLabel;
    private TextView ethanolTextView;
    private TextView ethanolPriceLabel;
    private TextView chosenTextView;
    private TextView decisionLabel;
    private ImageView fuelImage;
    private SeekBar gasolineSeekBar;
    private SeekBar ethanolSeekBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gasolineTextView = findViewById(R.id.gasolineTextView);
        gasPriceLabel = findViewById(R.id.gasPriceLabel);
        gasPriceLabel.setText(currencyFormat.format(gasPrice));
        ethanolTextView = findViewById(R.id.ethanolTextView);
        ethanolPriceLabel = findViewById(R.id.ethanolPriceLabel);
        ethanolPriceLabel.setText(currencyFormat.format(ethanolPrice));

        chosenTextView = findViewById(R.id.chosenTextView);
        decisionLabel = findViewById(R.id.decisionLabel);
        fuelImage = findViewById(R.id.fuelImage);

        gasolineSeekBar = findViewById(R.id.gasolineSeekBar);
        gasolineSeekBar.setOnSeekBarChangeListener(seekBarChangeListenerGasoline);

        ethanolSeekBar = findViewById(R.id.ethanolSeekBar);
        ethanolSeekBar.setOnSeekBarChangeListener(seekBarChangeListenerEthanol);
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListenerGasoline =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    gasPrice = progress/100d;
                    gasPriceLabel.setText(currencyFormat.format(gasPrice));
                    calculate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private SeekBar.OnSeekBarChangeListener seekBarChangeListenerEthanol =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    ethanolPrice = progress/100d;
                    ethanolPriceLabel.setText(currencyFormat.format(ethanolPrice));
                    calculate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private void calculate() {
        if ((ethanolPrice/gasPrice) < 0.7) {
            decisionLabel.setText(getResources().getString(R.string.decisionEthanol_text));
            fuelImage.setImageResource(R.drawable.ethanol);
        } else {
            decisionLabel.setText(getResources().getString(R.string.decisionGas_text));
            fuelImage.setImageResource(R.drawable.gasoline);
        }
    }
}
