package deakinsit305.chalani.task21p;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner sourceUniteSpinner, destUnitSpinner;
    TextView resultText;
    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputValue = findViewById(R.id.inputValue);
        sourceUniteSpinner = findViewById(R.id.spinner4);
        destUnitSpinner = findViewById(R.id.spinner5);
        resultText = findViewById(R.id.textView12);
        convertButton = findViewById(R.id.button2);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sourceUnit = sourceUniteSpinner.getSelectedItem().toString();
                String destUnit = destUnitSpinner.getSelectedItem().toString();
                String input = inputValue.getText().toString();

                if(!input.isEmpty()){
                    try {
                        double value = Double.parseDouble(input);
                        double result = convertUnits(sourceUnit, destUnit, value);
                        resultText.setText("Result: " + result);
                    }
                    catch (Exception e){
                        resultText.setText("Invalid input");
                    }
                }
                else{
                    resultText.setText("Please enter a value.");
                }
            }
        });
    }

    private double convertUnits(String from, String to, double value){
        double baseValue = toBaseUnit(from, value);
        return fromBaseUnit(to, baseValue);
    }

    private double toBaseUnit(String unit, double value){
        switch (unit){
            case "Inch": return  value * 2.54;
            case "Foot": return value * 30.48;
            case "Yard": return value * 91.44;
            case "Mile": return value * 160934;
            case "Centimeter": return value;
            case "Kilometer": return value * 100000;

            case "Pound": return value * 453.592;
            case "Ounce": return value * 28.3495;
            case "Ton": return value * 907185;
            case "Gram": return value;
            case "Kilogram": return value * 1000;

            case "Celsius": return value;
            case "Fahrenheit": return (value - 32) / 1.8;
            case "Kelvin": return value - 273.15;
        }
        return 0;
    }

    private double fromBaseUnit(String unit, double baseValue){
        switch (unit){
            case "Inch": return baseValue / 2.54;
            case "Foot": return baseValue / 30.48;
            case "Yard": return baseValue / 91.44;
            case "Mile": return baseValue / 160934;
            case "Centimeter": return baseValue;
            case "Kilometer": return baseValue / 100000;

            case "Pound": return baseValue / 453.592;
            case "Ounce": return baseValue / 28.3495;
            case "Ton": return baseValue / 907185;
            case "Gram": return baseValue;
            case "Kilogram": return baseValue / 1000;

            case "Celsius": return baseValue;
            case "Fahrenheit": return (baseValue * 1.8) + 32;
            case "Kelvin": return baseValue + 273.15;
        }
        return 0;
    }
}
