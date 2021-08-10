package com.jsb.android_jetpack_practice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jsb.android_jetpack_practice.model.entities.RandomBeer
import com.jsb.android_jetpack_practice.model.network.BeerApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class RandomBeerViewModel: ViewModel()
{
    private val randomBeerApiService = BeerApiService()
    private val compositeDisposable = CompositeDisposable()

    val loadRandomBeer = MutableLiveData<Boolean>()
    val randomBeerResponse = MutableLiveData<RandomBeer.Beers>()
    val randomBeerLoadingError = MutableLiveData<Boolean>()

    fun getRandomBeerFromAPI(){
        loadRandomBeer.value = true

        compositeDisposable.add(
            randomBeerApiService.getRandomBeer()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RandomBeer.Beers>(){
                    override fun onSuccess(value: RandomBeer.Beers?) {
                        loadRandomBeer.value = false
                        randomBeerResponse.value = value!!
                        randomBeerLoadingError.value = false
                    }

                    override fun onError(e: Throwable?) {
                        loadRandomBeer.value = false
                        randomBeerLoadingError.value = true
                        e!!.printStackTrace()
                    }
                })
        )
    }
}