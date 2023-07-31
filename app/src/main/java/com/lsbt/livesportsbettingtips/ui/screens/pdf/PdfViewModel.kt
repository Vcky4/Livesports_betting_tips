package com.lsbt.livesportsbettingtips.ui.screens.pdf

//class PdfViewModel(application: Application) : AndroidViewModel(application) {
//    // The list of PDFs in our `assets` folder.
//    private val assetsToLoad = listOf(
//        "Annotations.pdf",
//        "Aviation.pdf",
//        "Calculator.pdf",
//        "Classbook.pdf",
//        "Construction.pdf",
//        "Student.pdf",
//        "Teacher.pdf",
//        "The-Cosmic-Context-for-Life.pdf"
//    )
//
//    private val mutableState = MutableStateFlow(State())
//    val state: StateFlow<State> = mutableState
//
//    private fun <T> MutableStateFlow<T>.mutate(mutateFn: T.() -> T) {
//        value = value.mutateFn()
//    }
//
//    // Launching in Dispatcher.IO prevents the UI from janking.
//// Using the `viewModelScope` to launch ensures that the lifecycle
//// is tied to the `ViewModel` itself.
//    fun loadPdfs() = viewModelScope.launch(Dispatchers.IO) {
//
//        // Mutate the state to indicate that we're now loading.
//        mutableState.mutate { copy(loading = true) }
//
//        val context = getApplication<Application>().applicationContext
//
//        // Each map here is running a suspended function.
//        val pdfDocuments = assetsToLoad
//            .map { extractPdf(context, it) }
//            .map { it.toUri to loadPdf(context, it.toUri()) }
//            .toMap()
//
//        // Stop loading and add the PDFs to the state.
//        mutableState.mutate {
//            copy(
//                loading = false,
//                documents = pdfDocuments
//            )
//        }
//    }
//
//    private fun extractPdf(context: Context?, it: String) =
//        context?.assets?.open(it)?.use { inputStream ->
//            context.cacheDir.resolve(it).outputStream().use { outputStream ->
//                inputStream.copyTo(outputStream)
//            }
//        }
//
//    private suspend fun loadPdf(context: Context, uri: Uri) = suspendCoroutine<PdfDocument> { continuation ->
//        PdfDocumentLoader
//            .openDocumentAsync(context, uri)
//            .subscribe(continuation::resume, continuation::resumeWithException)
//    }
//}