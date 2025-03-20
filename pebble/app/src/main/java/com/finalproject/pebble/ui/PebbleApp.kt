
import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil.compose.rememberAsyncImagePainter
import com.finalproject.pebble.R
import com.finalproject.pebble.data.Sample
import com.finalproject.pebble.data.SampleDataSource
import com.finalproject.pebble.ui.AddViewModel
import com.finalproject.pebble.ui.DetailViewModel
import com.finalproject.pebble.ui.ListViewModel
import com.finalproject.pebble.ui.theme.PebbleTheme
import kotlinx.serialization.Serializable
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PebbleAppBar(
    title: String,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = false,
    onUpClick: () -> Unit = { },
) {
    TopAppBar(
        title = { Text(
            text = title,
            fontFamily = FontFamily(Font(R.font.libre_baskerville_bold, FontWeight.Bold)),
            fontSize = 25.sp
        ) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onUpClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                }
            }
        }
    )
}

@Composable
fun ListScreen(
    onImageClick: (Sample) -> Unit,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    viewModel: ListViewModel = viewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        PebbleAppBar(
            title = "pebble",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = { Spacer(modifier = Modifier.height(56.dp)) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(16.dp),
                    modifier = modifier.padding(innerPadding),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(viewModel.sampleList) { sample ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable(
                                    onClick = { onImageClick(sample) },
                                    onClickLabel = "Select the pet"
                                )
                        ) {
                            Image(
                                painter = painterResource(id = sample.imageId),
                                contentDescription = "${sample.id} ${sample.desc}",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .clickable(
                                        onClick = { onImageClick(sample) },
                                        onClickLabel = "select sample"
                                    )
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .background(Color.Black.copy(alpha = 0.6f))
                                    .padding(2.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                        .horizontalScroll(rememberScrollState())
                                ) {

                                    sample.tags.forEach { tag ->
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    Color.Gray.copy(alpha = 0.6f),
                                                    RoundedCornerShape(16.dp)
                                                )
                                                .padding(
                                                    horizontal = 5.dp,
                                                    vertical = 2.dp
                                                )
                                        ) {
                                            Text(
                                                text = tag.name,
                                                color = Color.White,
                                                fontSize = 10.sp,
                                                modifier = Modifier.padding(horizontal = 2.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                FloatingActionButton(
                    onClick = onAddClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Create Sample")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewListScreen() {
    PebbleTheme {
        ListScreen(
            onImageClick = { },
            onAddClick = { }
        )
    }
}


@Composable
fun DetailScreen(
    sampleId: Int,
    onAdoptClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(),
    onUpClick: () -> Unit = { }
) {
    val sample = viewModel.getSample(sampleId)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                PebbleAppBar(
                    title = "pebble",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    canNavigateBack = true,
                    onUpClick = onUpClick
                ) }
        ) { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(sample.imageId),
                    contentDescription = "rock",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = modifier.padding(24.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                    ) {
                        sample.tags.forEach { tag ->
                            Box(
                                modifier = Modifier
                                    .background(
                                        Color.Gray.copy(alpha = 0.6f),
                                        RoundedCornerShape(16.dp)
                                    )
                                    .padding(
                                        horizontal = 8.dp,
                                        vertical = 8.dp
                                    )
                            ) {
                                Text(
                                    text = tag.name,
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(horizontal = 5.dp)
                                )
                            }
                        }
                        Button(
                            onClick = onAdoptClick,
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White
                            ),
                        ) {
                            Text("+")

                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Text(
                            text="Description",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp)
                        )
                        Text(
                            text = sample.desc ?: "",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewDetailScreen() {
    val pet = SampleDataSource().loadSamples()[0]
    PebbleTheme {
        DetailScreen(
            sampleId = pet.id,
            onAdoptClick = { }
        )
    }
}


@Composable
fun AddScreen(
    onSave: (Context, String, Uri) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddViewModel = viewModel(),
    onUpClick: () -> Unit = { }
) {
    val description = viewModel.description.value
    val tags = viewModel.tags
    val selectedTags = remember { mutableStateListOf<String>() }
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value
    val successMessage = viewModel.successMessage.value

    val context = LocalContext.current
    val cameraPermission = Manifest.permission.CAMERA
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            Log.d("CameraCapture", "Image captured successfully: ${imageUri.value}")
        } else {
            Log.e("CameraCapture", "Image capture failed.")
        }
    }

    val file = File(context.cacheDir, "images/captured_image.jpg")
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)

    LaunchedEffect(Unit) {
        permissionLauncher.launch(cameraPermission)
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                PebbleAppBar(
                    title = "pebble",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    canNavigateBack = true,
                    onUpClick = onUpClick
                )
            }
        ) { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(Color.Black)
                        .clickable { cameraLauncher.launch(uri) }
                ) {
                    if (imageUri.value != null) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri.value),
                            contentDescription = "Captured Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text(
                            text = "Tap to Open Camera",
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = description,
                    onValueChange = { viewModel.description.value = it },
                    label = { Text("Description") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(top = 8.dp)
                ) {
                    tags.forEach { tag ->
                        Box(
                            modifier = Modifier
                                .background(
                                    if (selectedTags.contains(tag)) Color.Blue.copy(alpha = 0.7f)
                                    else Color.Gray.copy(alpha = 0.6f),
                                    RoundedCornerShape(16.dp)
                                )
                                .clickable {
                                    if (selectedTags.contains(tag)) {
                                        selectedTags.remove(tag)
                                    } else {
                                        selectedTags.add(tag)
                                    }
                                }
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = tag,
                                color = Color.White,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                        }
                    }
                    Button(
                        onClick = {}
                    ) {
                        Text("+")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (selectedTags.isNotEmpty()) {
                    Text("Selected Tags:", style = MaterialTheme.typography.titleMedium)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .padding(top = 8.dp)
                    ) {
                        selectedTags.forEach { tag ->
                            Box(
                                modifier = Modifier
                                    .background(color = Color(0xFF375F7A), RoundedCornerShape(16.dp))
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Text(tag, color = Color.White, fontSize = 15.sp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(modifier = Modifier
                    .fillMaxSize()) {
                    Button(
                        onClick = {
                            if (imageUri.value != null && description.isNotEmpty()) {
                                onSave(context, description, imageUri.value!!)
                            } else {
                                Toast.makeText(context, "Please enter text and capture an image", Toast.LENGTH_SHORT).show()
                            } }
                        ,
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                    ) {
                        Text("Save Sample")
                    }
                }
            }
        }

    }
}

/*
@Preview
@Composable
fun PreviewAddScreen() {
    PebbleTheme {
        AddScreen(
            onSave = { },
            onUpClick = { }
        )
    }
}*/

fun saveImageToCache(context: Context, bitmap: Bitmap): Uri {
    val imagesDir = File(context.cacheDir, "images")
    if (!imagesDir.exists()) {
        imagesDir.mkdirs()
    }

    val file = File(context.cacheDir, "images/captured_image.jpg")
    file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it) }
    return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
}

sealed class Routes {
    @Serializable
    data object List

    @Serializable
    data object Add

    @Serializable
    data class Detail(
        val sampleId: Int
    )

    @Serializable
    data class Adopt(
        val sampleId: Int
    )
}

@Composable
fun PebbleApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.List
    ) {
        composable<Routes.List> {
            ListScreen(
                onImageClick = { sample ->
                    navController.navigate(
                        Routes.Detail(sample.id)
                    )
                },
                onAddClick = {
                    navController.navigate(
                        Routes.Add
                    )
                }
            )
        }
        composable<Routes.Detail> { backstackEntry ->
            val details: Routes.Detail = backstackEntry.toRoute()

            DetailScreen(
                sampleId = details.sampleId,
                onAdoptClick = {
                    navController.navigate(
                        Routes.Adopt(details.sampleId)
                    )
                },
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Add> { backstackEntry ->
            val add: Routes.Add = backstackEntry.toRoute()

            AddScreen(
                onSave = { context, description, imageUri ->
                    if (description != "") {
                        // Handle saving the data, you can save to a database or SharedPreferences
                        saveData(context, description.toString(), imageUri)
                        // Navigate back after saving
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Please make sure both description and image are provided", Toast.LENGTH_SHORT).show()
                    }
                },
                onUpClick = {
                    navController.navigateUp()
                }

            )
        }
    }
}

fun saveData(context: Context, description: String, imageUri: Uri) {
    val sharedPref = context.getSharedPreferences("MyAppData", Context.MODE_PRIVATE)
    sharedPref.edit()
        .putString("description", description)
        .putString("imageUri", imageUri.toString())
        .apply()
}