package com.lsbt.livesportsbettingtips.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.data.db.models.HomeItemModel
import com.lsbt.livesportsbettingtips.ui.theme.Primary

@Composable
fun HomeItem(item: HomeItemModel, onClick: () -> Unit) {
    Card(Modifier
        .clickable { onClick.invoke() }
        .padding(vertical = 4.dp, horizontal = 4.dp)) {
        Row(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 12.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = item.image),
                contentDescription = "",
                tint = Primary,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = item.title) ,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Primary
            )
        }
    }
}
    
