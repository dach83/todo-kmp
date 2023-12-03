package presentation.todo.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateRectAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.TodoEntity
import domain.model.TodoRole

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TodoItem(
    role: TodoRole,
    todo: TodoEntity,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (todo.done) {
            Color(0xff24d65f)
        } else {
            Color(0xffff6363)
        },
        animationSpec = tween(500)
    )
    val shape = role.toShape(16.dp, 4.dp)

    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .animateItemPlacement(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(color)
                .clickable {
                    onClick()
                }
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primary)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    AnimatedVisibility(
                        visible = todo.done,
                        enter = scaleIn() + fadeIn(),
                        exit = scaleOut() + fadeOut()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = color
                        )
                    }
                }
                Row {
                    AnimatedVisibility(
                        visible = !todo.done,
                        enter = scaleIn() + fadeIn(),
                        exit = scaleOut() + fadeOut()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = color
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = todo.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Text(
                    text = todo.subtitle,
                    fontSize = 12.sp,
                    color = Color(0xffebebeb)
                )
            }
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primary)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        onDelete()
                    }
                )
            }
        }
    }
}

@Composable
private fun TodoRole.toShape(outerCornerSize: Dp, innerCornerSize: Dp): Shape {
    val (outerCornerSizePx, innerCornerSizePx) = LocalDensity.current.run {
        outerCornerSize.toPx() to innerCornerSize.toPx()
    }

    val targetRect = remember(this, outerCornerSize, innerCornerSize) {
        when (this) {
            TodoRole.TOP -> Rect(
                outerCornerSizePx,
                outerCornerSizePx,
                innerCornerSizePx,
                innerCornerSizePx
            )

            TodoRole.BOTTOM -> Rect(
                innerCornerSizePx,
                innerCornerSizePx,
                outerCornerSizePx,
                outerCornerSizePx
            )

            TodoRole.MIDDLE -> Rect(
                innerCornerSizePx,
                innerCornerSizePx,
                innerCornerSizePx,
                innerCornerSizePx
            )

            TodoRole.SINGLE -> Rect(
                outerCornerSizePx,
                outerCornerSizePx,
                outerCornerSizePx,
                outerCornerSizePx
            )
        }
    }
    val animatedRect by animateRectAsState(targetRect)

    return RoundedCornerShape(
        animatedRect.left,
        animatedRect.top,
        animatedRect.right,
        animatedRect.bottom
    )
}
