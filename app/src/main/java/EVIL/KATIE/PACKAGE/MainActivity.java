package EVIL.KATIE.PACKAGE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button buttonGoTTM;
    Button buttonGoML;
    Button buttonGoMTS;

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
        Extras.sayHello();

        buttonGoTTM = findViewById(R.id.buttonGoTTM);
        buttonGoML = findViewById(R.id.buttonGoML);
        buttonGoMTS = findViewById(R.id.buttonGoMTS);

        buttonGoTTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTTM = new Intent(MainActivity.this, TTMActivity.class);
                startActivity(intentTTM);
            }
        });
        buttonGoML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentML = new Intent(MainActivity.this, MLActivity.class);
                startActivity(intentML);
            }
        });
        buttonGoMTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMTS = new Intent(MainActivity.this, MTSActivity.class);
                startActivity(intentMTS);
            }
        });
    }
}