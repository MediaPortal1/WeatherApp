package io.mediaportal.ironsource

import io.mediaportal.ironsource.model.ButtonToAction
import retrofit2.Response
import retrofit2.http.GET


interface ButtonActionEndpoint {
    @GET("/androidexam/butto_to_action_config.json")
    suspend fun getButtonAsAction(): Response<List<ButtonToAction>>
}