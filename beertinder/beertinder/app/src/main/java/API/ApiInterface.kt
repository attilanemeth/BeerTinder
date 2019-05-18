package API

import retrofit2.Call
import retrofit2.http.*


interface  ApiInterface{
    @GET("/api/places/place")
    public fun getPlaces(): Call<List<Place>>

    @POST("/api/notification/noti")
    @Headers("Content-Type: application/json")
    fun postNoti(@Body body: Message):Call<String>


    @GET("/api/places/inProgress")
    fun getInProgress():Call<List<Place>>

}