import akka.actor.Actor
import java.security.MessageDigest

class Worker extends Actor{
 
  var listOfCoins: Map[String,String] = Map()
  
  def startMining(length: Int,start: Long)= {
  clear

    while(System.currentTimeMillis() != start + (2 * 60 * 1000)){ 
     val x = randomStringGenerator(length)
     val y: String = "arai"
     
     val input: String = y + x
     val output = sha256(input)
     if(output.charAt(0).equals('0'))
    	 listOfCoins+= input -> output
    	 
     if(listOfCoins.size == 1000){
       sender ! BitCoin.ReturnBitCoin(listOfCoins)
       listOfCoins = scala.collection.immutable.Map.empty
     }
    	 
   }
   sender ! BitCoin.ReturnBitCoin(listOfCoins)
  }
  
  def randomStringGenerator(n: Int): String = {
    n match {
      case 1 => util.Random.nextPrintableChar.toString
      case _ => util.Random.nextPrintableChar.toString ++ randomStringGenerator(n-1).toString
    }
  }
  
  def sha256(s: String) = {
    val m = MessageDigest.getInstance("SHA-256").digest(s.getBytes("UTF-8"))
    m.map("%02x".format(_)).mkString
}
  
  def receive = {
    case BitCoin.StartBitMining(length, noOfZeros, start) =>
      startMining(length, start)
      sender ! BitCoin.DoneMining
      context.stop(self)
  }
}
