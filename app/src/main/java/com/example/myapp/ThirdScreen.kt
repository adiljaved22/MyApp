package com.example.myapp

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import kotlin.collections.addAll
import kotlin.text.clear
import kotlin.text.set

@Composable
fun ThirdScreen(NavigateToThirdScreen: () -> Unit) {
    val context = LocalContext.current

    val multiplePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris: List<Uri> ->

        Responsible.formList.clear()
        uris.forEach { uri ->
            Responsible.formList.add(ImageItem(uri = uri, name = "", description = ""))
        }
    }

    LazyColumn {
        item {
            Text(
                "Choose photos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    multiplePhotoLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            ) {
                Icon(imageVector = Icons.Rounded.Image, contentDescription = "")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Add Photos")
            }

            Responsible.formList.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = rememberAsyncImagePainter(item.uri), contentDescription = "user imag")
                    AsyncImage(
                        model = item.uri,
                        contentDescription = "User Image",
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                            .padding(8.dp),
                        contentScale = ContentScale.Crop
                    )

                    TextField(
                        value = item.name,
                        onValueChange = { newName ->
                            Responsible.formList[index] = item.copy(name = newName)
                        },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    TextField(
                        value = item.description,
                        onValueChange = { newDesc ->
                            Responsible.formList[index] = item.copy(description = newDesc)
                        },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    )
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    val isAllFilled = Responsible.formList.all {
                        it.name.isNotBlank() && it.description.isNotBlank()
                    }
                    if (isAllFilled) {

                        Responsible.savedList.addAll(Responsible.formList)
                        Responsible.formList.clear()
                        NavigateToThirdScreen()
                    } else {
                        Toast.makeText(
                            context,
                            "Please fill name and description for all images",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Text("Save")
            }
        }
    }
}
