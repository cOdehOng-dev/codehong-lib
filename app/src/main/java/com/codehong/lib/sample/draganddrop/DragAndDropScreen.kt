package com.codehong.lib.sample.draganddrop

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.R
import com.codehong.lib.sample.draganddrop.model.BreadItem
import com.codehong.library.widget.draganddrop.HongGridDragAndDropBuilder
import com.codehong.library.widget.draganddrop.HongGridDragAndDropCompose
import com.codehong.library.widget.rule.color.HongColor


@Composable
fun DragAndDropScreen(
    onFinish: () -> Unit
) {
    val breadList = listOf(
        BreadItem(1, "새벽베이커리", "우유 식빵", 3200, "10", R.drawable.img_bread_1),
        BreadItem(2, "달콤제과", "고메 버터 바게뜨", 4500, "15", R.drawable.img_bread_2),
        BreadItem(3, "건강한 밀밭", "유기농 통밀빵", 6800, "20", R.drawable.img_bread_3),
        BreadItem(4, "추억의 빵집", "수제 단팥빵", 1800, "5", R.drawable.img_bread_4),
        BreadItem(5, "치즈가득", "콤비네이션 피자빵", 3500, "30", R.drawable.img_bread_5),
        BreadItem(6, "바삭공방", "속이 꽉 찬 공갈빵", 2500, "12", R.drawable.img_bread_6),
        BreadItem(7, "빵지순례", "잠봉뵈르 샌드위치", 8500, "8", R.drawable.img_bread_1),
        BreadItem(8, "오븐의 마법", "플레인 소금빵", 2800, "40", R.drawable.img_bread_2),
        BreadItem(9, "프랑스 아침", "버터 크로와상", 3800, "25", R.drawable.img_bread_3),
        BreadItem(10, "할머니 손맛", "쫀득한 쑥 식빵", 5500, "18", R.drawable.img_bread_4),
        BreadItem(11, "치즈러버", "롤치즈 치아바타", 4200, "10", R.drawable.img_bread_5),
        BreadItem(12, "뉴욕 베이글", "어니언 베이글", 4800, "50", R.drawable.img_bread_6),
        BreadItem(13, "달콤한 오후", "초코 소라빵", 2200, "15", R.drawable.img_bread_1),
        BreadItem(14, "밀가루 공장", "무설탕 깜빠뉴", 7200, "22", R.drawable.img_bread_2),
        BreadItem(15, "로컬 베이커", "밤 가득 식빵", 6300, "10", R.drawable.img_bread_3),
        BreadItem(16, "골목 빵집", "매콤 소시지빵", 3000, "35", R.drawable.img_bread_4),
        BreadItem(17, "정성제과", "부드러운 카스테라", 5000, "7", R.drawable.img_bread_5),
        BreadItem(18, "디저트 랩", "블루베리 머핀", 3500, "12", R.drawable.img_bread_6)
    )

    // test
    HongGridDragAndDropCompose(
        option = HongGridDragAndDropBuilder()
            .itemList(breadList)
            .backgroundColor(HongColor.WHITE_100)
            .onBackClick { onFinish() }
            .onItemClick {
                Log.i("TAG", "test here onItemClick")
            }
            .applyOption(),
        subContent = {
            CartContent()
        }
    ) { bread ->
        Card(
            modifier = Modifier
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(0.dp)
                ),
            elevation = CardDefaults
                .cardElevation(10.dp),
            shape = RoundedCornerShape(0.dp),
        ) {
            BreadItemContent(breadItem = bread as BreadItem)
        }
    }

//    LongPressDraggable {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color = Color.White)
//        ) {
//            LazyVerticalGrid(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f),
//                columns = GridCells.Fixed(3),
//                contentPadding = PaddingValues(10.dp),
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(
//                    items = breadList
//                ) { food ->
//                    DragAndDropItem(
//                        item = food,
//                        isShaking = isEditMode,
//                        onLongClick = {
//                            isEditMode = true
//                        },
//                        onClick = {
//                            if (!isEditMode) {
//                                // 상세 페이지 이동 로직 등...
//                            }
//                        }
//                    ) {
//                        Card(
//                            modifier = Modifier
//                                .background(
//                                    color = Color.Transparent,
//                                    shape = RoundedCornerShape(0.dp)
//                                ),
//                            elevation = CardDefaults
//                                .cardElevation(10.dp),
//                            shape = RoundedCornerShape(0.dp),
//                        ) {
//                            BreadItemContent(breadItem = food)
//                        }
//                    }
//                }
//            }
//            CartContent()
//        }
//
//    }
}
