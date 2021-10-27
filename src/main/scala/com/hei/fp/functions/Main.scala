package com.hei.fp.functions

import breeze.math.i
import breeze.numerics.sqrt
import play.api.libs.json.{JsValue, Json}
import scalaj.http.{Http, HttpResponse}

import java.util.Calendar
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Main {
  def main(args: Array[String]): Unit = {

    val api : CallAPI = new CallAPI()
    val liste1 = api.callAPI(21)
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

    println(liste1.length)
    println(moyenne_mobile(liste1))


    //Bollinger avec la moyenne mobile sur 20 jours (la valeur on fait +/- la valeur close pour faire un encadrement faire 2 fonctions ? pour valeur haute/basse ?
    def Bollinger(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var somme : Double = 0.0
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

    //println(Bollinger(liste1))


    def moyenne_mobile_pondérée(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var somme1 : Double = 0.0
      var somme2 : Double = 0.0
      var res = new ListBuffer[Double]()
      var i : Int = 20
      var sommeFin : Double = 0.0
      for (r <- 1 to 20){
        somme2 = somme2 + r
      }
      for (k <- 20 to list.length){
        for (n <- 0 to 19){
          i=i-1
          somme1 = somme1 + (list(n+k-20)(4).asInstanceOf[Double] * i)
        }
        i=0
        sommeFin = somme1 / somme2
        res += sommeFin
        somme1 = 0
      }
      val resList = res.toList
      return resList
    }

    //println(moyenne_mobile_pondérée(liste1))
    //println(liste1(0)(0))
    //println(liste1(0)(4))
  }
}