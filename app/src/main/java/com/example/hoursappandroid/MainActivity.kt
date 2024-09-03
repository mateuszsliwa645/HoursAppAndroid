package com.example.hoursappandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hoursappandroid.ui.theme.HoursAppAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val LoginTextEdit = findViewById<EditText>(R.id.idLogin)
        val PassTextExit = findViewById<EditText>(R.id.idPassword)
        val LoginButton = findViewById<Button>(R.id.idBtnLogin)
            
        }
    }


