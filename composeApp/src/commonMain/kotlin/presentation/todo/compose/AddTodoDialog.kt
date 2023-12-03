package presentation.todo.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import domain.model.TodoEntity

@Composable
fun AddTodoDialog(
    addTodo: (TodoEntity) -> Unit,
    onDismissRequest: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var subTitle by remember { mutableStateOf("") }
    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.primary)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Title")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    textColor = Color.White
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = subTitle,
                onValueChange = { subTitle = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Sub title")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    textColor = Color.White
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (title.isNotEmpty() && subTitle.isNotEmpty()) {
                        val todo = TodoEntity(
                            title = title,
                            subtitle = subTitle
                        )
                        addTodo(todo)
                        onDismissRequest()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Submit",
                    color = Color.White
                )
            }
        }
    }
}
