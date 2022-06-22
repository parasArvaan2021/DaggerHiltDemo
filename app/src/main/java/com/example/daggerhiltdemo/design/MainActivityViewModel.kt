package com.example.daggerhiltdemo.design

import androidx.databinding.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhiltdemo.repository.MainRepository
import com.example.daggerhiltdemo.response.MainReponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import com.example.daggerhiltdemo.BR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel(), Observable {
    private val propertyChangeRegistry = PropertyChangeRegistry()

    private var _mainResponse: MutableLiveData<MainReponse> = MutableLiveData()
    var dataText: ObservableField<String> = ObservableField("")

    @Bindable
    fun getTextData(): String {
        return dataText.get().toString()
    }

    fun setTextData(dataText: String) {
        this.dataText.set(dataText)
        propertyChangeRegistry.notifyChange(this, BR.textData)
    }

    val mainResponse: LiveData<MainReponse>
        get() {
            return _mainResponse
        }

    init {
        callApi()
    }

    fun callApi() {

        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.IO) {
            val reponse = mainRepository.getExpenseList()
            launch {
                _mainResponse.postValue(reponse)
            }
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    fun setData(mainReponse: MainReponse) {
        setTextData(mainReponse[0].title.toString())
    }
}