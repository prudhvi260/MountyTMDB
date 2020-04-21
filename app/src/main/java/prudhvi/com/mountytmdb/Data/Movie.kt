package prudhvi.com.mountytmdb.Data

import com.google.gson.annotations.SerializedName

class Movie (
    @SerializedName("id") val id:Long,
    @SerializedName("title")val title:String,
    @SerializedName("overview")val overview:String,
    @SerializedName("poster_path")val posterpath:String,
    @SerializedName("backdrop_path")val backdroppath:String,
    @SerializedName("vote_hit")val rating:Float,
    @SerializedName("release_date")val releasedate:String
)