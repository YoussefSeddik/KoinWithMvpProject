package com.example.graphqltest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graphqltest.R
import com.graphq_test.data.data.models.LaunchModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {
    val presenter :MainActivityPresenter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        presenter.getLaunches()
    }

    override fun onGetLaunchesSuccess(lunches: List<LaunchModel>) {
        val list = lunches
    }

    override fun onGetLaunchesFailed(err: String) {
    }
}