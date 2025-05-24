package com.example.atividadeavaliativa1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.atividadeavaliativa1.ui.theme.AtividadeAvaliativa1Theme
import com.example.atividadeavaliativa1.ui.theme.robotoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    onSuccessfulLogin: () -> Unit = {},
    onForgetPasswordButtonClick: () -> Unit = {},
    onRegisterButtonClick: () -> Unit = {}
) {
    val username by viewModel::username
    val password by viewModel::password
    val usernameError by viewModel::usernameError
    val passwordError by viewModel::passwordError
    val isLoading by viewModel::isLoading
    val isLoginSuccessful by viewModel::isLoginSuccessful

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = "Ícone do App",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.login_welcome_text),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = robotoFontFamily
            )
            Text(
                text = stringResource(R.string.login_welcome_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontFamily = robotoFontFamily
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { viewModel.username = it },
                label = { Text(stringResource(R.string.email_input_label), fontFamily = robotoFontFamily) },
                placeholder = { Text(stringResource(R.string.email_input_placeholder), fontFamily = robotoFontFamily) },
                singleLine = true,
                isError = usernameError != LoginError.NONE,
                supportingText = {
                    when (val error = usernameError) {
                        LoginError.REQUIRED_FIELD -> Text(
                            text = stringResource(R.string.error_required_field),
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = robotoFontFamily
                        )
                        LoginError.WRONG_EMAIL -> Text(
                            text = stringResource(R.string.error_wrong_username),
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = robotoFontFamily
                        )
                        LoginError.UNKNOWN -> Text(
                            text = stringResource(R.string.error_unknown),
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = robotoFontFamily
                        )
                        else -> { /* nada a exibir */ }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )



            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.password = it },
                label = { Text(stringResource(R.string.password_input_label), fontFamily = robotoFontFamily) },
                placeholder = { Text(stringResource(R.string.password_input_placeholder), fontFamily = robotoFontFamily) },
                singleLine = true,
                isError = passwordError != LoginError.NONE,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                supportingText = {
                    when (val error = viewModel.passwordError) {
                        LoginError.REQUIRED_FIELD -> Text(
                            text = stringResource(R.string.error_required_field),
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = robotoFontFamily
                        )
                        LoginError.WRONG_PASSWORD -> Text(
                            text = stringResource(R.string.error_wrong_password),
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = robotoFontFamily
                        )
                        LoginError.UNKNOWN -> Text(
                            text = stringResource(R.string.error_unknown),
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = robotoFontFamily
                        )
                        else -> { /* nada a exibir */ }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.performLogin() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = !isLoading,
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B3A2E))
            ) {
                Text(stringResource(R.string.enter_button_label), color = Color.White, fontFamily = robotoFontFamily)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.login_forget_password_text),
                modifier = Modifier.clickable(onClick = onForgetPasswordButtonClick),
                color = Color(0xFF8B3A2E),
                style = TextStyle(fontSize = 14.sp),
                fontFamily = robotoFontFamily
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Text(stringResource(R.string.login_register_text), fontFamily = robotoFontFamily)
                Text(
                    stringResource(R.string.login_register_clickable_text),
                    color = Color(0xFF8B3A2E),
                    modifier = Modifier.clickable(onClick = onRegisterButtonClick),
                    fontWeight = FontWeight.Bold,
                    fontFamily = robotoFontFamily
                )
            }
        }
        if (isLoading)
            LoadingDialog()

        if (isLoginSuccessful) {
            onSuccessfulLogin()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    AtividadeAvaliativa1Theme {
        LoginScreen()
    }
}