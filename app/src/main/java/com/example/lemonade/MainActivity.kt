package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    Citron()
                }
            }
        }
    }
}

@Composable
fun Citron() {
    // Déclaration des images et des textes
    val images = arrayOf(
        painterResource(R.drawable.lemon_tree),
        painterResource(R.drawable.lemon_squeeze),
        painterResource(R.drawable.lemon_drink),
        painterResource(R.drawable.lemon_restart)
    )

    val texts = arrayOf(
        stringResource(R.string.lemon_tree),
        stringResource(R.string.citron),
        stringResource(R.string.verre_de_citronnade),
        stringResource(R.string.verre_vide)
    )

    // Gérer l'état pour l'index actuel
    val currentIndex = remember { mutableStateOf(0) }

    // Gérer le nombre de clics sur l'image de citron
    val squeezeClickCount = remember { mutableStateOf(0) }

    // Créer le bouton avec fond blanc
    Button(
        onClick = {
            if (currentIndex.value == 1) {
                // Si l'utilisateur est sur l'image de citron, incrémenter le nombre de clics
                squeezeClickCount.value += 1
            }


            if (currentIndex.value == 1 && squeezeClickCount.value >= 3) {
                squeezeClickCount.value = 0 // Réinitialiser le compteur de clics
                currentIndex.value =
                    (currentIndex.value + 1) % images.size
            } else if (currentIndex.value != 1) {
                // Si l'utilisateur n'est pas sur l'image de citron, changer l'image
                currentIndex.value = (currentIndex.value + 1) % images.size
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,  // Couleur de fond du bouton en blanc
            contentColor = Color.Black     // Couleur du texte du bouton en noir
        ),
        modifier = Modifier.padding(16.dp)
    ) {


        // Structure de l'UI
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Afficher l'image
            Image(painter = images[currentIndex.value], contentDescription = null)

            // Afficher le texte
            Text(text = texts[currentIndex.value], color = colorResource(id = R.color.black))

            Spacer(modifier = Modifier.height(16.dp))

            // Si l'utilisateur est sur l'image de citron, afficher combien de clics il lui reste
            if (currentIndex.value == 1 && squeezeClickCount.value < 3) {

            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        Citron()
    }
}
