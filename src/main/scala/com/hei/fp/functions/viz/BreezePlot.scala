package com.hei.fp.functions.viz

import breeze.linalg._
import breeze.plot.PaintScaleFactory.singletonFactoryForPaintScale
import breeze.plot._
import breeze.stats.distributions.{RandBasis, ThreadLocalRandomGenerator}
import org.apache.commons.math3.random.MersenneTwister
import play.api.libs.functional.syntax.toApplicativeOps

// https://zwild.github.io/posts/plotly-examples-for-scala/
// api doc : http://www.scalanlp.org/api/breeze/index.html#breeze.package

class BreezePlot {


  val fig = Figure()

  val p = fig.subplot(0)
  val x = linspace(0.0, 1.0)
  p += plot(x, x ^:^ 2.0)
  p += plot(x, x ^:^ 3.0, '.')
  p.title = "lines plotting"
  p.xlabel = "x axis"
  p.ylabel = "y axis"

  val seed: Int = 0
  val randBasis: RandBasis = new RandBasis(new ThreadLocalRandomGenerator(new MersenneTwister(seed)))
  val p2 = fig.subplot(2, 2, 1)
  val g2 = breeze.stats.distributions.Gaussian(0, 1)(randBasis)
  p2 += hist(g2.sample(100000), 100)
  p2.title = "A normal distribution"

  val p3 = fig.subplot(2, 2, 2)
  val x3 = linspace(0.0, 1.0, 100)
  val size3 = 0.1 * DenseVector.rand(100)
  p3 += scatter(x3, x3 ^:^ 2.0, size3.apply)
  p3.title = "scatter plotting"

  val p4 = fig.subplot(2, 2, 3)
  p4 += image(DenseMatrix.rand(200, 200))
  p4.title = "A random distribution"

}
