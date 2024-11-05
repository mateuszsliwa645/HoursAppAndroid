package com.example.hoursappandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class HomePageActivity : ComponentActivity(){

    lateinit var logOutBtn : Button
    lateinit var enterOfficeBtn : Button
    lateinit var leaveOfficeBtn : Button
    lateinit var enterRemoteBtn : Button
    lateinit var leaveRemoteBtn : Button
    lateinit var objectsPageBtn : Button
    lateinit var historyBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        logOutBtn = findViewById(R.id.logOutBtn)
        enterOfficeBtn = findViewById(R.id.enterObjectBtn)
        leaveOfficeBtn = findViewById(R.id.leaveObjectBtn)
        enterRemoteBtn = findViewById(R.id.startRoadBtn)
        leaveRemoteBtn = findViewById(R.id.endRoadBtn)
        objectsPageBtn = findViewById(R.id.objectsPageBtn)
        historyBtn = findViewById(R.id.historyBtn)



        val userId = intent.getStringExtra("user_id")

        logOutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        enterOfficeBtn.setOnClickListener {
            userId?.let{
                enter_office(it)
            }
        }

        leaveOfficeBtn.setOnClickListener {
            userId?.let{
                leave_office(it)
            }
        }

        enterRemoteBtn.setOnClickListener {
            userId?.let {
                enter_remote(it)
            }
        }

        leaveRemoteBtn.setOnClickListener {
            userId?.let {
                leave_remote(it)
            }
        }

        objectsPageBtn.setOnClickListener {
            intent = Intent(this, ObjectsActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
            finish()
        }

        historyBtn.setOnClickListener {
            intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra(
                "user_id", userId
            )
            startActivity(intent)
            finish()
        }
    }

    private fun enter_office(user_id: String) {
        val url = "https://hosting2480966.online.pro/installatorapp/HoursApp/office_enter.php"

        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                // Obsługa odpowiedzi serwera
                Toast.makeText(this, "Response: $response", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                // Obsługa błędów
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                // Parametry do wysłania w zapytaniu POST
                val params = HashMap<String, String>()
                params["user_id"] = user_id
                return params
            }
        }
        requestQueue.add(stringRequest)
    }


    private fun leave_office(user_id : String){
        val url = "https://hosting2480966.online.pro/installatorapp/HoursApp/office_leave.php"

        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                // Obsługa odpowiedzi serwera
                Toast.makeText(this, "Response: $response", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                // Obsługa błędów
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                // Parametry do wysłania w zapytaniu POST
                val params = HashMap<String, String>()
                params["user_id"] = user_id
                return params
            }
        }
        requestQueue.add(stringRequest)

    }


    private fun enter_remote(user_id : String){
        val url = "https://hosting2480966.online.pro/installatorapp/HoursApp/remote_enter.php"

        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                // Obsługa odpowiedzi serwera
                Toast.makeText(this, "Response: $response", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                // Obsługa błędów
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                // Parametry do wysłania w zapytaniu POST
                val params = HashMap<String, String>()
                params["user_id"] = user_id
                return params
            }
        }
        requestQueue.add(stringRequest)
    }

    private fun leave_remote(user_id : String){
        val url = "https://hosting2480966.online.pro/installatorapp/HoursApp/remote_leave.php"

        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                // Obsługa odpowiedzi serwera
                Toast.makeText(this, "Response: $response", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                // Obsługa błędów
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                // Parametry do wysłania w zapytaniu POST
                val params = HashMap<String, String>()
                params["user_id"] = user_id
                return params
            }
        }
        requestQueue.add(stringRequest)
    }
}