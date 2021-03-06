import org.scalatest.freespec.AnyFreeSpec
import com.hei.fp.functions
import com.hei.fp.functions.Functions

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class FunctionsTest extends AnyFreeSpec{
  // Date,	Open,	High,	Low,	Close,	Adj Close,	Volume

  // Date,	Open,	High,	Low,	Close,	Adj Close,	Volume
  var gle = new ArrayBuffer[Array[Any]]()
  gle = ArrayBuffer(
    Array("2020-10-12",12.548000,12.890000,12.528000,12.712000,12.445146,6560510),
    Array("2020-10-13",12.702000,12.702000,12.122000,12.210000,11.953684,6225036),
    Array("2020-10-14",12.200000,12.516000,12.126000,12.322000,12.063332,4234361),
    Array("2020-10-15",12.102000,12.108000,11.600000,11.826000,11.577744,6236560),
    Array("2020-10-16",11.980000,12.112000,11.550000,12.058000,11.804874,6586723),
    Array("2020-10-19",12.118000,12.372000,12.014000,12.294000,12.035920,2509756),
    Array("2020-10-20",12.246000,12.812000,12.108000,12.608000,12.343328,7325655),
    Array("2020-10-21",12.784000,12.906000,12.392000,12.392000,12.131864,5132329),
    Array("2020-10-22",12.250000,12.570000,12.058000,12.508000,12.245428,3928345),
    Array("2020-10-23",12.620000,12.876000,12.542000,12.750000,12.482347,5270262),
    Array("2020-10-26",12.418000,12.916000,12.360000,12.584000,12.319832,6023197),
    Array("2020-10-27",12.620000,12.818000,11.944000,11.944000,11.693268,6990562),
    Array("2020-10-28",11.428000,11.602000,11.048000,11.382000,11.143065,10900173),
    Array("2020-10-29",11.414000,11.568000,11.080000,11.350000,11.111737,6352473),
    Array("2020-10-30",11.200000,11.750000,11.150000,11.640000,11.395650,7073000),
    Array("2020-11-02",11.784000,12.134000,11.592000,12.124000,11.869489,6029462),
    Array("2020-11-03",12.344000,12.860000,12.344000,12.784000,12.515635,7722893),
    Array("2020-11-04",12.460000,12.872000,12.298000,12.752000,12.484305,6868731),
    Array("2020-11-05",13.500000,13.580000,13.020000,13.228000,12.950313,10958701),
    Array("2020-11-06",13.340000,13.550000,13.050000,13.080000,12.805420,6151174),
    Array("2020-11-09",13.394000,15.620000,13.374000,15.488000,15.162870,22981471),
    Array("2020-11-10",15.460000,16.410000,15.456000,16.202000,15.861882,18514843),
    Array("2020-11-11",16.101999,16.257999,15.378000,15.604000,15.276436,9206919),
    Array("2020-11-12",15.250000,15.496000,14.860000,15.276000,14.955321,7047684),
    Array("2020-11-13",15.244000,15.710000,15.080000,15.628000,15.299932,6537718),
    Array("2020-11-16",16.100000,16.653999,15.846000,16.285999,15.944118,11282326),
    Array("2020-11-17",16.388000,16.500000,16.028000,16.500000,16.153625,6728383),
    Array("2020-11-18",16.299999,16.525999,16.143999,16.417999,16.073347,6392792),
    Array("2020-11-19",16.200001,16.312000,15.846000,16.186001,15.846219,6793677),
    Array("2020-11-20",16.167999,16.379999,16.084000,16.164000,15.824679,4116625))
  val fnt = new Functions()

  "My function  " - {
    ": moyenn_mobile for gle " - {
      "should return liste" in {
        val liste = Array(12.3274, 13.08257, 13.3199285, 13.495896425000002, 13.677194821250001, 13.8647597410625, 14.073737937053128, 14.278786846852658, 14.490339242342634, 14.684816912117132, 14.865240795605853)
        for (i <- 0 to liste.length-1)
        assert(fnt.moyenne_mobile(gle)(i) == liste(i)
        )
      }
    }
  }

  "My function  " - {
    ": moyenne_mobile_pond??r??e for gle " - {
      "should return liste" in {
        val liste = Array(12.274238095238093, 12.250828571428574, 12.294238095238097, 12.342609523809523, 12.454647619047622, 12.561590476190478, 12.665066661904763, 12.757171419047618, 12.88901902857143, 13.02733330952381, 13.158857114285716)
        for (i <- 0 to liste.length-1)
          assert(fnt.moyenne_mobile_pond??r??e(gle)(i) == liste(i)
          )
      }
    }
  }

  "My function  " - {
    ": moyenne_mobile_exponentielle for gle " - {
      "should return liste" in {
        val liste = Array(12.274238095238093, 12.3317925170068, 12.557235908649172, 12.817576200888517, 13.016606472253622, 13.177991724235506, 13.352992315361542, 13.562492792835718, 13.772314736204596, 13.961292183618554, 14.120199956217228, 14.266185673630282)

        for (i <- 0 to liste.length-1)
          assert(fnt.moyenne_mobile_exponentielle(gle)(i) == liste(i)
          )
      }
    }
  }

  "My function  " - {
    ": Tenkan_Sen for gle " - {
      "should return liste" in {
              val liste = Array(12.269, 12.288, 12.288, 12.288, 12.065999999999999, 12.05, 12.05, 12.05, 12.067, 12.067, 12.289, 12.289, 13.419, 13.776, 13.921000000000001, 14.163, 14.477, 14.5189995, 14.79, 14.79, 15.888, 15.888)
              for (i <- 0 to liste.length-1)
                assert(fnt.Tenkan_Sen(gle)(i) == liste(i)
                )
            }
          }
        }
  "My function  " - {
    ": Kijun_Sen for gle " - {
      "should return liste" in {
        val liste = Array(13.925, 13.925, 13.925, 13.925)

        for (i <- 0 to liste.length-1)
          assert(fnt.Kijun_Sen(gle)(i) == liste(i)
          )
      }
    }
  }

  "My function  " - {
    ": Senkou_SpanA for gle " - {
      "should return liste" in {
        val liste = Array(13.1065, 13.1065, 12.9955, 12.9875)
        for (i <- 0 to liste.length-1)
          assert(fnt.Senkou_SpanA(gle)(i) == liste(i)
          )
      }
    }
  }

  "My function  " - {
    ": envelopehaut for gle " - {
      "should return liste" in {
        val liste = Array(12.820496, 13.6058728, 13.85272564, 14.035732282000001, 14.224282614100002, 14.419350130705, 14.636687454535252, 14.849938320726764, 15.06995281203634, 15.272209588601816, 15.459850427430087)

        for (i <- 0 to liste.length-1)
          assert(fnt.enveloppesHaut(gle)(i) == liste(i)
          )
      }
    }
  }

  "My function  " - {
    ": envelopebas for gle " - {
      "should return liste" in {
        val liste = Array(11.834304, 12.5592672, 12.78713136, 12.956060568000002, 13.130107028400001, 13.31016935142, 13.510788419571002, 13.707635372978551, 13.910725672648928, 14.097424235632445, 14.27063116378162)

        for (i <- 0 to liste.length-1)
          assert(fnt.enveloppesBas(gle)(i) == liste(i)
          )
      }
    }
  }
  }
