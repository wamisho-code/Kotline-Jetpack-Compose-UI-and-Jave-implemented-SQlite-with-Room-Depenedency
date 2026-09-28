package com.example.myjavakt.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dokar.sonner.Toast
import com.example.myjavakt.entity.Students
import com.example.myjavakt.ui.theme.MyBlue
import com.example.myjavakt.viewmodel.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentUI(studentsViewModel: StudentViewModel= viewModel()) {
    val students by studentsViewModel.students.observeAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Students portal")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MyBlue
                )

            )
        }
    ) {
        StudentListUi(paddingValues = it,students)
    }
}
@Composable
fun StudentListUi(paddingValues: PaddingValues,students: List<Students>){
    LazyColumn(
        modifier = Modifier.padding(top=paddingValues.calculateTopPadding())
    ) {
        items(students){ student->
            Spacer(modifier = Modifier.height(5.dp))
            StudentCard(student)
            Spacer(modifier = Modifier.height(5.dp))

        }
    }
}
@Composable
fun StudentCard(student: Students){
    val context = LocalContext.current;
    Card(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            .clickable(
                onClick = {
                   // StudentDetailUi(student)
                    android.widget.Toast.makeText(context,"Will be opened soon", android.widget.Toast.LENGTH_SHORT).show()
                }
            ),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomEnd = 0.dp, bottomStart = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp)
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
}