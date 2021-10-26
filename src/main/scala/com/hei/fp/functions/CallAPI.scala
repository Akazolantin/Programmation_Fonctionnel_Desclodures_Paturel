package com.hei.fp.functions

import play.api.libs.json.{JsValue, Json}
import scalaj.http.{Http, HttpResponse}

import java.util.Calendar
import scala.collection.mutable.ArrayBuffer


class CallAPI {
  def callAPI(number: Int): ArrayBuffer[Array[Any]] = {

    val request: HttpResponse[String] = Http("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=DAI.DEX&outputsize=full&datatype=json&apikey=EXALLWIOROD5BP80").param("q","monkeys").asString
    val json : JsValue  = Json.parse(request.body)
    val now = Calendar.getInstance()

    var allElement = new ArrayBuffer[Array[Any]]();
    var i = 0
    for( x <-0 to number) {
      now.set(2021,10,26)
      val element: Array[Any] = new Array[Any](6);
      now.set(Calendar.DATE, (now.get(Calendar.DATE) - x))
      try {
        val date: String = now.get(Calendar.YEAR) + "-" +  "%02d".format(now.get(Calendar.MONTH)) + "-" + "%02d".format(now.get(Calendar.DATE))
        element(0) = date
        element(1) = (json("Time Series (Daily)")(date)("1. open").toString().slice(1,(json("Time Series (Daily)")(date)("1. open").toString()).size-2).toDouble)
        element(2) = (json("Time Series (Daily)")(date)("2. high").toString().slice(1,(json("Time Series (Daily)")(date)("2. high").toString()).size-2).toDouble)
        element(3) = (json("Time Series (Daily)")(date)("3. low").toString().slice(1,(json("Time Series (Daily)")(date)("3. low").toString()).size-2).toDouble)
        element(4) = (json("Time Series (Daily)")(date)("4. close").toString().slice(1,(json("Time Series (Daily)")(date)("4. close").toString()).size-2).toDouble)
        element(5) = (json("Time Series (Daily)")(date)("5. volume").toString().slice(1,(json("Time Series (Daily)")(date)("5. volume").toString()).size-2).toDouble)
        allElement += element;
        i=i+1
      }catch{
        case exception: Exception=>{}
      }
    }

    return allElement

  }

}