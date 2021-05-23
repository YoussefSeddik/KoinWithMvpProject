package com.example.graphqltest.ui

import com.graphq_test.data.data.ApiHelper
import com.graphq_test.data.data.AppResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivityPresenter(private val apiHelper: ApiHelper) : MainActivityContract.Presenter {
    private val compositeDisposable = CompositeDisposable()
    private var mainActivityView:MainActivityContract.View ? =null

    fun attachView(view:MainActivityContract.View){
        mainActivityView = view
    }
    override fun getLaunches() {
        compositeDisposable.addAll(
            apiHelper.getLaunches().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribeBy(
                    onNext = {
                        when (it) {
                            is AppResult.Success -> {
                                mainActivityView?.onGetLaunchesSuccess(it.data)
                            }
                            is AppResult.Failure -> {
                                mainActivityView?.onGetLaunchesFailed(it.errorMessage)
                            }
                        }
                    },
                    onError = {

                    }
                )
        )
    }
}