package com.example.investeasy

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.investeasy.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {
            val valmonth = binding.tieValor.text.toString()
            val amountmonth = binding.tieQtdmeses.text.toString()
            val fees = binding.tieJuros.text.toString()
            if (valmonth.isEmpty() || amountmonth.isEmpty() || fees.isEmpty()) {
                Snackbar.make(binding.tieValor, "Preenche todos os campos", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                val valmonthDbl = valmonth.toDoubleOrNull() ?: 0.0
                val qtdmonthInt = amountmonth.toIntOrNull() ?: 0
                val valfeesDbl = fees.toDoubleOrNull() ?: 0.0

                if (valmonthDbl == null || qtdmonthInt == null || valfeesDbl == null  || valmonthDbl <= 0 || qtdmonthInt <= 0 || valfeesDbl <= 0) {
                    Snackbar.make(binding.tieValor, "Insira uma valor válido", Snackbar.LENGTH_LONG)
                        .show()
                    return@setOnClickListener
                }
                val jurosDecimal = valfeesDbl / 100
                val montantTotal = valmonthDbl * ((1 + jurosDecimal).pow(qtdmonthInt) - 1) / jurosDecimal
                val valorTotalInvest = valmonthDbl * qtdmonthInt
                val totalJuros =    montantTotal - valorTotalInvest

                binding.tvValorTotal.text = String.format("R$ %.2f", montantTotal)
                binding.tvRend.text = String.format("R$ %.2f", totalJuros)

            }

        }
        binding.btnClean.setOnClickListener {
            binding.tieValor.text?.clear()
            binding.tieQtdmeses.text?.clear()
            binding.tieJuros.text?.clear()
            binding.tvValorTotal.text = ""
            binding.tvRend.text = ""

        }
    }
}