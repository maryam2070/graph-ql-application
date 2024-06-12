package com.example.grapgqlapplication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DataList(data: List<String>,header:String) {

    Text(text = header, modifier = Modifier.padding(5.dp),
        fontWeight = FontWeight.Bold)
    LazyRow {
        items(data) {
            Card(
                modifier = Modifier.padding(5.dp),
                shape = RoundedCornerShape(16.dp)
            ) {

                Text(it,
                    Modifier
                        .background(Color.Gray)
                        .padding(5.dp), color = Color.White)
            }
        }
    }
}