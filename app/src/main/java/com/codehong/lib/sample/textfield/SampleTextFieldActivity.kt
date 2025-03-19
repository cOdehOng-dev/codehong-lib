package com.codehong.lib.sample.textfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.SampleMenu
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
                topBar = {}
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                        .padding(horizontal = 20.dp)
                ) {
                    item {
                        MarginTopOrBottom(30)
                        SampleMenu(title = "input 외부 remember") {
                            var inputText by rememberSaveable { mutableStateOf("") }
                            HongTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .roundBackground(
                                        color = HongComposeColor(
                                            type = ColorType.BLACK_10
                                        ),
                                        allRadius = 50
                                    )
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                placeholder = "값을 입력해주세요.",
                                inputText = inputText
                            ) { trackingText ->
                                inputText = trackingText
                            }
                        }
                    }

                    item {
                        SampleMenu(title = "input 내부 remember") {
                            HongTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .roundBackground(
                                        color = HongComposeColor(
                                            type = ColorType.BLACK_10
                                        ),
                                        allRadius = 50
                                    )
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                placeholder = "값을 입력해주세요."
                            ) { trackingText ->

                            }
                        }
                    }

                    item {
                        SampleMenu(title = "키보드 Done") {
                            HongTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .roundBackground(
                                        color = HongComposeColor(
                                            type = ColorType.BLACK_10
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

                    item {
                        SampleMenu(title = "키보드 Go") {
                            HongTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .roundBackground(
                                        color = HongComposeColor(
                                            type = ColorType.BLACK_10
                                        ),
                                        allRadius = 50
                                    )
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                placeholder = "값을 입력해주세요.",
                                keyboardType = KeyboardType.GO
                            ) {

                            }
                        }
                    }

                    item {
                        SampleMenu(title = "키보드 Search") {
                            HongTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .roundBackground(
                                        color = HongComposeColor(
                                            type = ColorType.BLACK_10
                                        ),
                                        allRadius = 50
                                    )
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                placeholder = "값을 입력해주세요.",
                                keyboardType = KeyboardType.SEARCH
                            ) {

                            }
                        }
                    }

                    item {
                        SampleMenu(title = "키보드 Send") {
                            HongTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .roundBackground(
                                        color = HongComposeColor(
                                            type = ColorType.BLACK_10
                                        ),
                                        allRadius = 50
                                    )
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                placeholder = "값을 입력해주세요.",
                                keyboardType = KeyboardType.SEND
                            ) {

                            }
                        }
                    }

                    item {
                        SampleMenu(title = "지우기 버튼 존재(외부 input)") {
                            var inputText by rememberSaveable { mutableStateOf("") }
                            HongTextFieldRemoveButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .roundBackground(
                                        color = HongComposeColor(
                                            type = ColorType.BLACK_10
                                        ),
                                        allRadius = 50
                                    )
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                placeholder = "값을 입력해주세요.",
                                keyboardType = KeyboardType.DONE,
                                inputText = inputText,
                                removeClick = {
                                    inputText = ""
                                },
                                onTextChanged = {
                                    inputText = it
                                }
                            )
                        }
                    }

                    item {
                        SampleMenu(title = "지우기 버튼 존재(내부 input)") {
                            HongTextFieldRemoveButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .roundBackground(
                                        color = HongComposeColor(
                                            type = ColorType.BLACK_10
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

                    item {
                        SampleMenu(title = "Debounce 500ms") {
                            HongTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .roundBackground(
                                        color = HongComposeColor(
                                            type = ColorType.BLACK_10
                                        ),
                                        allRadius = 50
                                    )
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                placeholder = "값을 입력해주세요.",
                                keyboardType = KeyboardType.DONE,
                                debounceTime = 500L
                            ) {
                            }
                        }
                    }

                    item {
                        SampleMenu(title = "Debounce 500ms with remove button") {
                            HongTextFieldRemoveButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .roundBackground(
                                        color = HongComposeColor(
                                            type = ColorType.BLACK_10
                                        ),
                                        allRadius = 50
                                    )
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                placeholder = "값을 입력해주세요.",
                                keyboardType = KeyboardType.DONE,
                                debounceTime = 500L
                            ) {
                            }
                        }
                    }
                }
            }
        }
    }
}
