package com.hei.fp.functions

import breeze.math.i
import breeze.numerics.sqrt
import play.api.libs.json.{JsValue, Json}
import scalaj.http.{Http, HttpResponse}

import java.util.Calendar
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Main {
  def main(args: Array[String]): Unit = {

    val api: CallAPI = new CallAPI()
    val liste1 = api.callAPI(40)
    //println(liste1(0)(0))
    //println(liste1.length)

    // for(i<- 0 to liste1.length-1)
    // println(liste1(i).mkString("///"))
    //}


    //moyenne mobile pour les derniers 20 jours
    def moyenne_mobile(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var somme: Double = 0.0
      var res = new ListBuffer[Double]()
      for (i <- 20 to list.length) {
        for (n <- i - 20 to i - 1) {
          somme = somme + liste1(n)(4).asInstanceOf[Double]
        }
        somme = somme / 20
        res += somme
      }
      val resList = res.toList
      return resList
    }

    //println("moyenne mobile")
    //println(moyenne_mobile(liste1))


    //Bollinger avec la moyenne mobile sur 20 jours (la valeur on fait +/- la valeur close pour faire un encadrement faire 2 fonctions ? pour valeur haute/basse ?
    //a vérifier
    def Bollinger(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var somme: Double = 0.0
      var res = new ListBuffer[Double]()
      for (i <- 20 to list.length) {
        for (n <- i - 20 to i - 1) {
          somme = somme + ((list(n)(4).asInstanceOf[Double] - moyenne_mobile(liste1)(i - 20)) * (list(n)(4).asInstanceOf[Double] - moyenne_mobile(liste1)(i - 20)))
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

    //moyenn pondéré fini et fonctionne
    def moyenne_mobile_pondérée(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var somme1: Double = 0.0
      var somme2: Double = 0.0
      var res = new ListBuffer[Double]()
      var i: Int = 20
      var sommeFin: Double = 0.0
      for (r <- 1 to 20) {
        somme2 = somme2 + r
      }
      for (k <- 20 to list.length) {
        for (n <- 0 to 19) {
          somme1 = somme1 + (list(n + k - 20)(4).asInstanceOf[Double] * i)
          i = i - 1
        }
        i = 20
        sommeFin = somme1 / somme2
        res += sommeFin
        somme1 = 0
      }
      val resList = res.toList
      return resList
    }

    //println("moyenne mobile pond")
    //println(moyenne_mobile_pondérée(liste1))

    def moyenne_mobile_exponentielle(list: ArrayBuffer[Array[Any]]): List[Double] = {
      val y = 1.5 //facteur de lissage
      var m: Double = y / 21
      var s = 0
      var r = 0.0
      var listmme = new ListBuffer[Double]()
      listmme += moyenne_mobile_pondérée(list)(0)
      for (i <- 20 to list.length) {
        for (j <- i - 20 to i - 1) {
          r = listmme(s) + m * (list(j)(4).asInstanceOf[Double] - listmme(s))
        }
        s = s + 1
        listmme += r
      }
      val rlist = listmme.toList
      return rlist
    }

    //println("moyenne mobile expo")
    //println(moyenne_mobile_exponentielle(liste1))

    //Autre graph fait de 4 fonctions

    //Fonction 1
    def Tenkan_Sen(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var moy: Double = 0.0
      var res = new ListBuffer[Double]()
      for (n <- 8 to list.length-1){
        var max1: Double = list(n-1)(4).asInstanceOf[Double]
        var min1: Double = list(n-1)(4).asInstanceOf[Double]
        for (i <- 0 to 8){
          if (max1<list(n-i)(4).asInstanceOf[Double]){
            max1=list(n-i)(4).asInstanceOf[Double]
          }
          if(min1>list(n-i)(4).asInstanceOf[Double]){
            min1=list(n-i)(4).asInstanceOf[Double]
          }
      }
        moy=(max1+min1)/2
        res+=moy
      }
      val resList = res.toList
      return resList
    }

    //println(Tenkan_Sen(liste1))


    //2eme fonction
    def Kijun_Sen(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var moy: Double = 0.0
      var res = new ListBuffer[Double]()
      for (n <- 26 to list.length-1){
        var max2: Double = list(n-1)(4).asInstanceOf[Double]
        var min2: Double = list(n-1)(4).asInstanceOf[Double]
        for (i <- 0 to 25){
          if (max2<list(n-i)(4).asInstanceOf[Double]){
            max2=list(n-i)(4).asInstanceOf[Double]
          }
          if(min2>list(n-i)(4).asInstanceOf[Double]){
            min2=list(n-i)(4).asInstanceOf[Double]
          }
        }
        moy=(max2+min2)/2
        res+=moy
      }
      val resList = res.toList
      return resList
    }

    //println(Kijun_Sen(liste1))

    //3eme  et derniere fonction
    def Senkou_SpanA(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var moy: Double = 0.0
      var res = new ListBuffer[Double]()
      for(n<-0 to list.length-27) {
      moy = (Tenkan_Sen(list)(n+2)+Kijun_Sen(list)(n))/2
      res+=moy
      }
      val resList = res.toList
      return resList
    }

    //println(Senkou_SpanA(liste1))

    //valeur haute et basse 2 fonctions ?
    def enveloppes(list: ArrayBuffer[Array[Any]]): List[Double] = {
      var moy : Double = 0.0
      var res = new ListBuffer[Double]()
      for (i <- 0 to moyenne_mobile(list).length){
        moy = moyenne_mobile(list)(i)
        moy = moy + 4/100 * moy
        res+=moy
      }
      val resList = res.toList
      return resList
    }
  }
}