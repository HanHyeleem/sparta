package com.android.spa_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.spa_02.ui.theme.Spa_02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Spa_02Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private var lastNumeric: Boolean = false
    private var stateError: Boolean = false
    private var lastOperator: Char? = null
    private var valueOne: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
    }

    fun onDigit(view: View) {
        if (stateError) {
            editText.setText((view as Button).text)
            stateError = false
        } else {
            editText.append((view as Button).text)
        }
        lastNumeric = true
    }

    fun onClear(view: View) {
        editText.setText("")
        lastNumeric = false
        stateError = false
        lastOperator = null
        valueOne = null
    }

    fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            valueOne = editText.text.toString().toDouble()
            lastOperator = (view as Button).text[0]
            editText.append((view as Button).text)
            lastNumeric = false
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric && !stateError && lastOperator != null) {
            val valueTwo = editText.text.toString().substringAfterLast(lastOperator.toString()).toDouble()
            val result = when (lastOperator) {
                '+' -> valueOne!! + valueTwo
                '-' -> valueOne!! - valueTwo
                else -> 0.0
            }
            editText.setText(result.toString())
            lastOperator = null
            valueOne = null
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Spa_02Theme {
        Greeting("Android")
    }
}