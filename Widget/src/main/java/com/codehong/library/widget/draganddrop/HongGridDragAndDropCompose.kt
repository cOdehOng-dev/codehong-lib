package com.codehong.library.widget.draganddrop

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongSpacing

@Composable
fun HongGridDragAndDropCompose(
    option: HongGridDragAndDropOption,
    subContent: @Composable () -> Unit = {},
    content: @Composable (Any) -> Unit
) {
    var isEditMode by remember { mutableStateOf(false) }

    BackHandler {
        if (isEditMode) {
            isEditMode = false
        } else {
            option.onBackClick()
        }
    }
    LongPressDraggable {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .hongBackground(
                    color = option.backgroundColorHex
                )
                .hongSpacing(option.padding),
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = option.itemList
                ) { food ->
                    DragAndDropItem(
                        item = food,
                        isShaking = isEditMode,
                        onLongClick = {
                            isEditMode = true
                        },
                        onClick = {
                            if (!isEditMode) {
                                option.onItemClick()
                            }
                        }
                    ) {
                        content(food)
                    }
                }
            }
            subContent()
        }
    }

}