package com.graphq_test.data.data

import com.apollographql.apollo.api.Response
import com.graphq_test.data.data.models.LaunchModel
import io.reactivex.Observable

interface ApiHelper {
    fun getLaunches(): Observable<AppResult<List<LaunchModel>>>
}