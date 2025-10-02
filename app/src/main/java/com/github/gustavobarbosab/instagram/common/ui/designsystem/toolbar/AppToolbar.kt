package com.github.gustavobarbosab.instagram.common.ui.designsystem.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramTheme

private const val MAX_HEIGHT = 56
private const val MIN_ICON_WIDTH = 56

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
    Row(
        modifier = modifier.height(MAX_HEIGHT.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(1f)
                .widthIn(min = MIN_ICON_WIDTH.dp),
            contentAlignment = Alignment.Center,
            content = startContent
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
        Box(
            modifier = Modifier
                .fillMaxHeight(1f)
                .widthIn(min = MIN_ICON_WIDTH.dp),
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
        startContent = {
            startIcon?.let {
                ToolbarIcon(
                    onClick = startIconClick,
                    painter = it.painter,
                    contentDescription = it.contentDescription,
                )
            }
        },
        endContent = {
            endIcon?.let {
                ToolbarIcon(
                    onClick = endIconClick,
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
                modifier = Modifier.fillMaxWidth(),
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleMedium
            )
        }
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
    firstEndIcon: ToolbarIcons.Right,
    firstEndIconClick: () -> Unit,
    secondEndIcon: ToolbarIcons.Right,
    secondEndIconClick: () -> Unit,
) {
    AppToolbar(
        modifier = modifier,
        startContent = {
            startIcon?.let {
                ToolbarIcon(
                    onClick = startIconClick,
                    painter = it.painter,
                    contentDescription = it.contentDescription,
                )
            }
        },
        endContent = {
            Row {
                ToolbarIcon(
                    onClick = firstEndIconClick,
                    painter = firstEndIcon.painter,
                    contentDescription = firstEndIcon.contentDescription,
                )
                ToolbarIcon(
                    onClick = secondEndIconClick,
                    painter = secondEndIcon.painter,
                    contentDescription = secondEndIcon.contentDescription,
                )
            }
        },
        content = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleSmall
            )
        }
    )
}

@Composable
private fun ToolbarIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    painter: Painter,
    contentDescription: String
) {
    IconButton(
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(
            modifier = modifier,
            painter = painter,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}


@ThemePreview
@Composable
private fun AppToolbarPreviewText() {
    InstagramTheme {
        AppToolbar(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            text = "Instagram",
            startIcon = ToolbarIcons.Back,
            endIcon = ToolbarIcons.Chat
        )
    }
}

@ThemePreview
@Composable
private fun AppToolbarPreview() {
    InstagramTheme {
        AppToolbar(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            text = "Instagram",
            startIcon = ToolbarIcons.Back,
            firstEndIcon = ToolbarIcons.Hearth,
            firstEndIconClick = {},
            secondEndIcon = ToolbarIcons.Chat,
            secondEndIconClick = {}
        )
    }
}