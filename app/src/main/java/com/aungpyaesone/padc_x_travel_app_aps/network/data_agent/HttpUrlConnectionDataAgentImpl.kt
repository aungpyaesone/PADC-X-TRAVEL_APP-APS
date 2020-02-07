package com.aungpyaesone.padc_x_travel_app_aps.network.data_agent

import android.os.AsyncTask
import android.util.Log
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.network.response.GetAllTourResponse
import com.aungpyaesone.padc_x_travel_app_aps.utils.BASE_URL
import com.aungpyaesone.padc_x_travel_app_aps.utils.END_POINT
import com.aungpyaesone.padc_x_travel_app_aps.utils.EN_CONNECTION_ERROR
import com.aungpyaesone.padc_x_travel_app_aps.utils.PARAM_ACCESS_TOKEN
import com.google.gson.Gson
import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

object HttpUrlConnectionDataAgentImpl : TourDataAgent {

    override fun getAllTours(
        accessToken: String,
        onSuccess: (List<CountryVO>, List<CountryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        GetAllTour(
                    accessToken = accessToken,
                    onSuccess = onSuccess,
                    onFailure = onFailure).execute()
    }

    class GetAllTour(
                       val accessToken:String,
                       val onSuccess: (List<CountryVO>, List<CountryVO>) -> Unit,
                      val onFailure: (String) -> Unit): AsyncTask<Void,Void,GetAllTourResponse?>(){

        override fun doInBackground(vararg params: Void?): GetAllTourResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder


            try{
                url = URL(BASE_URL + END_POINT)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                connection.readTimeout = 1500
                connection.doInput = true
                connection.doOutput = true

                val param = ArrayList<NameValuePair>()
                param.add(
                    BasicNameValuePair(
                        PARAM_ACCESS_TOKEN,
                        accessToken
                    )
                )

                val outputStream = connection.outputStream
                val writer = BufferedWriter(OutputStreamWriter(outputStream,"UTF-8"))
                writer.write(getQuery(param))
                writer.flush()
                writer.close()
                outputStream.close()
                connection.connect()

                reader = BufferedReader(
                    InputStreamReader(connection.inputStream)
                )
                stringBuilder = StringBuilder()
                for (line in reader.readLines()){
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                return Gson().fromJson(responseString,GetAllTourResponse::class.java)
            }
            catch(e: Exception) {
                e.printStackTrace()
                Log.e("News Error", e.message ?: "")
            }
            finally {
                if (reader!= null)
                {
                    try {
                        reader.close()
                    }
                    catch (ioe: IOException){
                        ioe.printStackTrace()
                    }
                }
            }
            return null

        }
        @Throws(UnsupportedEncodingException::class)
        private fun getQuery(params:List<NameValuePair>):String{
            val result = StringBuilder()
            var first = true

            for (pair in params)
            {
                if (first)
                    first = false
                else
                    result.append("&")
                result.append(URLEncoder.encode(pair.name,"UTF-8"))
                result.append("=")
                result.append(URLEncoder.encode(pair.value,"UTF-8"))
            }
            return result.toString()
        }


        override fun onPostExecute(result: GetAllTourResponse?) {
            super.onPostExecute(result)
            if( result != null){
                if(result.isResponseOk()){
                    result.data?.let {data->
                        onSuccess(data.country.toList(),data.popular_tour.toList())
                    }
                }
                else{
                    onFailure(result.message)
                }

            }else{
                onFailure(EN_CONNECTION_ERROR)
            }
        }
    }
}