package ar.com.ifts18.apparcial

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HistorialActivity: AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val tvHistorial1 = findViewById<TextView>(R.id.historial1)
        val tvHistorial2 = findViewById<TextView>(R.id.historial2)
        val tvHistorial3 = findViewById<TextView>(R.id.historial3)
        val tvHistorial4 = findViewById<TextView>(R.id.historial4)
        val tvHistorial5 = findViewById<TextView>(R.id.historial5)
        val tvDatosUsuario = findViewById<TextView>(R.id.datosUsuario)

        // Traigo Shared Preferences para info usuario
        val misPreferencias = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val correo_global = misPreferencias.getString("correo_global", null)

        val historialPreferences = getSharedPreferences("user_$correo_global", Context.MODE_PRIVATE)

        val hNombre = historialPreferences.getString("nombre", null)
        val hUsername = historialPreferences.getString("apellido", null)
        val hPerfil = historialPreferences.getString("perfil", null)
        // uso correo pero va perfil, falta ver con Ari


        if (hNombre != null) {
            if (hUsername != null) {
                if (hPerfil != null) {
                    tvDatosUsuario.text = "Hola ${hNombre.replaceFirstChar { it.titlecase() }} ${hUsername.replaceFirstChar { it.titlecase() }} su perfil es ${hPerfil.replaceFirstChar { it.titlecase() }}.\nEstas son sus ultimas consultas:"
                }
            }
        }


        val contadorMax = historialPreferences.getInt("contador", 0)

        val historialRegistro: MutableMap<String, String> = mutableMapOf()

        historialRegistro["historial1"] = "registro1"
        historialRegistro["historial2"] = "registro2"
        historialRegistro["historial3"] = "registro3"

        // Obtén los valores de SharedPreferences, y si no existen usa "Sin registro" como valor por defecto
        val registro1 = historialPreferences.getString("historial1", "Sin registro")
        val registro2 = historialPreferences.getString("historial2", "Sin registro")
        val registro3 = historialPreferences.getString("historial3", "Sin registro")
        val registro4 = historialPreferences.getString("historial4", "Sin registro")
        val registro5 = historialPreferences.getString("historial5", "Sin registro")

        /*
        if(contadorMax != 0){
            for (i in 1..contadorMax) {
                tvHistorial
            }
        }
        */
        if(registro5 == "Sin registro"){
            tvHistorial5.visibility = View.GONE
        }
        if(registro4 == "Sin registro"){
            tvHistorial4.visibility = View.GONE
        }
        if(registro3 == "Sin registro"){
            tvHistorial3.visibility = View.GONE
        }
        if(registro2 == "Sin registro"){
            tvHistorial2.visibility = View.GONE
        }
        if(registro1 == "Sin registro"){
            tvHistorial1.visibility = View.GONE
        }
        // Asigna cada valor a su correspondiente TextView
        tvHistorial1.text = registro1 ?: "Sin registro"
        tvHistorial2.text = registro2 ?: "Sin registro"
        tvHistorial3.text = registro3 ?: "Sin registro"
        tvHistorial4.text = registro4 ?: "Sin registro"
        tvHistorial5.text = registro5 ?: "Sin registro"




        val buttonRegresar = findViewById<Button>(R.id.buttonRegresar)
        buttonRegresar.setOnClickListener {
            irAlSimulador()
            // Regresa a MainActivity
            //finish() // regresar al main para que ingrese otro usuario sus datos
        }

    }
    private fun irAlSimulador() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun mostrarToast(mensajeToast: String){
        Toast.makeText(this, mensajeToast, Toast.LENGTH_LONG).show()
    }
}
