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

class ObjectsHistoryActivity : ComponentActivity() {

    lateinit var logOutBtn : Button
    lateinit var officePageBtn : Button
    lateinit var objectsPageBtn : Button
    private lateinit var adapter: HistoryObjectAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var objectHoursBtn : Button
    lateinit var officeHistoryBtn : Button
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objects_history)

        val userId = intent.getStringExtra("user_id")

        logOutBtn = findViewById(R.id.logOutBtn)
        officePageBtn = findViewById(R.id.officePageBtn)
        objectsPageBtn = findViewById(R.id.objectsPageBtn)
        recyclerView = findViewById(R.id.recyclerView)
        objectHoursBtn = findViewById(R.id.objectsHoursBtn)
        officeHistoryBtn = findViewById(R.id.officeHoursBtn)

        logOutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        officeHistoryBtn.setOnClickListener {
            val intent = Intent (this, HistoryActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
            finish()
        }

        objectHoursBtn.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            intent.putExtra("user_id", userId)
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

        if (userId != null) {
            getHistoryData(userId.toInt()) { historyList ->
                adapter = HistoryObjectAdapter(historyList)
                recyclerView.adapter = adapter
            }
        }

    }
    fun getHistoryData(userId: Int,callback: (List<HistoryRecord>) -> Unit) {
        val url = "https://hosting2480966.online.pro/installatorapp/HoursApp/showObjectHistory.php?userId=$userId" // Adres URL do skryptu PHP

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