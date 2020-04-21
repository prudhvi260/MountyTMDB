package prudhvi.com.mountytmdb

import io.reactivex.internal.operators.single.SingleDoOnSuccess
import prudhvi.com.mountytmdb.Data.GetMoviesResponse
import prudhvi.com.mountytmdb.Data.Movie
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {
    private val api:Api
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api=retrofit.create(Api::class.java)
    }
    fun getTopRatedMovies(page:Int=1,onSuccess:(movies:List<Movie>)->Unit,onError:()->Unit){
        api.getTopRatedMovies(page=page).enqueue(object :Callback<GetMoviesResponse>{
            override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                if (response.isSuccessful)
                {
                    val responseBody=response.body()
                    if (responseBody!=null){
                        onSuccess.invoke(responseBody.movies)
                    }else{
                        onError.invoke()
                    }
                }else{
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                onError.invoke()
            }
        })
    }
    fun getUpcomingMovies(page: Int=1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit){
        api.getUpcommingMovies(page=page).enqueue(object :Callback<GetMoviesResponse>
        {
            override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                onError.invoke()
            }

            override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        onSuccess.invoke(responseBody.movies)
                    } else {
                        onError.invoke()
                    }
                } else {
                    onError.invoke()
                }
            }

        })
    }
}