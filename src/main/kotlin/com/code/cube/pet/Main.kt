package com.code.cube.pet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.animatedimage.*

private val actions = mapOf(
    "喝水" to "023b5bb5c9ea15ce6fbb2d66b1003af33a87b20c.gif",
    "啊~,我死了~" to "2cf5e0fe9925bc31c739ccf759df8db1cb13707d.gif",
    "打扫" to "37d3d539b6003af3673f110f322ac65c1138b6dc.gif",
    "求包养" to "4034970a304e251f2df62997a086c9177f3e5358.gif",
    "贴贴" to "79f0f736afc37931f03c4b46ecc4b74543a9110d.gif",
    "切~" to "7a899e510fb30f2467e46253cf95d143ad4b0367.gif",
    "舔爪子" to "7af40ad162d9f2d3706be980aeec8a136327cc63.gif",
    "(～ o ～)~zZ" to "7dd98d1001e939015ce640517cec54e737d196fd.gif",
    "烦躁" to "8c1001e93901213fc65683b353e736d12f2e957c.gif",
    "趴着" to "a71ea8d3fd1f413465268b2e221f95cad1c85e49.gif",
    "抱枕" to "b999a9014c086e063d4ef22405087bf40bd1cbff.gif",
    "打点滴" to "e824b899a9014c083239a6f50d7b02087bf4f464.gif",
    "思考..." to "e850352ac65c1038cc7e4092b5119313b07e895a.gif",
)

fun main() = application {

    val icon = painterResource("app_icon.svg")
    var animatedImage: AnimatedImage? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()

    Tray(
        icon = icon,
        menu = {
            Item("ヾ(￣▽￣)Bye~Bye~", onClick = ::exitApplication)
            for (entry in actions.entries) {
                Item(entry.key, onClick = {
                    coroutineScope.launch {
                        animatedImage = null
                        animatedImage = try {
                            loadResourceAnimatedImage(entry.value)
                        } catch (e: Exception) {
                            null
                        }
                    }
                })
            }
        }
    )

    Window(
        title = "罗小黑",
        onCloseRequest = ::exitApplication,
        state = WindowState(width = 100.dp, height = 100.dp, position = WindowPosition(Alignment.Center)),
        undecorated = true,
        resizable = false,
        transparent = true,
        icon = icon
    ) {
        WindowDraggableArea {
            LaunchedEffect(null) {
                animatedImage = try {
                    loadResourceAnimatedImage("b999a9014c086e063d4ef22405087bf40bd1cbff.gif")
                } catch (e: Exception) {
                    null
                }
            }
            Image(
                bitmap = animatedImage?.animate() ?: ImageBitmap.Blank,
                contentDescription = null,
                Modifier.size(100.dp)
            )
        }
    }
}
