package com.hei.fp.functions.viz

import breeze.plot._

// https://zwild.github.io/posts/plotly-examples-for-scala/
// api doc : http://www.scalanlp.org/api/breeze/index.html#breeze.package

class BreezePlot {

  val fig = Figure()

  var i : Int = 0
  var col : Int = 2
  var row : Int = 2

  val p = fig.subplot(col,row,0)

  var currentPlot = p

  def addGraf(): Unit ={
    val p = fig.subplot(col,row,i)
    currentPlot = p
    i += 1
    p.legend=true
  }

  def addTitle(name : String):Unit = {
    currentPlot.title = name
  }

  def addLine(Y : Array[Double],start : Int,name : String): Unit ={
    var X : Array[Double] = new Array[Double](Y.size)
    for(x <- 0 to Y.size-1 ){
      X(x)= Y.size-1-x+start
    }
    currentPlot += plot(X,Y,name = name)
  }

  def setCol(n : Int) : Unit={
    col=n
  }

  def setRow(n : Int) : Unit={
    row=n
  }
}
