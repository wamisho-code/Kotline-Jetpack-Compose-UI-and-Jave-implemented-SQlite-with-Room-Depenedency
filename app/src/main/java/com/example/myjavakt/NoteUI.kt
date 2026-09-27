package com.example.myjavakt

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dokar.sonner.Toast
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.ToasterState
import com.dokar.sonner.rememberToasterState
import com.example.myjavakt.ui.theme.MyBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteUI(viewModel: NoteViewModel = viewModel()) {
    val notes by viewModel.allNotes.observeAsState(emptyList())
    var showDialog by remember {
        mutableStateOf(false)
    }
    val toaster = rememberToasterState()



    if(showDialog){
        ModalBottomSheet(
            onDismissRequest = {
                showDialog=false
            }
        ) {
            InputDialog(viewModel, onDismis = {showDialog=false},toaster)
            Toaster(
                state = toaster
            )
        }
//        Dialog(
//            onDismissRequest = {
//                showDialog=false
//            }
//        ) {
//            InputDialog(viewModel, onDismis = {showDialog=false})
//        }
    }
    Box (
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(notes) { note ->
                Spacer(modifier = Modifier.height(5.dp))
                NoteItems(note)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

        FloatingActionButton (
            onClick = {
                showDialog = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add note"
            )
        }
        Toaster(
            state = toaster
        )

    }
}
@Composable
fun NoteItems(noteEntity: NoteEntity){
    Card(
        modifier = Modifier.wrapContentSize()
            .clickable(
                onClick = {
                    Toast("Will be opened soon!", ToastType.Success)
                }
            ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                   // .padding(6.dp)
                ,
                text = noteEntity.title,
                color = Color.DarkGray,
                textAlign = TextAlign.Left,
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight(),
                text = noteEntity.content,
                color = Color.Gray,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic
                )
            )

        }
    }
}
@Composable
fun InputDialog(viewModel: NoteViewModel, onDismis:()->Unit,toaster: ToasterState){

    var title by remember {
        mutableStateOf("")
    }
    var content by remember {
        mutableStateOf("")
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.width(360.dp),
                value = title,
                onValueChange = {
                    title=it
                },
                label = {
                    Text("Title")
                },
                leadingIcon = {
                    Icon(
                    painter = painterResource(R.drawable.title_svg),
                    contentDescription = null,
                    tint = Color.Blue,
                        modifier = Modifier.size(24.dp)
                )
                },
                singleLine = true,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier.width(360.dp),
                value = content,
                onValueChange = {
                    content=it
                },
                label = {
                    Text("Content")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.content_svg),
                        contentDescription = null,
                        tint = Color.Blue,
                        modifier = Modifier.size(24.dp)
                    )
                },
                singleLine = false,
                maxLines = 10
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
               onClick = {
                   if(title.isEmpty() || content.isEmpty()){
                       toaster.show(
                           "Please provide all information",
                           type = ToastType.Success
                       )
                       Log.e("toast","the failed")
                   }else{
                       viewModel.insert(NoteEntity(title,content))
                       toaster.show(
                           "Note Successfully Created!!",
                           type = ToastType.Success
                       )
                       onDismis()

                   }
               },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MyBlue
                )

            ) {
                Text(
                    "Save Note",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

            }
        }
    }

}