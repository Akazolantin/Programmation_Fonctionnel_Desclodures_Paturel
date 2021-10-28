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

    val gle = Array(Array("2020-10-12","12.548000","12.890000","12.528000","12.712000","12.445146","6560510"),
      Array("2020-10-13","12.702000","12.702000","12.122000","12.210000","11.953684","6225036"),
      Array("2020-10-14","12.200000","12.516000","12.126000","12.322000","12.063332","4234361"),
      Array("2020-10-15","12.102000","12.108000","11.600000","11.826000","11.577744","6236560"),
      Array("2020-10-16","11.980000","12.112000","11.550000","12.058000","11.804874","6586723"),
      Array("2020-10-19","12.118000","12.372000","12.014000","12.294000","12.035920","2509756"),
      Array("2020-10-20","12.246000","12.812000","12.108000","12.608000","12.343328","7325655"),
      Array("2020-10-21","12.784000","12.906000","12.392000","12.392000","12.131864","5132329"),
      Array("2020-10-22","12.250000","12.570000","12.058000","12.508000","12.245428","3928345"),
      Array("2020-10-23","12.620000","12.876000","12.542000","12.750000","12.482347","5270262"),
      Array("2020-10-26","12.418000","12.916000","12.360000","12.584000","12.319832","6023197"),
      Array("2020-10-27","12.620000","12.818000","11.944000","11.944000","11.693268","6990562"),
      Array("2020-10-28","11.428000","11.602000","11.048000","11.382000","11.143065","10900173"),
      Array("2020-10-29","11.414000","11.568000","11.080000","11.350000","11.111737","6352473"),
      Array("2020-10-30","11.200000","11.750000","11.150000","11.640000","11.395650","7073000"),
      Array("2020-11-02","11.784000","12.134000","11.592000","12.124000","11.869489","6029462"),
      Array("2020-11-03","12.344000","12.860000","12.344000","12.784000","12.515635","7722893"),
      Array("2020-11-04","12.460000","12.872000","12.298000","12.752000","12.484305","6868731"),
      Array("2020-11-05","13.500000","13.580000","13.020000","13.228000","12.950313","10958701"),
      Array("2020-11-06","13.340000","13.550000","13.050000","13.080000","12.805420","6151174"),
      Array("2020-11-09","13.394000","15.620000","13.374000","15.488000","15.162870","22981471"))

    var res = new ArrayBuffer[Array[Any]]()
    res = gle.to(ArrayBuffer[Array[Any]])
    System.out.println(l.moyenne_mobile(res))

    val breezePlot = new BreezePlot()
    breezePlot.setCol(2)
    breezePlot.setRow(4)

    breezePlot.addTitle("Moyenne mobile")
    breezePlot.addLine(l.moyenne_mobile(l.Call_API(100)),20)
    breezePlot.addLine(l.list_bourse(l.Call_API(100)),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Bollinger")
    breezePlot.addLine(l.Bollinger(l.Call_API(100)),0)
    breezePlot.addLine(l.list_bourse(l.Call_API(100)),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Moyenne mobile pondérée")
    breezePlot.addLine(l.moyenne_mobile_pondérée(l.Call_API(100)),0)
    breezePlot.addLine(l.list_bourse(l.Call_API(100)),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Moyenne mobile exponentielle")
    breezePlot.addLine(l.moyenne_mobile_exponentielle(l.Call_API(100)),0)
    breezePlot.addLine(l.list_bourse(l.Call_API(100)),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Tenkan Sen")
    breezePlot.addLine(l.Tenkan_Sen(l.Call_API(100)),0)
    breezePlot.addLine(l.list_bourse(l.Call_API(100)),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Kijun Sen")
    breezePlot.addLine(l.Kijun_Sen(l.Call_API(100)),0)
    breezePlot.addLine(l.list_bourse(l.Call_API(100)),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Senkou Span A")
    breezePlot.addLine(l.Senkou_SpanA(l.Call_API(100)),0)
    breezePlot.addLine(l.list_bourse(l.Call_API(100)),0)

    breezePlot.addGraf()
    breezePlot.addTitle("Enveloppes")
    breezePlot.addLine(l.enveloppes(l.Call_API(100)),0)
    breezePlot.addLine(l.list_bourse(l.Call_API(100)),0)
  }
}