package com.github.gustavobarbosab.instagram.common.ui.designsystem.components.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.github.gustavobarbosab.instagram.R

sealed class AppToolbarIcons {
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

    data object Back : AppToolbarIcons(), Left {
        override val painter: Painter
            @Composable
            get() = rememberVectorPainter(Icons.AutoMirrored.Default.ArrowBack)

        override val contentDescription: String
            @Composable
            get() = stringResource(R.string.toolbar_icons_back_content_description)
    }

    data object Close : AppToolbarIcons(), Left, Right {
        override val painter: Painter
            @Composable
            get() = rememberVectorPainter(Icons.Filled.Close)

        override val contentDescription: String
            @Composable
            get() = stringResource(R.string.toolbar_icons_close_content_description)
    }

    data object Chat : AppToolbarIcons(), Right {
        override val painter: Painter
            @Composable
            get() = rememberVectorPainter(Icons.Outlined.Email)

        override val contentDescription: String
            @Composable
            get() = stringResource(R.string.toolbar_icons_chat_content_description)
    }

    data object Hearth : AppToolbarIcons(), Right {
        override val painter: Painter
            @Composable
            get() = rememberVectorPainter(Icons.Outlined.FavoriteBorder)

        override val contentDescription: String
            @Composable
            get() = stringResource(R.string.toolbar_icons_hearth_content_description)
    }

    data object Logo : AppToolbarIcons() {
        override val painter: Painter
            @Composable
            get() = rememberVectorPainter(Icons.Default.AccountBox)

        override val contentDescription: String
            @Composable
            get() = stringResource(R.string.toolbar_icons_logo_content_description)
    }
}
