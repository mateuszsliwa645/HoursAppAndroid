package com.example.hoursappandroid

import android.content.Intent
import android.os.BaseBundle
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class HistoryActivity : ComponentActivity() {

    lateinit var logOutBtn : Button
    lateinit var officePageBtn : Button
    lateinit var objectsPageBtn : Button
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val userId = intent.getStringExtra("user_id")

        logOutBtn = findViewById(R.id.logOutBtn)
        officePageBtn = findViewById(R.id.officePageBtn)
        objectsPageBtn = findViewById(R.id.objectsPageBtn)

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

    }
}