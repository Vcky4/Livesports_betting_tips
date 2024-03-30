package com.lsbt.livesportsbettingtips.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun Button2(
    modifier: Modifier = Modifier,
    text: String,
    processing: Boolean = false,
    enabled: Boolean = true,
    color: Color = Color.Black,
    contentColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        enabled = !processing && enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = contentColor,
            disabledContainerColor = color.copy(alpha = 0.5f),
            disabledContentColor = Color.White
        ),
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
    ) {
        if (processing) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                strokeWidth = 2.dp,
            )
        } else {
            Text(text = text, style = MaterialTheme.typography.bodyLarge, color = Color.White)
        }
    }
}