package galaxyraiders.core.score

import galaxyraiders.core.game.Asteroid
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.io.File
import kotlinx.serialization.Serializable

@Serializable
data class PlayerScore(var time : Long, var score: Double, var asteroids : Int)

class ScoreHandler {
    var scoreboard: List<PlayerScore>
    var leaderboard: List<PlayerScore>
    
    val scoreFile: File
    val leaderFile: File

    val scoreboardPath : String = "./src/main/kotlin/galaxyraiders/core/score/Scoreboard.json"
    val leaderboardPath : String = "./src/main/kotlin/galaxyraiders/core/score/Leaderboard.json"

    init {
        scoreFile = File(this.scoreboardPath)
        if (!scoreFile.exists()) {
            scoreFile.getParentFile().mkdirs();
            scoreFile.createNewFile();
            scoreFile.writeText("[]")
        }
        val scoreboardText = File(this.scoreboardPath).readText()
        this.scoreboard = Json.decodeFromString<List<PlayerScore>>(scoreboardText)
        this.scoreboard += PlayerScore(System.currentTimeMillis(), 0.0, 0)
        
        leaderFile = File(this.leaderboardPath)
        if (!leaderFile.exists()) {
            leaderFile.getParentFile().mkdirs();
            leaderFile.createNewFile();
            leaderFile.writeText("[]")
        }
        val leaderboardText = File(this.leaderboardPath).readText()
        this.leaderboard = Json.decodeFromString<List<PlayerScore>>(leaderboardText)
    }
    private fun scoreFromAsteroid(asteroid: Asteroid): Double{
        return asteroid.mass / (asteroid.radius * asteroid.radius)
    }


    fun updateScores(asteroid: Asteroid) {
        this.scoreboard [this.scoreboard.size - 1].score += scoreFromAsteroid(asteroid)
        this.scoreboard [this.scoreboard.size - 1].asteroids += 1
        this.saveScores()
    }

    fun saveScores() {
        val leaderboardCandidates : MutableList<PlayerScore> = mutableListOf()
        leaderboardCandidates.add(this.scoreboard[this.scoreboard.size - 1])
        leaderboardCandidates.addAll(this.leaderboard)
        leaderboardCandidates.sortBy { - it.score }

        if(leaderboardCandidates.size > 3){
            leaderboardCandidates.removeLast()
        }
        
        val scoreboardText: String = Json.encodeToString(this.scoreboard)
        scoreFile.writeText(scoreboardText)
        val leaderboardText: String = Json.encodeToString(leaderboardCandidates)
        leaderFile.writeText(leaderboardText)
    }
}