package com.example.composedyanamiccontentexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val mutableStateList = remember { mutableStateListOf<String>("Lax", "Jitu") }
    val inputTextState = remember {mutableStateOf("")}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(
            names = mutableStateList,
            {
                mutableStateList.add(inputTextState.value)
            },
            inputTextState.value,
            {
                inputTextState.value = it
            }
        )
    }
}

@Composable
fun GreetingList(names: List<String>,
                 buttonClick: () -> Unit,
                 textFieldValue: String,
                 textFieldUpdate: (newInputString: String) -> Unit) {
    for (name in names) {
        Greeting(name = name)
    }

    TextField(value = textFieldValue, onValueChange = textFieldUpdate)

    Button(onClick = buttonClick) {
        Text(text = "Add New Name")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        style = MaterialTheme.typography.displaySmall
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}