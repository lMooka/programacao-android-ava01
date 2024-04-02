package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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
    }

    public void onClickCalcular(View v) {
        EditText etAva1a = findViewById(R.id.etValorNota1);
        EditText etAva1b = findViewById(R.id.etValorNota2);
        EditText etAva2 = findViewById(R.id.etValorNota3);
        EditText etAva3 = findViewById(R.id.etValorNota4);
        EditText etAva4 = findViewById(R.id.etValorNota5);
        CheckBox chkbSubstitutiva = findViewById(R.id.chkbSubstitutiva);
        TextView tvResultStatus = findViewById(R.id.tvValorResultadoStatus);
        TextView tvResultMsg = findViewById(R.id.tvValorResultadoMsg);
        TextView tvResultValor = findViewById(R.id.tvValorResultado);

        Double ma = 0d;
        try {
            Boolean temSubstitutiva = chkbSubstitutiva.isChecked();
            Double av1a = Double.parseDouble(etAva1a.getText().toString());
            Double av1b = Double.parseDouble(etAva1b.getText().toString());
            Double av2 = Double.parseDouble(etAva2.getText().toString());
            Double av3 = Double.parseDouble(etAva3.getText().toString());
            Double av4 = Double.parseDouble(etAva4.getText().toString());

            boolean aprovado;
            String resultMsg = "";

            ma = (((av1a + av1b) / 2) + av2 + av3) / 3;

            if (!temSubstitutiva && (av1a == 0d || av1b == 0d)) {
                aprovado = false;
                resultMsg = "Nota da Ava 1 ou Ava2 é zero";
                ma = 0d;
            } else if (!temSubstitutiva && av2 == 0d) {
                aprovado = false;
                resultMsg = "Nota da AV2 é zero";
                ma = 0d;
            } else if (!temSubstitutiva && av3 == 0d) {
                aprovado = false;
                resultMsg = "Nota da AV3 é zero";
                ma = 0d;
            } else {
                if (temSubstitutiva) {
                    ma = av4;
                }

                if (ma < 6d) {
                    aprovado = false;
                    resultMsg = "Média Final é menor que 6.0";
                } else {
                    aprovado = true;
                    resultMsg = "";
                }
            }

            tvResultMsg.setText(resultMsg);
            tvResultStatus.setText(aprovado ? "Aprovado" : "Reprovado");
            tvResultValor.setText(String.format(Locale.US, "%.2f", ma));
        } catch (Exception e) {
            Toast.makeText(this, "Necessário preencher todos os campos obrigatórios!", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickSubstitutiva(View v) {
        CheckBox chkbSubstitutiva = findViewById(R.id.chkbSubstitutiva);
        EditText tvSubstitutiva = findViewById(R.id.etValorNota5);

        tvSubstitutiva.setEnabled(chkbSubstitutiva.isChecked());
    }
}