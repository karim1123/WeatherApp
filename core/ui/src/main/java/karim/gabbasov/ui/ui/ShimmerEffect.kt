package karim.gabbasov.ui.ui

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import karim.gabbasov.designsystem.theme.DarkShimmerColor
import karim.gabbasov.designsystem.theme.LightShimmerColor

private const val ONE_SECOND = 1000
private const val VALUE = 2

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -VALUE * size.width.toFloat(),
        targetValue = VALUE * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(ONE_SECOND)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                LightShimmerColor,
                DarkShimmerColor,
                LightShimmerColor,
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}
