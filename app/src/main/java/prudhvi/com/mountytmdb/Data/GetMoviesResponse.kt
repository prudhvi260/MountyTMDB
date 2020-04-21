package prudhvi.com.mountytmdb.Data

import com.google.gson.annotations.SerializedName

class GetMoviesResponse(@SerializedName("page")val page:Int,
                        @SerializedName("results")val movies:List<Movie>,
                        @SerializedName("total pages")val pages:Int)
