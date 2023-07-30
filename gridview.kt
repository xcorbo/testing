package xyz.corbolabs.concretecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.corbolabs.concretecalculator.ui.theme.ConcreteCalculatorTheme

// Init Vals
const val PREVIEW_WIDTH_DP = 720
const val PREVIEW_HEIGHT_DP = 1280


class MainActivity : ComponentActivity() {
    // General UI declarations?
    val headerText = "What are we looking to build today?"

    // Data class for each card
    data class ImageCard(
        val title: String,
        val thumbnail: Int,
        val description: String
    )

    // Your list of image cards
    val imageCards = listOf(
        ImageCard("Slabs", R.drawable.thumba1, "Slabs, Square footings or Walls"),
        ImageCard("Slabs", R.drawable.thumba2, "Rectangular tube slabs"),
        ImageCard("Curbs", R.drawable.thumba3, "Curbs or Gutter barriers"),
        ImageCard("Cylinder", R.drawable.thumba4, "Holes, Columns or Round footings"),
        ImageCard("Cylinder", R.drawable.thumba5, "Circular Slabs or Tubes"),
        ImageCard("Stairs", R.drawable.thumba6, "Filled stairs"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConcreteCalculatorTheme() {
                Surface() {
                    Column(modifier = Modifier.fillMaxSize()) {
                        headerBar(text = headerText)
                        CardGrid(imageCards = imageCards)
                    }
                }
            }
        }
    }

    @Composable
    fun CardGrid(imageCards: List<ImageCard>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            val rows = imageCards.chunked(3)
            items(rows) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    rowItems.forEach { imageCard ->
                        ImageCardTile(imageCard)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    @Composable
    fun ImageCardTile(imageCard: ImageCard) {
        Box(
            modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(10.dp))
                .padding(12.dp)
                .size(100.dp, 200.dp)
        ) {
            // Display the title, description, and thumbnail
            Text(text = imageCard.title, fontSize = 16.sp)
            // Load the thumbnail image
            Image(
                painter = painterResource(id = imageCard.thumbnail),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = imageCard.description,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }

    @Composable
    fun headerBar(
        text: String,
        modifier: Modifier = Modifier,
        backgroundColor: Color = Color(44, 44, 44),
        textColor: Color =  Color.Yellow,
    ){
        Box(
            modifier = modifier
                .background(backgroundColor, RoundedCornerShape(10.dp))
                .padding(12.dp)
                .fillMaxWidth()
        ){
            Text(
                text = text,
                color = textColor,
                fontSize = 18.sp,
                modifier = Modifier.align(alignment = Alignment.CenterEnd)
            )
        }
    }
}
