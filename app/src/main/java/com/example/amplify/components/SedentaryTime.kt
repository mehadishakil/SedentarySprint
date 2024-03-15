package com.example.amplify.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.amplify.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Preview(showBackground = true)
@Composable
fun SedentaryTimePreview() {
    SedentaryTime()
}


@Composable
fun SedentaryTime() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image positioned at the start
                Image(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.ic_hourglass),
                    contentDescription = "Hourglass Icon"
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sedentary Time",
                        color = Color(0xFF383A3D),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    TimeDisplay()

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(120.dp)
                            .padding(),
                    ) {
                        Text(
                            text = "Reset",
                            color = Color.White, // Change to a contrasting color
                            modifier = Modifier.padding(),
                        )
                    }
                }
            }
    }
}

@Composable
fun TimeDisplay() {
    val initialTime = remember { "05:12:00" }
    var elapsedTime by remember { mutableStateOf(0L) }

    LaunchedEffect(key1 = Unit) {
        launch {
            while (isActive) {
                delay(1000)
                val currentTime = System.currentTimeMillis()
                val startTimeMillis = getMillisFromTimeString(initialTime)
                elapsedTime = (currentTime - startTimeMillis) / 1000
            }
        }
    }

    val formattedTime = with(SimpleDateFormat("HH:mm:ss", Locale.getDefault())) {
        format(Date(elapsedTime * 1000))
    }

    Text(
        text = formattedTime,
        modifier = Modifier.padding(),
        style = TextStyle(
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            color = Color(0xFF004AB8),
            fontWeight = FontWeight.Bold
        )
    )
}

fun getMillisFromTimeString(timeString: String): Long {
    val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val date = dateFormat.parse(timeString)
    return date?.time ?: 0L
}

