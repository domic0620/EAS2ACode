package edu.co.icesi.eas2acode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView loginTitle;
    private ImageView loginLogo;
    private EditText loginUsername;
    private EditText loginPassword;
    private Button loginIniciarBtn;

    private Button control;
    private int contador = 0;
    private boolean buttonPressed;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginTitle = findViewById(R.id.loginTitle);
        loginLogo = findViewById(R.id.loginLogo);
        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
        loginIniciarBtn = findViewById(R.id.loginIniciarBtn);

        control = findViewById(R.id.control);

        loginIniciarBtn.setOnClickListener(
                (v) -> {
                    String username = loginUsername.getText().toString();
                    String password = loginPassword.getText().toString();
                    Toast.makeText(this, username + " " + password, Toast.LENGTH_LONG).show();
                    loginTitle.setText(username + ":" + password);
                }
        );

        loginLogo.setOnTouchListener(
                (view, event) -> {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            loginTitle.setText("DOWN: " + event.getX() + ", " + event.getY());
                            break;

                        case MotionEvent.ACTION_MOVE:
                            loginTitle.setText("MOVE: " + event.getX() + ", " + event.getY());
                            break;

                        case MotionEvent.ACTION_UP:
                            loginTitle.setText("UP: " + event.getX() + ", " + event.getY());
                            break;
                    }

                    return true;
                }
        );


        control.setOnTouchListener(
                (view, event) -> {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            control.setBackgroundColor(Color.rgb(30, 30, 30));
                            buttonPressed = true;
                            new Thread(
                                    () -> {
                                        while (buttonPressed) {
                                            contador++;
                                            Log.e("Cuenta", "contador: " + contador);

                                            runOnUiThread(() -> loginTitle.setText("" + contador));

                                            try {
                                                Thread.sleep(300);
                                            } catch (InterruptedException ex) {

                                            }
                                        }
                                    }
                            ).start();
                            break;

                        case MotionEvent.ACTION_MOVE:

                            break;

                        case MotionEvent.ACTION_UP:
                            buttonPressed = false;
                            control.setBackgroundColor(Color.rgb(120, 120, 120));
                            break;
                    }

                    return true;
                }

        );


    }
}