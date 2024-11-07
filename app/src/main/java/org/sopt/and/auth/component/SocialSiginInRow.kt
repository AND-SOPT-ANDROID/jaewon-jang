package org.sopt.and.auth.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SocialLoginIcon(iconResId: Int, contentDescription: String, onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            tint = Color.Unspecified,
            modifier = Modifier.size(48.dp)
        )
    }
}
