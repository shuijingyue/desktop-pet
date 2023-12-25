package com.code.cube.pet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.animatedimage.*

private sealed interface LoadState<T> {
    class Loading<T> : LoadState<T>
    data class Success<T>(val data: T) : LoadState<T>
    data class Error<T>(val error: Exception) : LoadState<T>
}

private val actions = mapOf(
    "喝水" to "023b5bb5c9ea15ce6fbb2d66b1003af33a87b20c.gif",
    "跌倒" to "03087bf40ad162d991c2ec4c16dfa9ec8a13cdb6.gif",
    "啊~,我死了~" to "2cf5e0fe9925bc31c739ccf759df8db1cb13707d.gif",
    "打扫" to "37d3d539b6003af3673f110f322ac65c1138b6dc.gif",
    "求包养" to "4034970a304e251f2df62997a086c9177f3e5358.gif",
    "贴贴" to "79f0f736afc37931f03c4b46ecc4b74543a9110d.gif",
    "切~" to "7a899e510fb30f2467e46253cf95d143ad4b0367.gif",
    "舔爪子" to "7af40ad162d9f2d3706be980aeec8a136327cc63.gif",
    "睡觉" to "7dd98d1001e939015ce640517cec54e737d196fd.gif",
    "生闷气" to "8c1001e93901213fc65683b353e736d12f2e957c.gif",
    "趴着" to "a71ea8d3fd1f413465268b2e221f95cad1c85e49.gif",
    "抱枕" to "b999a9014c086e063d4ef22405087bf40bd1cbff.gif",
    "打点滴" to "e824b899a9014c083239a6f50d7b02087bf4f464.gif",
    "思考..." to "e850352ac65c1038cc7e4092b5119313b07e895a.gif",
)

fun main() = application {
    val icon = painterResource("app_icon.png")
    var url by remember { mutableStateOf("2cf5e0fe9925bc31c739ccf759df8db1cb13707d.gif") }
    var state: LoadState<AnimatedImage>  by remember { mutableStateOf(LoadState.Loading()) }

    Tray(
        icon = icon,
        menu = {
            Item("ヾ(￣▽￣)Bye~Bye~", onClick = ::exitApplication)
            for (entry in actions.entries) {
                Item(entry.key, onClick = {
                    url = entry.value
                    println(entry.value)
                })
            }
        }
    )

    Window(
        onCloseRequest = ::exitApplication,
        undecorated = true,
        resizable = false,
        transparent = true,
    ) {
        WindowDraggableArea {
                LaunchedEffect(url) {
                    state = try {
                        LoadState.Success(loadResourceAnimatedImage(url))
                    } catch (e: Exception) {
                        LoadState.Error(e)
                    }
                }

                when (val animatedImage = state) {
                    is LoadState.Success -> Image(
                        bitmap = animatedImage.data.animate(),
                        contentDescription = null,
                        Modifier.size(100.dp)
                    )
                    else -> {}
                }
            }
        }
    }
