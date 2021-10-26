package com.hei.fp.functions

import breeze.numerics.sqrt
import play.api.libs.json.{JsValue, Json}
import scalaj.http.{Http, HttpResponse}

import java.util.Calendar
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Main {
  def main(args: Array[String]): Unit = {

    val api : CallAPI = new CallAPI()
    val liste1 = api.callAPI(100)
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
      }
      val resList = res.toList
      return resList
    }

    println(moyenne_mobile(liste1)(0))


    def RSI(list: Array[Int]): Unit = {

    }

    //Bollinger avec la moyenne mobile sur 20 jours
    def Bollinger(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var somme : Double = 0.0;
      var res = new ListBuffer[Double]()
      for (i <- 20 to list.length){
        for (n <- i-20 to i-1){
          somme = somme + ((list(n)(4).asInstanceOf[Double]-moyenne_mobile(liste1)(i-20))*(list(n)(4).asInstanceOf[Double]-moyenne_mobile(liste1)(i-20)))
        }
        somme = somme / 20
        somme = sqrt(somme)
        somme = somme * 2
        res += somme
      }
      val resList = res.toList
      return resList
    }

    println(Bollinger(liste1))
  }
}