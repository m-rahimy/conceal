package ir.mrahimy.conceal.ui.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.mrahimy.conceal.base.BaseViewModel
import ir.mrahimy.conceal.data.Sample
import ir.mrahimy.conceal.repository.SampleRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SampleViewModel(private val sampleRepository: SampleRepository) : BaseViewModel() {

    val sampleList = MutableLiveData<List<Sample>>()

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        viewModelScope.launch {
            _isLoading.postValue(true)
            delay(100)
            sampleList.postValue(sampleRepository.getSampleInitList())
            _isLoading.postValue(false)
        }
    }

    fun addRandomSample() = viewModelScope.launch {
        _isLoading.postValue(true)
        delay(50)
        val samples = sampleList.value?.toMutableList() ?: mutableListOf()
        sampleList.postValue(samples.apply { add(sampleRepository.getRandomSample(samples.size)) })
        _isLoading.postValue(false)
    }

    fun addRandomSamples() = viewModelScope.launch {
        _isLoading.postValue(true)
        repeat(10) {
            delay(100)
            addRandomSample()
        }

        _isLoading.postValue(false)
    }

    fun clearSamples() = viewModelScope.launch {
        _isLoading.postValue(true)
        val list = sampleList.value?.toMutableList() ?: return@launch
        val iter = list.iterator()
        while (iter.hasNext()) {
            iter.apply {
                next()
                remove()
            }
            sampleList.postValue(list)
            delay(50)
        }

        _isLoading.postValue(false)
    }
}