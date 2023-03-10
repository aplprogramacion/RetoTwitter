package com.apl.retotwitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apl.retotwitter.ui.theme.RetoTwitterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetoTwitterTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF161D26))
                ) {
                    TwitterCard()
                    TuitDivider()
                    TwitterCard()
                }
            }
        }
    }
}

@Preview
@Composable
fun TwitterCard() {
    var chat by remember { mutableStateOf(false) }
    var rt by remember { mutableStateOf(false) }
    var like by remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF161D26))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier
                .clip(
                    CircleShape,
                )
                .size(60.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextTitle("Antonio", Modifier.padding(end = 8.dp))
                DefaultText("@AntonioDev", Modifier.padding(end = 8.dp))
                DefaultText("1h", Modifier.padding(end = 8.dp))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painterResource(id = R.drawable.ic_dots),
                    contentDescription = "",
                    tint = Color.White
                )
            }
            TextBody(
                "Lorem ipsum dolor sit amet," +
                        " consectetur adipiscing elit. Sed euismod," +
                        " nunc vel tincidunt lacinia, nunc nisl aliquam nisl," +
                        " nec lacinia nisl nisl nec nunc. Sed euismod," +
                        " nunc vel tincidunt lacinia, nunc nisl aliquam nisl, " +
                        " nec lacinia nisl nisl nec nunc."
            )
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10)),
                contentScale = ContentScale.Crop
            )

            Row(Modifier.padding(top = 16.dp)) {
                SocilaIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_chat),
                            "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_chat_filled),
                            "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    isSelected = chat
                ) {
                    chat = !chat
                }

                SocilaIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_rt),
                            "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_rt),
                            "",
                            tint = Color(0xFF00FF27)
                        )
                    },
                    isSelected = rt
                ) {
                    rt = !rt
                }

                SocilaIcon(
                    modifier = Modifier.weight(1f),
                    unselectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_like),
                            "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_like_filled),
                            "",
                            tint = Color(0xFFFF0000)
                        )
                    },
                    isSelected = like
                ) {
                    like = !like
                }


            }
        }
    }
}

@Composable
fun SocilaIcon(
    modifier: Modifier,
    unselectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val defaultValue = 1

    Row(
        modifier = modifier.clickable { onItemSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            selectedIcon()
        } else {
            unselectedIcon()
        }
        Text(
            text = if (isSelected) (defaultValue + 1).toString() else defaultValue.toString(),
            color = Color(0xFF7E8B98),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun TextBody(title: String, modifier: Modifier = Modifier) {
    Text(text = title, color = Color.White, modifier = modifier)
}

@Composable
fun TextTitle(title: String, modifier: Modifier = Modifier) {
    Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold, modifier = modifier)
}

@Composable
fun DefaultText(title: String, modifier: Modifier = Modifier) {
    Text(text = title, color = Color.Gray, modifier = modifier)
}

@Composable
fun TuitDivider() {
    Divider(
        Modifier
            .padding(top = 4.dp)
            .height(0.5.dp)
            .fillMaxWidth(),
        color = Color(0xFF7E8B98)
    )
}