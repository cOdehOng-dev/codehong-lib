package com.codehong.lib.sample.textfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.lib.ui.SampleHeader
import com.codehong.lib.ui.SampleMenu
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.keyboard.KeyboardType
import com.codehong.library.widget.roundBackground
import com.codehong.library.widget.textfield.HongTextField
import com.codehong.library.widget.textfield.HongTextFieldRemoveButton

class SampleTextFieldActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    SampleHeader(title = "입력창")
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                        .padding(horizontal = 20.dp)
                ) {
                    MarginTopOrBottom(30)
                    SampleMenu(title = "HongTextField") {
                        HongTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            placeholder = "값을 입력해주세요."
                        ) {
                        }
                    }

                    SampleMenu(title = "키보드 Done") {
                        HongTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp)
                                .roundBackground(
                                    color = HongComposeColor(
                                        colorType = ColorType.BLACK_10
                                    ),
                                    allRadius = 50
                                )
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            placeholder = "값을 입력해주세요.",
                            keyboardType = KeyboardType.DONE
                        ) {
                        }
                    }

                    SampleMenu(title = "키보드 Go") {
                        HongTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp)
                                .roundBackground(
                                    color = HongComposeColor(
                                        colorType = ColorType.BLACK_10
                                    ),
                                    allRadius = 50
                                )
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            placeholder = "값을 입력해주세요.",
                            keyboardType = KeyboardType.GO
                        ) {
                        }
                    }

                    SampleMenu(title = "키보드 Search") {
                        HongTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp)
                                .roundBackground(
                                    color = HongComposeColor(
                                        colorType = ColorType.BLACK_10
                                    ),
                                    allRadius = 50
                                )
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            placeholder = "값을 입력해주세요.",
                            keyboardType = KeyboardType.SEARCH
                        ) {
                        }
                    }

                    SampleMenu(title = "키보드 Send") {
                        HongTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp)
                                .roundBackground(
                                    color = HongComposeColor(
                                        colorType = ColorType.BLACK_10
                                    ),
                                    allRadius = 50
                                )
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            placeholder = "값을 입력해주세요.",
                            keyboardType = KeyboardType.SEND
                        ) {
                        }
                    }

                    SampleMenu(title = "지우기 버튼이 있는 textField") {
                        HongTextFieldRemoveButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp)
                                .roundBackground(
                                    color = HongComposeColor(
                                        colorType = ColorType.BLACK_10
                                    ),
                                    allRadius = 50
                                )
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            placeholder = "값을 입력해주세요.",
                            keyboardType = KeyboardType.DONE
                        ) {
                        }
                    }
                }
            }
        }
    }
}
