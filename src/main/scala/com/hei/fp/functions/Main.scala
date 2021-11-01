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
    val tab = api.callAPI(500)

    val breezePlot = new BreezePlot()
    breezePlot.setCol(4)
    breezePlot.setRow(2)

    breezePlot.addGraf()
    breezePlot.addTitle("Moyenne mobile")
    breezePlot.addLine(l.moyenne_mobile(tab),10, "Moyenne mobile")
    breezePlot.addLine(l.list_bourse(tab),0, "liste de la bourse")


    breezePlot.addGraf()
    breezePlot.addTitle("Moyenne mobile pondérée")
    breezePlot.addLine(l.moyenne_mobile_pondérée(tab),20, "Moyenne pondérée")
    breezePlot.addLine(l.list_bourse(tab),0,"liste de la bourse")

    breezePlot.addGraf()
    breezePlot.addTitle("Moyenne mobile exponentielle")
    breezePlot.addLine(l.moyenne_mobile_exponentielle(tab),20,"Moyenne mobile")
    breezePlot.addLine(l.list_bourse(tab),0,"liste de la bourse")

    breezePlot.addGraf()
    breezePlot.addTitle("Tenkan Sen")
    breezePlot.addLine(l.Tenkan_Sen(tab),5,"Tenkan Sen")
    breezePlot.addLine(l.list_bourse(tab),0,"liste de la bourse")

    breezePlot.addGraf()
    breezePlot.addTitle("Kijun Sen")
    breezePlot.addLine(l.Kijun_Sen(tab),10,"Kijun Sen")
    breezePlot.addLine(l.list_bourse(tab),0,"liste de la bourse")

    breezePlot.addGraf()
    breezePlot.addTitle("Senkou Span A")
    breezePlot.addLine(l.Senkou_SpanA(tab),20,"Senkou Span")
    breezePlot.addLine(l.list_bourse(tab),0,"liste de la bourse")

    breezePlot.addGraf()
    breezePlot.addTitle("Enveloppes")
    breezePlot.addLine(l.enveloppesHaut(tab),0,"enveloppe haute")
    breezePlot.addLine(l.moyenne_mobile(tab),0,"Moyenne mobile")
    breezePlot.addLine(l.enveloppesBas(tab),0,"enveloppe basse")
  }
}