package EVIL.KATIE.PACKAGE;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MTSActivity extends AppCompatActivity {

    private static final int SAMPLE_RATE = 44100;

    private Button playButton;
    EditText input;
    private AudioTrack audioTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mts);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playButton = findViewById(R.id.MTSPlayButton);
        input = findViewById(R.id.MTSInput);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMorse(input.getText().toString());
            }
        });
    }

    private void playMorse(String morse) {

        new Thread(() -> {

            sleep(300);

            for (char c : morse.toCharArray()) {

                if (c == '.') {
                    runOnUiThread(this::dot);
                    sleep(150);
                }

                else if (c == '-') {
                    runOnUiThread(this::dash);
                    sleep(350);
                }

                else if (c == ' ') {
                    sleep(300);
                }

                else if (c == '/') {
                    sleep(700);
                }

                sleep(80);
            }

        }).start();
    }

    private void dot() {
        playTone(100, 300);
    }

    private void dash() {
        playTone(300, 300);
    }

    private void playTone(int durationMs, double freq) {

        int count = (int) (SAMPLE_RATE * durationMs / 1000.0);
        short[] samples = new short[count];

        for (int i = 0; i < count; i++) {
            samples[i] = (short) (Math.sin(2 * Math.PI * i / (SAMPLE_RATE / freq)) * 32767);
        }

        audioTrack = new AudioTrack(
                AudioManager.STREAM_MUSIC,
                SAMPLE_RATE,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                samples.length * 2,
                AudioTrack.MODE_STATIC
        );

        audioTrack.write(samples, 0, samples.length);
        audioTrack.play();
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (audioTrack != null) {
            audioTrack.release();
            audioTrack = null;
        }
    }
}