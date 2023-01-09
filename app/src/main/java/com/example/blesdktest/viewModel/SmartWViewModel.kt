package com.example.blesdktest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blesdktest.smartwatch.IBleSdkCallback
import com.example.blesdktest.smartwatch.SmartWImp
import com.veepoo.protocol.model.datas.*

class SmartWViewModel : ViewModel() {
    private var _sportData = MutableLiveData<SportData>()
    val sportData: LiveData<SportData> = _sportData

    private var _heartRate = MutableLiveData<HeartData>()
    val heartRate: LiveData<HeartData> = _heartRate

    private var _tempData = MutableLiveData<Float>()
    val tmpData: LiveData<Float> = _tempData

    private val _pwData = MutableLiveData<PwdData>()
    val pwdData: LiveData<PwdData> = _pwData

    private val _functionSupportData = MutableLiveData<FunctionDeviceSupportData>()
    val functioinSupportData: LiveData<FunctionDeviceSupportData> = _functionSupportData


    fun cekTmp() {
        swi.checkTemp()
    }

    fun checkHr() {
        swi.checkHeartRate()
    }

    fun cekStep() {
        swi.readStep()
    }

    fun verification() {
        swi.verification()
    }

    fun syncProfile() {
        swi.syncProfile()
    }

    private val swi = SmartWImp(object : IBleSdkCallback {
        override fun onHeartDataChange(heartData: HeartData) {
            _heartRate.value = heartData
        }

        override fun onSportDataChange(sportData: SportData) {
            _sportData.value = sportData
        }

        override fun onTmpDataChange(tmpDetectData: TemptureDetectData) {
            _tempData.value = tmpDetectData.tempture
        }

        override fun onVerificationpwData(pwdData: PwdData) {
            _pwData.value = pwdData
        }

        override fun onVerificationFuntionSupport(functionDeviceSupportData: FunctionDeviceSupportData) {
            _functionSupportData.value = functionDeviceSupportData
        }

        override fun onSyncProfile() {

        }

    })


}