package com.github.gustavobarbosab.instagram.common.ui.designsystem.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramTheme

private const val MAX_HEIGHT = 56
private const val MAX_ICON_WIDTH = 56

/**
 * This Composable aims to create a generic toolbar, allowing contents on the left, right and
 * center of the parent Composable.
 */
@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    startContent: @Composable BoxScope.() -> Unit = { },
    endContent: @Composable BoxScope.() -> Unit = { },
    content: @Composable BoxScope.() -> Unit,
) {
    Row(modifier) {
        Box(
            Modifier.size(MAX_ICON_WIDTH.dp),
            contentAlignment = Alignment.Center,
            content = startContent
        )
        Box(
            modifier = Modifier
                .height(MAX_HEIGHT.dp)
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
        Box(
            Modifier.size(MAX_ICON_WIDTH.dp),
            contentAlignment = Alignment.Center,
            content = endContent
        )
    }
}

/**
 * This Composable aims to create a generic toolbar, which applies ToolbarIcons to the left and right
 * side of the component.
 */
@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    startIcon: ToolbarIcons.Left? = null,
    startIconClick: () -> Unit = {},
    endIcon: ToolbarIcons.Right? = null,
    endIconClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    AppToolbar(
        modifier = modifier,
        endContent = {
            endIcon?.let {
                Icon(
                    modifier = Modifier
                        .clickable(
                            onClick = endIconClick,
                            onClickLabel = it.contentDescription
                        ),
                    painter = it.painter,
                    contentDescription = it.contentDescription,
                )
            }
        },
        startContent = {
            startIcon?.let {
                Icon(
                    modifier = Modifier
                        .clickable(
                            onClick = startIconClick,
                            onClickLabel = it.contentDescription
                        ),
                    painter = it.painter,
                    contentDescription = it.contentDescription,
                )
            }
        },
        content = content
    )
}

/**
 * This Composable aims to show a screen title, allowing to apply buttons on the left and right.
 */
@Composable
fun AppToolbar(
    text: String,
    modifier: Modifier = Modifier,
    startIcon: ToolbarIcons.Left? = null,
    startIconClick: () -> Unit = {},
    endIcon: ToolbarIcons.Right? = null,
    endIconClick: () -> Unit = {},
) {
    AppToolbar(
        modifier = modifier,
        endIcon = endIcon,
        endIconClick = endIconClick,
        startIconClick = startIconClick,
        startIcon = startIcon,
        content = {
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}


/**
 * This Composable aims to create a toolbar with the app logo.
 */
@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    startIcon: ToolbarIcons.Left? = null,
    startIconClick: () -> Unit = {},
    endIcon: ToolbarIcons.Right? = null,
    endIconClick: () -> Unit = {},
) {
    AppToolbar(
        modifier = modifier,
        endIcon = endIcon,
        endIconClick = endIconClick,
        startIconClick = startIconClick,
        startIcon = startIcon,
        content = {
            val icon = ToolbarIcons.Logo
            Icon(
                painter = icon.painter,
                contentDescription = icon.contentDescription,
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AppToolbarPreviewText() {
    InstagramTheme {
        AppToolbar(
            text = "Testing",
            startIcon = ToolbarIcons.Back,
            endIcon = ToolbarIcons.Chat
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppToolbarPreviewIcon() {
    InstagramTheme {
        AppToolbar(
            content = {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "",
                )
            }
        )
    }
}