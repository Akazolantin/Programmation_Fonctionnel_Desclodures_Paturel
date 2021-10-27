package com.hei.fp.functions.viz

import breeze.linalg._
import breeze.plot._
import breeze.stats.distributions.RandBasis

// https://zwild.github.io/posts/plotly-examples-for-scala/
// api doc : http://www.scalanlp.org/api/breeze/index.html#breeze.package

class BreezePlot {

  val fig = Figure()

  var i : Int = 1
  var col : Int = 2
  var row : Int = 2

  val p = fig.subplot(col,row,0)
  val x = linspace(0.0, 1.0)
  p += plot(x, x ^:^ 2.0)
  p += plot(x, x ^:^ 3.0, '.')
  p.title = "lines plotting"
  p.xlabel = "x axis"
  p.ylabel = "y axis"

  var currentPlot = p

  def addGraf(): Unit ={
    val p = fig.subplot(col,row,i)
    currentPlot = p
    i += 1
  }

  def addLine(array : Array[Double],start : Int): Unit ={
    var Y : Array[Double] = reverse(array)
    var X : Array[Double] = new Array[Double](Y.size)
    for(x <- start to Y.size+start ){
      X(x-start)= x
    }
    currentPlot += plot(X,Y)
  }

  def reverse(array : Array[Double]): Array[Double] ={
    var result : Array[Double] = new Array[Double](array.size)
    for(x <- 0 to array.size){
      result(x) = array(array.size - x)
    }
    return result
  }

  def setCol(n : Int) : Unit={
    col=n
  }

  def setRow(n : Int) : Unit={
    row=n
  }
}
