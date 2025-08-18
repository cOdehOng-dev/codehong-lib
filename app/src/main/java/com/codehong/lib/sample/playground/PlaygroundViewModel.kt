package com.codehong.lib.sample.playground

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaygroundViewModel : ViewModel() {

    private var _isBorderOn = MutableLiveData(false)
    val isBorderOn: MutableLiveData<Boolean>
        get() = _isBorderOn


}