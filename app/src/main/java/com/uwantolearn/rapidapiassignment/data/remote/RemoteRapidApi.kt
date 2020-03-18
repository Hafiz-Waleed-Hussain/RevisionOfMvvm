package com.uwantolearn.rapidapiassignment.data.source.remote

import com.google.gson.Gson
import com.uwantolearn.api.retrofit.service.RapidAPIRetrofitService
import com.uwantolearn.rapidapiassignment.model.RapidImage
import io.reactivex.Single
import javax.inject.Inject

class RemoteRapidApi @Inject constructor(private val service: RapidAPIRetrofitService) {

    fun loadImages(query: String, pageNumber: Int): Single<List<RapidImage>> {
        val json =
            "[{\"url\":\"https://dressupwho.net/i-files/thumb_1/20610-donald-trump-vs-hillary-clinton-70x175.jpg\",\"height\":175,\"width\":70,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=6446573495239113800\",\"thumbnailHeight\":400,\"thumbnailWidth\":160,\"base64Encoding\":null},{\"url\":\"http://games.dressuppink.com/images/donald-trump-vs-hillary-clinton.jpg?t9tad7\",\"height\":60,\"width\":80,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=8187980894059393231\",\"thumbnailHeight\":210,\"thumbnailWidth\":280,\"base64Encoding\":null},{\"url\":\"https://pmcdeadline2.files.wordpress.com/2018/08/donald-trump-2.jpg?w=605\",\"height\":403,\"width\":605,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=6250921748791014870\",\"thumbnailHeight\":173,\"thumbnailWidth\":260,\"base64Encoding\":null},{\"url\":\"http://www.india.com/wp-content/uploads/2018/08/donald-trump.jpg\",\"height\":415,\"width\":700,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=8531529161775551633\",\"thumbnailHeight\":237,\"thumbnailWidth\":400,\"base64Encoding\":null},{\"url\":\"https://cdn.dnaindia.com/sites/default/files/styles/full/public/2018/08/28/723845-660810-trumpdonald-afp-031518.jpg\",\"height\":720,\"width\":1280,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=3621691658002374059\",\"thumbnailHeight\":157,\"thumbnailWidth\":280,\"base64Encoding\":null},{\"url\":\"http://projects.thestar.com/donald-trump-fact-check/images/trumpcheck-02.jpg\",\"height\":391,\"width\":800,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=8672178186432489387\",\"thumbnailHeight\":127,\"thumbnailWidth\":260,\"base64Encoding\":null},{\"url\":\"http://a57.foxnews.com/images.foxnews.com/content/fox-news/entertainment/2018/08/28/snl-star-pete-davidson-recalls-working-with-psycho-donald-trump-and-his-bad-audition-to-portray-him/_jcr_content/par/featured_image/media-0.img.jpg/0/0/1535481722723.jpg?ve=1\",\"height\":720,\"width\":1280,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=7252629321769597480\",\"thumbnailHeight\":191,\"thumbnailWidth\":340,\"base64Encoding\":null},{\"url\":\"https://sportshub.cbsistatic.com/i/r/2018/08/28/c9026af8-8d09-4d1b-9c5a-67e082693152/thumbnail/770x433/d62e243ababa261dfcbd28248ed83980/donald-trump-gianni-infantino.jpg\",\"height\":433,\"width\":770,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=5708997472337713695\",\"thumbnailHeight\":157,\"thumbnailWidth\":280,\"base64Encoding\":null},{\"url\":\"https://images.thestar.com/4683apHlx2GybGKlNQfrEOIzK6w=/1200x799/smart/filters:cb(1534514539753)/https://www.thestar.com/content/dam/thestar/news/world/2018/08/17/donald-trump-cancels-ridiculously-expensive-military-parade/trumpmilitary.jpg\",\"height\":799,\"width\":1200,\"thumbnail\":\"https://contextualwebsearch.com/api/thumbnail/get?value=3802727927775564525\",\"thumbnailHeight\":159,\"thumbnailWidth\":240,\"base64Encoding\":null}]"
        val gson = Gson()
        val toMutableList = gson.fromJson(json, Array<RapidImage>::class.java).toMutableList()

        return Single.just(toMutableList)
//        return service.loadImages(query, pageNumber)
    }
}