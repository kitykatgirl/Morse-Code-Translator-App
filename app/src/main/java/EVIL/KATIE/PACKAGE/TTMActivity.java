package EVIL.KATIE.PACKAGE;

import static EVIL.KATIE.PACKAGE.MorseCode.convertMorse;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TTMActivity extends AppCompatActivity {

    boolean codeToEnglish = false;
    boolean isSwapping = false;
    String inputText = "";
    String outputText = "";
    TextView inputTextTTM;
    TextView outputTextTTM;
    EditText input;
    EditText output;
    Button swap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ttm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inputTextTTM = findViewById(R.id.TTMInputText);
        outputTextTTM = findViewById(R.id.TTMOutputText);
        input = findViewById(R.id.TTMInput);
        output = findViewById(R.id.TTMOutput);
        swap = findViewById(R.id.TTMSwapButton);

        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSwapping = true;

                inputText = output.getText().toString();
                outputText = input.getText().toString();

                input.setText(inputText);
                output.setText(outputText);

                if (codeToEnglish) {
                    codeToEnglish = false;
                    inputTextTTM.setText("Text");
                    input.setHint("Text here");
                    outputTextTTM.setText("Morse");
                    output.setHint("Morse appears here");

                    String stringInput = input.getText().toString();
                    if (!stringInput.isBlank()){
                        output.setText(convertMorse(codeToEnglish, stringInput));
                    }
                    else {
                        output.setText("");
                    }
                } else {
                    codeToEnglish = true;
                    inputTextTTM.setText("Morse");
                    input.setHint("Morse here");
                    outputTextTTM.setText("Text");
                    output.setHint("Text appears here");

                    String stringInput = input.getText().toString();
                    if (!stringInput.isBlank()){
                        output.setText(convertMorse(codeToEnglish, stringInput));
                    }
                    else {
                        output.setText("");
                    }
                }

                isSwapping = false;
            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (isSwapping) return;

                String stringInput = input.getText().toString();
                if (!stringInput.isBlank()){
                    output.setText(convertMorse(codeToEnglish, stringInput));
                }
                else {
                    output.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
            }
        });
    }

}