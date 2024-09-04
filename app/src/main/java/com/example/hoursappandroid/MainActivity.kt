package com.example.hoursappandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : ComponentActivity() {

    private lateinit var LoginTextEdit: EditText
    private lateinit var PassTextEdit: EditText
    private lateinit var LoginButton: Button
    private lateinit var requestQueue: RequestQueue
    private lateinit var rememberMeCheckBox : CheckBox
    private lateinit var sharedPreferences: SharedPreferences
    private val TAG = "MainActivity"
    private val PREF_NAME = "LoginPrefs"
    private val PREF_USERNAME = "username"
    private val PREF_PASSWORD = "password"
    private val PREF_REMEMBER = "rememberMe"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val LoginTextEdit = findViewById<EditText>(R.id.idLogin)
        val PassTextExit = findViewById<EditText>(R.id.idPassword)
        val LoginButton = findViewById<Button>(R.id.idBtnLogin)
        requestQueue = Volley.newRequestQueue(this)
        rememberMeCheckBox = findViewById(R.id.saveLoginCheckBox)

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        if(sharedPreferences.getBoolean(PREF_REMEMBER, false)) {
            LoginTextEdit.setText(sharedPreferences.getString(PREF_USERNAME, ""))
            PassTextExit.setText(sharedPreferences.getString(PREF_PASSWORD,""))
            rememberMeCheckBox.isChecked = true
        }


        LoginButton.setOnClickListener {
            val user_login = LoginTextEdit.text.toString().trim()
            val user_pass = PassTextExit.text.toString().trim()

            if(user_login.isNotEmpty() && user_pass.isNotEmpty()){
                loginUser(user_login, user_pass)
            }else{
                Toast.makeText(this, "Podaj login i hasło!", Toast.LENGTH_SHORT).show()
            }
        }
        }
    private fun loginUser(username: String, password: String){
        val url ="https://hosting2480966.online.pro/installatorapp/HoursApp/login.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Log.d(TAG, "Response: $response")
                try{
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if(success){
                        Log.d(TAG, "Login successful")

                        val editor = sharedPreferences.edit()
                        if(rememberMeCheckBox.isChecked) {
                            editor.putString(PREF_USERNAME, username)
                            editor.putString(PREF_PASSWORD, password)
                            editor.putBoolean(PREF_REMEMBER, true)
                            Log.d("SharedPreferences", "Dane zapisane: " + sharedPreferences.getString(PREF_USERNAME, ""))
                        } else {
                            editor.clear()
                        }
                        editor.apply()

                        val userId = jsonObject.getInt("user_id").toString()

                        val intent = Intent(this, HomePageActivity::class.java)
                        intent.putExtra("user_id", userId)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Złe hasło lub login!",Toast.LENGTH_SHORT).show()
                    }
                }catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Login error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
                
            },
            Response.ErrorListener { error ->
                Log.e(TAG, "Error: ${error.message}")
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }
        requestQueue.add(stringRequest)
    }




}


