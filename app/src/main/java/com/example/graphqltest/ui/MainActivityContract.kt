package com.example.graphqltest.ui

import com.graphq_test.data.data.models.LaunchModel

interface MainActivityContract {
    interface Presenter {
        fun getLaunches()
    }

    interface View {
        fun onGetLaunchesSuccess(lunches: List<LaunchModel>)
        fun onGetLaunchesFailed(err: String)

    }
}