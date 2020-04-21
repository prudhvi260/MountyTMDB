package prudhvi.com.mountytmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import prudhvi.com.mountytmdb.Data.Movie

class MainActivity : AppCompatActivity() {
    private lateinit var TopRateMovies: RecyclerView
    private lateinit var TopRatedMoviesAdapter: MoviesAdapter
    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager
    private var topratedmoviespage=1
    private lateinit var upcomingMovies:RecyclerView
    private lateinit var upcomingmoviesadapter:MoviesAdapter
    private lateinit var upcomingmoviesLayout:LinearLayoutManager
    private var upcomingmoviepage=1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TopRateMovies=findViewById(R.id.toprated)
        upcomingMovies=findViewById(R.id.upcomingmovies)
        TopRateMovies.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        TopRatedMoviesAdapter= MoviesAdapter(mutableListOf())
        TopRateMovies.adapter=TopRatedMoviesAdapter
        upcomingmoviesLayout=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        upcomingMovies.layoutManager=upcomingmoviesLayout
        upcomingmoviesadapter= MoviesAdapter(mutableListOf())
        upcomingMovies.adapter=upcomingmoviesadapter
        getTopRatedMovies()
        getupcomingMovies()
    }
    private fun getTopRatedMovies()
    {
        MoviesRepository.getTopRatedMovies(topratedmoviespage,onSuccess = ::getTopRatedMoviesFetched,onError = ::onError)
    }
    private fun getTopRatedMoviesFetched(movies:List<Movie>){
        Log.d("MainActivity","Movies :$movies")
        TopRatedMoviesAdapter.appendMoives(movies)
    }
    private fun onError(){
        Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
    }
    private fun getupcomingMovies(){
        MoviesRepository.getUpcomingMovies(upcomingmoviepage,::getupcomingfetched,::onError)
    }
    private fun getupcomingfetched(movies: List<Movie>){
        upcomingmoviesadapter.appendMoives(movies)
    }

}
