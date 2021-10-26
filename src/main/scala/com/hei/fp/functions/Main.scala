package com.hei.fp.functions

import play.api.libs.json.{JsValue, Json}
import scalaj.http.{Http, HttpResponse}

import java.util.Calendar
import scala.collection.mutable.ListBuffer

object Main {
  def main(args: Array[String]): Unit = {

    val request: HttpResponse[String] = Http("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=DAI.DEX&outputsize=full&datatype=json&apikey=EXALLWIOROD5BP80").param("q", "monkeys").asString
    val json: JsValue = Json.parse(request.body)
    val now = Calendar.getInstance()

    val allElement = Array.ofDim[String](500, 1)
    var index = 0;
    //val gle : Array[String] =new Array[String](request.body.size);
    for (x <- 0 to 500) {
      now.set(2021, 10, 12)
      val element: Array[String] = new Array[String](6);
      now.set(Calendar.DATE, (now.get(Calendar.DATE) - x))
      try {
        val date: String = now.get(Calendar.YEAR) + "-" + "%02d".format(now.get(Calendar.MONTH)) + "-" + "%02d".format(now.get(Calendar.DATE))
        element(0) = '"' + date + '"'
        element(1) = (json("Time Series (Daily)")(date)("1. open").toString())
        element(2) = (json("Time Series (Daily)")(date)("2. high").toString())
        element(3) = (json("Time Series (Daily)")(date)("3. low").toString())
        element(4) = (json("Time Series (Daily)")(date)("4. close").toString())
        element(5) = (json("Time Series (Daily)")(date)("5. volume").toString())
        allElement(index) = element
        index = index + 1
      } catch {
        case exception: Exception => {

        }
      }
    }
    //Affichage de la liste compléte
    var x = 0
    while (allElement(x).mkString(",") != "null") {
     // println(allElement(x).mkString(","))
      x += 1
    }

    def moyenne_mobile(list: Array[Int]): List[Double] = {
      var somme : Double = 0.0
      var res = new ListBuffer[Double]()
      for (i <- 20 to list.size){
        for (n <- i-20 to i-1){
          somme = somme + list(n)
        }
        somme = somme / 20
        res += somme
      }
      val resList = res.toList
      return resList
    }
    var liste = Array(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21)
    println(moyenne_mobile(liste))

    def RSI(list: Array[Int]): Unit = {

    }

    def Bollinger(list: Array[Int]): Unit = {

    }
  }
}
