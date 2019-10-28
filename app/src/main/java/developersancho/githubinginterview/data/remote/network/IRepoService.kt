package developersancho.githubinginterview.data.remote.network

import developersancho.githubinginterview.data.remote.model.Repos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IRepoService {

    @GET("users/{user}/repos")
    suspend fun repos(@Path("user") user: String): Response<ArrayList<Repos>>

}