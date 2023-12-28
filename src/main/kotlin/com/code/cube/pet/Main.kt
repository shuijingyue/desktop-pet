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

private val actions = mutableMapOf<String, String>()

fun initializeActions() {
    for (i in 1..31) {
        val name = "$i".padStart(2, '0')
        actions.put(name, "$name.gif")
    }
}

fun main() {

    initializeActions()

    application {

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
            state = WindowState(width = 100.dp, height = 100.dp, position = WindowPosition(Alignment.TopEnd)),
            undecorated = true,
            resizable = false,
            transparent = true,
            icon = icon
        ) {
            WindowDraggableArea {
                LaunchedEffect(null) {
                    animatedImage = try {
                        loadResourceAnimatedImage("01.gif")
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
}
