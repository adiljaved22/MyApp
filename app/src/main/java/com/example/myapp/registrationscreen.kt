package com.example.myapp



import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.serialization.builtins.LongArraySerializer
import kotlin.text.ifEmpty

@Composable
fun registerscreen(onBack:()-> Unit) {
    val context= LocalContext.current
    var username by remember { mutableStateOf("") }
    var userNamError by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var passwordVisible by remember{mutableStateOf(false)}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign-In")
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(
                text =userNamError.ifEmpty { "Username" },
                color = if (userNamError.isNotEmpty()) Red else Unspecified
            )},
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
            })
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value =email,
            onValueChange = {email= it },
            label = {  Text(
                text = emailError.ifEmpty { "Email" },
                color = if (emailError.isNotEmpty()) Red else Unspecified
            )},
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Email, contentDescription = null)
            })
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value =password,
            onValueChange = {password= it },
            label = {Text(text= passwordError.ifEmpty { "Password" },
                color = if (passwordError.isNotEmpty()) Red else Unspecified)},
            leadingIcon = {
                Icon(imageVector = (Icons.Rounded.Password), contentDescription = null)
            },


            visualTransformation =if (passwordVisible){
                VisualTransformation.None
            }
            else
            {
                PasswordVisualTransformation('*')
            },
            trailingIcon = {
                val visibilityIcon= if (passwordVisible){
                    Icons.Filled.Visibility
                }
                else
                {
                    Icons.Filled.VisibilityOff
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = visibilityIcon, contentDescription = null)
                }

            }
        )
        Button(onClick = {
            userNamError=if (username.isBlank()) "username is required" else ""
            emailError=when{
                email.isBlank() ->"email is required"
                !isValidEmail(email)->"email is invalid"
                else->""
            }
            passwordError= when{
                password.isBlank() ->"password is required"
                password.length<6 ->"Password must be at least 6 characters"
                else->""
            }
            if (userNamError.isEmpty() &&emailError.isEmpty() &&passwordError.isEmpty()){
                Toast.makeText(context, "SignUp Successful", Toast.LENGTH_LONG).show()
                onBack()
            }
            else{
                Toast.makeText(context, "signup unSuccessful", Toast.LENGTH_LONG).show()
            }
        }) {
            Text("Sign-Up")
        }

    }
}