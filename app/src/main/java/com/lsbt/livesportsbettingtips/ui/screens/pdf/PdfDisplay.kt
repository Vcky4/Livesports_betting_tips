package com.lsbt.livesportsbettingtips.ui.screens.pdf

import androidx.appcompat.app.AppCompatDelegate
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
fun PdfDisplay(trigger: String) {
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Asset(chooseDoc(trigger)),
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

fun chooseDoc(trigger: String): Int =
    when (trigger) {
        "about" -> when (AppCompatDelegate.getApplicationLocales().toLanguageTags()) {
            "fr" -> R.raw.about_fr
            "en-US", "en" -> R.raw.about_en
            "es" -> R.raw.about_es
            "pt" -> R.raw.about_pt
            else -> R.raw.about_en
        }

        "terms" -> when (AppCompatDelegate.getApplicationLocales().toLanguageTags()) {
            "fr" -> R.raw.terms_fr
            "en-US", "en" -> R.raw.terms_en
            "es" -> R.raw.terms_es
            "pt" -> R.raw.terms_pt
            else -> R.raw.terms_en
        }

        "privacy" -> when (AppCompatDelegate.getApplicationLocales().toLanguageTags()) {
            "fr" -> R.raw.privacy_fr
            "en-US", "en" -> R.raw.privacy_en
            "es" -> R.raw.privacy_es
            "pt" -> R.raw.privacy_pt
            else -> R.raw.privacy_en
        }

        else -> when (AppCompatDelegate.getApplicationLocales().toLanguageTags()) {
            "fr" -> R.raw.about_fr
            "en-US", "en" -> R.raw.about_en
            "es" -> R.raw.about_es
            "pt" -> R.raw.about_pt
            else -> R.raw.about_en
        }
    }