package dev.dayaonweb.glancesample

import android.graphics.fonts.Font
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import dev.dayaonweb.glancesample.ui.theme.GlanceSampleTheme
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlanceSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

class ExampleAppWidgetProvider : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget
        get() = ExampleAppWidget()

}

class ExampleAppWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        ExampleAppWidgetContent()
    }

}


@Composable
fun ExampleAppWidgetContent() {
    Column(
        modifier = GlanceModifier
            .appWidgetBackground()
    ) {
        Row(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(ImageProvider(R.drawable.bg_rounded_24))
                .padding(8.dp),
            verticalAlignment = Alignment.Vertical.CenterVertically
        ) {
            QRCode()
            TicketDetails()
        }
    }
}

@Composable
fun TicketDetails() {
    Column {
        Text(
            text = "Zomaland 2022", style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = ColorProvider(Color.Black),
                fontSize = 16.sp,
            ),
            maxLines = 1
        )
        Text(
            text = "Q9C59L", style = TextStyle(
                fontSize = 8.sp
            )
        )
        Text(
            modifier = GlanceModifier
                .padding(bottom = 2.dp),
            text = "3 May '23 at 5:00 pm", style = TextStyle(
                color = ColorProvider(Color.Black),
                fontSize = 12.sp,
            ),
            maxLines = 1
        )
        Text(
            modifier = GlanceModifier
                .padding(bottom = 2.dp),
            text = "Haveli Road, Mumbai", style = TextStyle(
                color = ColorProvider(Color.Black),
                fontSize = 10.sp,
            ),
            maxLines = 1
        )
        Text(
            modifier = GlanceModifier
                .background(ImageProvider(R.drawable.bg_rounded_8))
                .padding(vertical = 2.dp, horizontal = 8.dp),
            text = "Booked", style = TextStyle(
                color = ColorProvider(R.color.light_green),
                fontSize = 8.sp,
            )
        )
    }
}

@Composable
fun QRCode() {
    Image(
        provider = ImageProvider(R.drawable.sample_qr),
        contentDescription = "Ticket QR",
        modifier = GlanceModifier.width(100.dp)
            .height(140.dp)
    )
}


@Composable
fun Greeting(name: String) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GlanceSampleTheme {
        ExampleAppWidgetContent()
    }
}