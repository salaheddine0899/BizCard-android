package com.example.jetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetBizCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(){
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = CardDefaults.cardElevation(4.dp)
        )  {
            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally){
                CreateImageProfile()
                HorizontalDivider()
                CreateInfo()
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Button(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
                        onClick = {
                            buttonClickedState.value = ! buttonClickedState.value;
                        }, colors = ButtonColors(containerColor = Color.Transparent,
                            contentColor = Color.White, disabledContentColor = Color.White,
                            disabledContainerColor = Color.Gray),
                        shape = RoundedCornerShape(corner = CornerSize(5.dp))
                    ) {
                        Text(text = "Portfolio")
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                ){
                if(buttonClickedState.value){
                    ProjectsList()
                }
            }

        }

    }
}
@Preview
@Composable
fun ProjectsList(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)) {
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxSize(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Portfolio(data= listOf("Project 1", "Project 2", "Project 3"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data){
            item ->
                Card(modifier = Modifier.padding(13.dp),
                    shape = RectangleShape,
                    elevation = CardDefaults.cardElevation(3.dp)) {
                    Row(modifier = Modifier.background(color = Color.Transparent).fillMaxWidth()
                        .height(100.dp)
                        .padding(10.dp)
                        ,verticalAlignment = Alignment.CenterVertically) {
                        VerticalDivider()
                        Column(modifier = Modifier.background(color = Color.Transparent)){
                            Image(
                                painter = painterResource(R.drawable.profile), contentDescription = "profile",
                                modifier = Modifier.size(80.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(10 .dp)){
                            Text(text = item, style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "A great project indeed",
                                style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                    HorizontalDivider()
                }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Row {
            Text(
                text = "Tamega Canadien A.",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(text = "+", fontSize = TextUnit(value = 20f, TextUnitType.Sp))
        }
        Text(
            text = "Full stack developer", color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(3.dp)
        )
        Text(
            text = "@Software", color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(3.dp)
        )
    }
}

@Composable
private fun CreateImageProfile() {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp), shape = CircleShape,
        border = BorderStroke(width = 0.5.dp, color = Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(R.drawable.profile), contentDescription = "profile",
            modifier = Modifier.size(135.dp), contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetBizCardTheme {
        CreateBizCard()
    }
}