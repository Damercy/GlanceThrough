package dev.dayaonweb.glancesample


import android.content.Intent
import android.net.Uri
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
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.glance.*
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.text.FontWeight
import dev.dayaonweb.glancesample.ui.theme.GlanceSampleTheme
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import java.util.*

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
        val isUserLoggedIn = rememberSaveable { false }

        if (isUserLoggedIn)
            ExampleAppWidgetContent()
        else
            LoginContent()
    }

}

@Composable
fun LoginContent() {
    Column(
        modifier = GlanceModifier
            .appWidgetBackground()
            .fillMaxSize()
            .background(ImageProvider(R.drawable.bg_rounded_24))
            .padding(8.dp),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally
    ) {
        Text(
            modifier = GlanceModifier
                .padding(bottom = 8.dp),
            text = "Kindly login to your account to view latest tickets.",
            style = TextStyle(
                color = ColorProvider(Color.Black),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        )
        Button(
            modifier = GlanceModifier
                .wrapContentWidth(),
            text = "Login",
            onClick = actionStartActivity(Intent(LocalContext.current, MainActivity::class.java)),
        )
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
    val location = rememberSaveable {
        23.520445f to 87.311920f
    }
    val locationUri =
        String.format(Locale.getDefault(), "geo:%f,%f", location.first, location.second)
    val openMapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(locationUri))
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
        Row(verticalAlignment = Alignment.Vertical.CenterVertically) {
            Text(
                modifier = GlanceModifier
                    .padding(bottom = 2.dp)
                    .clickable(actionStartActivity(openMapIntent)),
                text = "Haveli Road, Mumbai", style = TextStyle(
                    color = ColorProvider(Color.Black),
                    fontSize = 10.sp,
                ),
                maxLines = 1
            )
            Image(
                provider = ImageProvider(R.drawable.ic_outline_location),
                contentDescription = "Event location",
                modifier = GlanceModifier
                    .size(12.dp)
                    .clickable(actionStartActivity(openMapIntent))
            )
        }
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