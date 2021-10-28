package com.hei.fp.functions

import breeze.math.i
import breeze.numerics.sqrt
import com.hei.fp.functions.viz.BreezePlot
import play.api.libs.json.{JsValue, Json}
import scalaj.http.{Http, HttpResponse}

import java.util.Calendar
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Main {
  def main(args: Array[String]): Unit = {

    val l = new Functions()
    val api: CallAPI = new CallAPI()
    val tab = api.callAPI(2000)

    val breezePlot = new BreezePlot()
    breezePlot.setCol(4)
    breezePlot.setRow(2)

    breezePlot.addTitle("Moyenne mobile")
    breezePlot.addLine(l.moyenne_mobile(tab),10)
    breezePlot.addLine(l.list_bourse(tab),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Bollinger")
    breezePlot.addLine(l.Bollinger(tab),10)
    breezePlot.addLine(l.list_bourse(tab),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Moyenne mobile pondérée")
    breezePlot.addLine(l.moyenne_mobile_pondérée(tab),15)
    breezePlot.addLine(l.list_bourse(tab),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Moyenne mobile exponentielle")
    breezePlot.addLine(l.moyenne_mobile_exponentielle(tab),0)
    breezePlot.addLine(l.list_bourse(tab),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Tenkan Sen")
    breezePlot.addLine(l.Tenkan_Sen(tab),5)
    breezePlot.addLine(l.list_bourse(tab),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Kijun Sen")
    breezePlot.addLine(l.Kijun_Sen(tab),10)
    breezePlot.addLine(l.list_bourse(tab),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Senkou Span A")
    breezePlot.addLine(l.Senkou_SpanA(tab),20)
    breezePlot.addLine(l.list_bourse(tab),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Enveloppes")
    breezePlot.addLine(l.enveloppes(tab),10)
    breezePlot.addLine(l.list_bourse(tab),0)
  }
}