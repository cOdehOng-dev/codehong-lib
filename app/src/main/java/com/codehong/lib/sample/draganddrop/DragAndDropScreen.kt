package com.codehong.lib.sample.draganddrop

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.R
import com.codehong.lib.sample.draganddrop.model.FoodItem
import com.codehong.library.widget.draganddrop.DragAndDropItemCard
import com.codehong.library.widget.draganddrop.LongPressDraggable


@Composable
fun DragAndDropScreen() {
    var isEditMode by remember { mutableStateOf(false) }

    BackHandler(
        enabled = isEditMode
    ) {
        isEditMode = false
    }


    val foodList = listOf(
        FoodItem(
            1,
            "우리동네 빵집",
            prdName = "식빵",
            2000,
            discountPer = "15",
            image = R.drawable.img_bread_1
        ),
        FoodItem(
            2,
            "빵굽는 집",
            prdName = "바게뜨",
            2500,
            discountPer = "10",
            image = R.drawable.img_bread_2
        ),
        FoodItem(
            3,
            "가나당",
            prdName = "통밀빵",
            5000,
            discountPer = "25",
            image = R.drawable.img_bread_3
        ),
        FoodItem(
            4,
            "빵굽는 나라",
            prdName = "단팥빵",
            100,
            discountPer = "3",
            image = R.drawable.img_bread_4
        ),
        FoodItem(
            5,
            "옆동네 빵집",
            prdName = "피자빵",
            35500,
            discountPer = "",
            image = R.drawable.img_bread_5
        ),
        FoodItem(
            6,
            "판교 빵집",
            prdName = "공갈빵",
            50000,
            discountPer = "96",
            image = R.drawable.img_bread_6
        ),
        FoodItem(
            7,
            "우리동네 빵집",
            prdName = "식빵",
            2000,
            discountPer = "15",
            image = R.drawable.img_bread_1
        ),
        FoodItem(
            8,
            "빵굽는 집",
            prdName = "바게뜨",
            2500,
            discountPer = "10",
            image = R.drawable.img_bread_2
        ),
        FoodItem(
            9,
            "가나당",
            prdName = "통밀빵",
            5000,
            discountPer = "25",
            image = R.drawable.img_bread_3
        ),
        FoodItem(
            10,
            "빵굽는 나라",
            prdName = "단팥빵",
            100,
            discountPer = "3",
            image = R.drawable.img_bread_4
        ),
        FoodItem(
            11,
            "옆동네 빵집",
            prdName = "피자빵",
            35500,
            discountPer = "",
            image = R.drawable.img_bread_5
        ),
        FoodItem(
            12,
            "판교 빵집",
            prdName = "공갈빵",
            50000,
            discountPer = "96",
            image = R.drawable.img_bread_6
        ),
        FoodItem(
            13,
            "우리동네 빵집",
            prdName = "식빵",
            2000,
            discountPer = "15",
            image = R.drawable.img_bread_1
        ),
        FoodItem(
            14,
            "빵굽는 집",
            prdName = "바게뜨",
            2500,
            discountPer = "10",
            image = R.drawable.img_bread_2
        ),
        FoodItem(
            15,
            "가나당",
            prdName = "통밀빵",
            5000,
            discountPer = "25",
            image = R.drawable.img_bread_3
        ),
        FoodItem(
            16,
            "빵굽는 나라",
            prdName = "단팥빵",
            100,
            discountPer = "3",
            image = R.drawable.img_bread_4
        ),
        FoodItem(
            17,
            "옆동네 빵집",
            prdName = "피자빵",
            35500,
            discountPer = "",
            image = R.drawable.img_bread_5
        ),
        FoodItem(
            18,
            "판교 빵집",
            prdName = "공갈빵",
            50000,
            discountPer = "96",
            image = R.drawable.img_bread_6
        )
    )

    LongPressDraggable {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
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
                    items = foodList
                ) { food ->
                    DragAndDropItemCard(
                        item = food,
                        isShaking = isEditMode,
                        onLongClick = {
                            isEditMode = true
                        },
                        onClick = {
                            if (!isEditMode) {
                                // 상세 페이지 이동 로직 등...
                            }
                        }
                    ) {
                        FoodItemContent(foodItem = food)
                    }
                }
            }
            CartContent()
        }

    }
}