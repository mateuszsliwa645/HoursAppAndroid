package com.example.hoursappandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class ObjectsActivity : ComponentActivity() {

    lateinit var logoutBtn : Button
    lateinit var enterObjectBtn : Button
    lateinit var leaveObjectBtn : Button
    lateinit var startRoadBtn : Button
    lateinit var endRoadBtn : Button
    lateinit var officePageBtn : Button
    lateinit var objectNameText : EditText
    lateinit var objectRoadText : EditText
    lateinit var TAG : String
    lateinit var objectName : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objects)

        logoutBtn = findViewById(R.id.logOutBtn)
        enterObjectBtn = findViewById(R.id.enterObjectBtn)
        leaveObjectBtn = findViewById(R.id.leaveObjectBtn)
        startRoadBtn = findViewById(R.id.startRoadBtn)
        endRoadBtn = findViewById(R.id.endRoadBtn)
        officePageBtn = findViewById(R.id.officePageBtn)
        objectNameText = findViewById(R.id.ObjectEditText)
        objectRoadText = findViewById(R.id.ObjectEditText2)
        TAG = "My Objects Activity"

        val userId = intent.getStringExtra("user_id")

        logoutBtn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        officePageBtn.setOnClickListener {
            intent = Intent(this, HomePageActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
            finish()
        }

        enterObjectBtn.setOnClickListener {
            userId?.let {
                Log.d(TAG,objectNameText.text.toString())
                enter_object(it)
            }
        }

        leaveObjectBtn.setOnClickListener {
            userId?.let {
                Log.d(TAG,objectNameText.text.toString())
                leave_object(it)
            }
        }

        startRoadBtn.setOnClickListener {
            userId?.let {
                Log.d(TAG,objectRoadText.text.toString())
                start_road(it)
            }
        }

        endRoadBtn.setOnClickListener {
            userId?.let {
                Log.d(TAG,objectRoadText.text.toString())
                end_road(it)
            }
        }

        val mainLayout = findViewById<RelativeLayout>(R.id.object_layout)
        mainLayout.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                hideKeyboard(v)
                v.clearFocus()
            }
            false
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun enter_object(user_id: String) {
        val url = "https://hosting2480966.online.pro/installatorapp/HoursApp/object_enter.php"
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
                params["object_name"] = objectNameText.text.toString()
                return params
            }
        }
        requestQueue.add(stringRequest)
    }

    private fun leave_object(user_id: String) {
        val url = "https://hosting2480966.online.pro/installatorapp/HoursApp/object_leave.php"

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
                params["object_name"] = objectNameText.text.toString()
                return params
            }
        }
        requestQueue.add(stringRequest)
    }

    private fun start_road(user_id: String) {
        val url = "https://hosting2480966.online.pro/installatorapp/HoursApp/road_enter.php"

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
                params["object_name"] = objectRoadText.text.toString()
                return params
            }
        }
        requestQueue.add(stringRequest)
    }

    private fun end_road(user_id: String) {
        val url = "https://hosting2480966.online.pro/installatorapp/HoursApp/road_leave.php"

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
                params["object_name"] = objectRoadText.text.toString()
                return params
            }
        }
        requestQueue.add(stringRequest)
    }
}
