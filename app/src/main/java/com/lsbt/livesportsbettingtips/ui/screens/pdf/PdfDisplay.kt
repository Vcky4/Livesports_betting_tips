package com.lsbt.livesportsbettingtips.ui.screens.pdf

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.ui.theme.CardColor2
import com.ramcosta.composedestinations.annotation.Destination
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState


@Destination
@Composable
fun PdfDisplay(asset: Int) {
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Asset(R.raw.etietop),
        isZoomEnable = true
    )

    LaunchedEffect(key1 = pdfState.error) {
        pdfState.error?.let {
            // Show error
        }
    }

    VerticalPDFReader(
        state = pdfState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = CardColor2)
    )
}