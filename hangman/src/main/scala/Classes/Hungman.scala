package Classes

class Hungman() {
    //takes the attempts from the game.attempts and draws ascii characcter print
    def draw(attemptsLeft:Int): Unit = {
      if(attemptsLeft < 6) println("\u2796"*3)
      if(attemptsLeft < 5) println("    \u26D3")
      if(attemptsLeft < 4) println("    \uD83D\uDE14")
      if(attemptsLeft < 3) println("  \ud83d\udcaa"+ "\uD83D\uDFE8"	+"\uD83E\uDD33")
      if(attemptsLeft < 2) println("    \uD83D\uDFE8 	 ")
      if(attemptsLeft < 1) println("   \uD83E\uDDB5" + "\uD83E\uDDB5" + "\n You lose :(." )
    }
  }


