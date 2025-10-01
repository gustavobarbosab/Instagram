package com.github.gustavobarbosab.instagram.common.ui.designsystem.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.github.gustavobarbosab.instagram.R

sealed class ToolbarIcons {
    abstract val painter: Painter
        @Composable get
    abstract val contentDescription: String
        @Composable get

    interface Left {
        val painter: Painter
            @Composable get
        val contentDescription: String
            @Composable get
    }

    interface Right {
        val painter: Painter
            @Composable get
        val contentDescription: String
            @Composable get
    }

    data object Back : ToolbarIcons(), Left {
        override val painter: Painter
            @Composable
            get() = rememberVectorPainter(Icons.AutoMirrored.Default.ArrowBack)

        override val contentDescription: String
            @Composable
            get() = stringResource(R.string.toolbar_icons_back_content_description)
    }

    data object Close : ToolbarIcons(), Left, Right {
        override val painter: Painter
            @Composable
            get() = rememberVectorPainter(Icons.Filled.Close)

        override val contentDescription: String
            @Composable
            get() = stringResource(R.string.toolbar_icons_close_content_description)
    }

    data object Chat : ToolbarIcons(), Right {
        override val painter: Painter
            @Composable
            get() = rememberVectorPainter(Icons.Filled.Email)

        override val contentDescription: String
            @Composable
            get() = stringResource(R.string.toolbar_icons_chat_content_description)
    }

    data object Logo : ToolbarIcons() {
        override val painter: Painter
            @Composable
            get() = rememberVectorPainter(Icons.Default.AccountBox)

        override val contentDescription: String
            @Composable
            get() = stringResource(R.string.toolbar_icons_logo_content_description)
    }
}
