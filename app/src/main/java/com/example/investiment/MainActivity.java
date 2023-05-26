package com.example.investiment;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText valorMensalEditText;
    private EditText valorCicloEditText;
    private EditText qtdeMesesInvestimentoEditText;
    private EditText qtdeMesesCicloEditText;
    private EditText porcentagemRetornoEditText;
    private EditText saldoInicialEditText;
    private Button calcularButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorMensalEditText = findViewById(R.id.valor_mensal);
        valorCicloEditText = findViewById(R.id.valor_ciclo);
        qtdeMesesInvestimentoEditText = findViewById(R.id.qtde_meses_investimento);
        qtdeMesesCicloEditText = findViewById(R.id.qtde_meses_ciclo);
        porcentagemRetornoEditText = findViewById(R.id.porcentagem_retorno);
        saldoInicialEditText = findViewById(R.id.saldo_inicial);
        calcularButton = findViewById(R.id.calcular_button_teste);

        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularInvestimento();
            }
        });
    }

    private void calcularInvestimento() {
        double valorMensal = Double.parseDouble(valorMensalEditText.getText().toString());
        double valorCiclo = Double.parseDouble(valorCicloEditText.getText().toString());
        int qtdeMesesInvestimento = Integer.parseInt(qtdeMesesInvestimentoEditText.getText().toString());
        int qtdeMesesCiclo = Integer.parseInt(qtdeMesesCicloEditText.getText().toString());
        double porcentagemRetorno = Double.parseDouble(porcentagemRetornoEditText.getText().toString());
        double saldoInicial = Double.parseDouble(saldoInicialEditText.getText().toString());

        List<String> resultados = new ArrayList<>();
        double saldo = saldoInicial;

        for (int mes = 1; mes <= qtdeMesesInvestimento; mes++) {
            double juros = saldo * (porcentagemRetorno / 100.0);
            saldo += juros;

            if (mes % qtdeMesesCiclo == 0) {
                String resultado = "Mês: " + mes + " - Juros sobre Saldo: R$ " + String.format("%.2f", juros) + " - Saldo: R$ " + String.format("%.2f", saldo);
                resultados.add(resultado);

                saldo += valorMensal;
                saldo += valorCiclo;
            }
        }

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putStringArrayListExtra("resultados", (ArrayList<String>) resultados);
        startActivity(intent);
    }
}
