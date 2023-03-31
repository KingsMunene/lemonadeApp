package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    LemonadeComponent()
}

@Composable
fun StateFulLemonade() {


}
@Composable
fun LemonadeComponent(modifier:Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)) {

    Column(modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {

        var lemonadeState by rememberSaveable { mutableStateOf(R.string.lemon_tree)}

        var lemonSize by remember { mutableStateOf(0)}


        var imageResource =  0
        var textResource = 0

        when(lemonadeState){
            R.string.lemon_tree -> {
                imageResource = R.drawable.lemon_tree
                textResource = R.string.select_message
            }

            R.string.lemon -> {
                imageResource = R.drawable.lemon_squeeze
                textResource = R.string.squeeze_message
            }

            R.string.lemonade_glass -> {
                imageResource =  R.drawable.lemon_drink
                textResource = R.string.drink_message
            }

           else -> {
                imageResource = R.drawable.lemon_restart
                textResource = R.string.begin_alert
            }

        }

        fun imageFunc(){
            lemonSize = (1..8).random()

            if (lemonSize > 0) {
                lemonSize -= 1
            }

            if (lemonadeState == R.string.lemon_tree && lemonSize > 0 ){
                lemonadeState = R.string.lemon
            } else if (lemonSize == 0){
                lemonadeState = R.string.lemonade_glass
            }else if (lemonadeState == R.string.lemonade_glass){
                lemonadeState = R.string.empty_glass
            }else if(lemonadeState == R.string.empty_glass){
                lemonadeState = R.string.lemon_tree
            }

        }

        Text(text = stringResource(textResource))

        Spacer(modifier = Modifier.height(16.dp))

        Image(painter = painterResource(imageResource), contentDescription = null,
        modifier = Modifier
            .border(3.dp, color = Color.Blue)
            .clickable(onClick = { imageFunc() })
        )

    }
    
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeAppTheme {
        LemonadeApp()
    }
}