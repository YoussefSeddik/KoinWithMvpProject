package com.graphq_test.data.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.graphq_test.data.GetLaunchesQuery
import com.graphq_test.data.data.models.LaunchModel
import com.graphq_test.data.fragment.FragLaunch
import io.reactivex.Observable

class ApiHelperImp(private val apolloClient: ApolloClient) : ApiHelper {
    override fun getLaunches(): Observable<AppResult<List<LaunchModel>>> {
        return Rx2Apollo.from(apolloClient.query(GetLaunchesQuery())).map {
            if (it.data != null) {
                val launches = it.data?.launches?.launches?.mapNotNull {queryResponse->
                    queryResponse?.fragments?.fragLaunch?.convertToLaunchModel()
                }?.toList()?: listOf()
                AppResult.Success<List<LaunchModel>>(launches)
            } else {
                AppResult.Failure<List<LaunchModel>>(it.errors?.firstOrNull()?.message?:"")
            }
        }
    }
}

fun FragLaunch.convertToLaunchModel(): LaunchModel {
    return LaunchModel(
        id = id,
        site = site?:""
    )
}