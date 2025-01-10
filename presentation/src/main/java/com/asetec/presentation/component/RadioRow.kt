package com.asetec.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RadioRow(
    yesORNo: List<String>
) {

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(yesORNo[0])
    }

    Row {
        yesORNo.forEach { text ->
            Row (
                modifier = Modifier
                    .width(120.dp)
                    .padding(top = 28.dp, start = 12.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
            ) {
                RadioButton(
                    selected = ( text == selectedOption ),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF2377f9)
                    )
                )

                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}