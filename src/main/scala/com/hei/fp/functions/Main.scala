package com.hei.fp.functions

import play.api.libs.json.{JsValue, Json}
import scalaj.http.{Http, HttpResponse}

import java.util.Calendar
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Main {
  def main(args: Array[String]): Unit = {

    val api : CallAPI = new CallAPI()
    val liste1 = api.callAPI()
    //println(liste1(0)(0))
    //println(liste1(0)(4))


    //moyenne mobile pour les derniers 20 jours
    def moyenne_mobile(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var somme : Double = 0.0
      var res = new ListBuffer[Double]()
      for (i <- 20 to list.length){
        for (n <- i-20 to i-1){
          somme = somme + liste1(n)(4).asInstanceOf[Double]
        }
        somme = somme / 20
        res += somme
        println(somme)
      }
      val resList = res.toList
      return resList
    }
    //var liste = Array(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21)
    println(moyenne_mobile(liste1))


    def RSI(list: Array[Int]): Unit = {

    }

    def Bollinger(list: Array[Int]): Unit = {

    }
  }
}
