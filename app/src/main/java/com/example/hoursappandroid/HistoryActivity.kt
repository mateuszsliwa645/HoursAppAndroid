package com.example.hoursappandroid

import android.content.Intent
import android.os.BaseBundle
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : ComponentActivity() {

    lateinit var logOutBtn : Button
    lateinit var officePageBtn : Button
    lateinit var objectsPageBtn : Button
    private lateinit var adapter: HistoryAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val userId = intent.getStringExtra("user_id")

        logOutBtn = findViewById(R.id.logOutBtn)
        officePageBtn = findViewById(R.id.officePageBtn)
        objectsPageBtn = findViewById(R.id.objectsPageBtn)
        recyclerView = findViewById(R.id.recyclerView)

        logOutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        objectsPageBtn.setOnClickListener {
            intent = Intent(this, ObjectsActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
            finish()
        }

        officePageBtn.setOnClickListener {
            intent = Intent(this, HomePageActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
            finish()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        getHistoryData { historyList ->
            adapter = HistoryAdapter(historyList)
            recyclerView.adapter = adapter
        }

    }
    fun getHistoryData(callback: (List<HistoryRecord>) -> Unit) {
        val url = "https://hosting2480966.online.pro/installatorapp/HoursApp/showHistory.php" // Adres URL do skryptu PHP

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Parsowanie JSON-a do listy obiektów HistoryRecord
                val listType = object : TypeToken<List<HistoryRecord>>() {}.type
                val historyList: List<HistoryRecord> = Gson().fromJson(response, listType)
                callback(historyList)
            },
            { error ->
                error.printStackTrace()
            }
        )

        // Dodanie żądania do kolejki Volley
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}