package com.hei.fp.functions

import breeze.numerics.sqrt

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class Functions {

  def list_bourse(list: ArrayBuffer[Array[Any]]): Array[Double] ={
    var res1 = new ListBuffer[Double]()
    for (i<- 0 to list.length-1){
      res1+=list(i)(4).asInstanceOf[Double]
    }
    val resList1 = res1.toArray
    return resList1
  }


  def moyenne_mobile(list: ArrayBuffer[Array[Any]]): Array[Double] = {
    var somme: Double = 0.0
    var res = new ListBuffer[Double]()
    for (i <- 20 to list.length) {
      for (n <- i - 20 to i - 1) {
        somme = somme + list(n)(4).asInstanceOf[Double]
      }
      somme = somme / 20

      res += somme
    }
    val resList = res.toArray
    return resList
  }


  def moyenne_mobile_pondérée(list: ArrayBuffer[Array[Any]]): Array[Double] = {
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
    val resList = res.toArray
    return resList
  }

  def moyenne_mobile_exponentielle(list: ArrayBuffer[Array[Any]]): Array[Double] = {
    val y = 1.5
    var m: Double = y / 21
    var index = 0
    var sommeFin = 0.0
    var res = new ListBuffer[Double]()
    res += moyenne_mobile_pondérée(list)(0)
    for (i <- 20 to list.length) {
      for (j <- i - 20 to i - 1) {
        sommeFin = res(index) + m * (list(j)(4).asInstanceOf[Double] - res(index))
      }
      index = index + 1
      res += sommeFin
    }
    val resList = res.toArray
    return resList
  }

  def Tenkan_Sen(list: ArrayBuffer[Array[Any]]): Array[Double] = {
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
    val resList = res.toArray
    return resList
  }

  def Kijun_Sen(list: ArrayBuffer[Array[Any]]): Array[Double] = {
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
    val resList = res.toArray
    return resList
  }

  def Senkou_SpanA(list: ArrayBuffer[Array[Any]]): Array[Double] = {
    var moy: Double = 0.0
    var res = new ListBuffer[Double]()
    for(n<-0 to list.length-27) {
      moy = (Tenkan_Sen(list)(n+2)+Kijun_Sen(list)(n))/2
      res+=moy
    }
    val resList = res.toArray
    return resList
  }

  def enveloppesHaut(list: ArrayBuffer[Array[Any]]): Array[Double] = {
    var moy : Double = 0.0
    var res = new ListBuffer[Double]()
    for (i <- 0 to moyenne_mobile(list).length-1){
      moy = moyenne_mobile(list)(i)
      moy = moy + (0.04 * moy)
      res+=moy
    }
    val resList = res.toArray
    return resList
  }

  def enveloppesBas(list: ArrayBuffer[Array[Any]]): Array[Double] = {
    var envHaut = enveloppesHaut(list)
    var moy : Double = 0.0
    for (i <- 0 to envHaut.length-1){
      moy = moyenne_mobile(list)(i)
      envHaut(i)=envHaut(i)-(2*0.04*moy)
    }
    return envHaut
  }

}
