import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.byd.R
@Preview
@Composable
fun App() {
    Scaffold(
        topBar = {
            TopAppBar()(
                 AppTitle()
            )
        },
        bottomBar = { /* Optional */ }
    ) { innerPadding ->  // This is important!
        // Content goes here, using the padding if needed
        Text(
            text = "This is my app's content",
            modifier = Modifier.padding(innerPadding)
        )
    }
}



@Preview
@Composable
 fun AppTitle() {
     Box()
     {
         Image(
             painter = painterResource(id = R.drawable.facebook),
             contentDescription = "coffee master logo"
         )


     }

}