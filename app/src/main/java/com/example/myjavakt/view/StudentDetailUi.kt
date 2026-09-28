package com.example.myjavakt.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myjavakt.entity.Students
import com.example.myjavakt.ui.theme.MyBlue

@Composable
fun StudentDetailUi(student: Students) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp)
            .background(MyBlue).alpha(0.5f)
    ) {
        Text(
            student.name,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Age: "+student.age,
            style = TextStyle(
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic
            ),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Gpa: "+student.gpa,
            style = TextStyle(
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic
            ),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}