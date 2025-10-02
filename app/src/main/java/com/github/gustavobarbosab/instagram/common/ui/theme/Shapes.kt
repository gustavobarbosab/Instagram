package com.github.gustavobarbosab.instagram.common.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * ## Shape System
 *
 * ### Standard Shapes
 * - MaterialTheme.shapes.extraSmall (4dp radius)
 * - MaterialTheme.shapes.small (8dp radius)
 * - MaterialTheme.shapes.medium (12dp radius)
 * - MaterialTheme.shapes.large (16dp radius)
 * - MaterialTheme.shapes.extraLarge (28dp radius)
 *
 * ### Custom Instagram Shapes
 * - InstagramButtonShape (8dp radius)
 * - InstagramCardShape (12dp radius)
 * - InstagramTextFieldShape (8dp radius)
 * - InstagramLogoShape (20dp radius)
 * - InstagramBottomSheetShape (16dp top radius)
 * - InstagramDialogShape (16dp radius)
 *
 */
val InstagramShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

// Custom shapes for Instagram components
val InstagramButtonShape = RoundedCornerShape(8.dp)
val InstagramCardShape = RoundedCornerShape(12.dp)
val InstagramTextFieldShape = RoundedCornerShape(8.dp)
val InstagramLogoShape = RoundedCornerShape(20.dp)
val InstagramBottomSheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
val InstagramDialogShape = RoundedCornerShape(16.dp)
