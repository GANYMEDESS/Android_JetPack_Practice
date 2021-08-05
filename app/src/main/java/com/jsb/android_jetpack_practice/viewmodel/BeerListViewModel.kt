package com.jsb.android_jetpack_practice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jsb.android_jetpack_practice.model.entities.beers.Beers
import com.jsb.android_jetpack_practice.model.network.BeerApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class BeerListViewModel: ViewModel()
{
    private val beerApiService = BeerApiService()
    private val compositeDisposable = CompositeDisposable()

    val loadBeerList = MutableLiveData<Boolean>()
    val beerListResponse = MutableLiveData<Beers>()
    val beerListLoadingError = MutableLiveData<Boolean>()

    fun getBeerListFromAPI(){
        loadBeerList.value = true

        compositeDisposable.add(
            beerApiService.getBeerList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Beers>(){
                    override fun onSuccess(value: Beers?) {
                        loadBeerList.value = false
                        beerListResponse.value = value!!
                        beerListLoadingError.value = false
                    }

                    override fun onError(e: Throwable?) {
                        loadBeerList.value = false
                        beerListLoadingError.value = true
                        e!!.printStackTrace()
                    }
                })
        )
    }
}