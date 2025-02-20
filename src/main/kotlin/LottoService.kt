import javax.print.DocFlavor.STRING
import kotlin.random.Random

class LottoService(private val random:Random) {

    fun getLotto():List<Int> {
        val lotto = mutableListOf<Int>()
        while (lotto.toSet().size != 6) {
            lotto.clear()
            repeat(6) { lotto.add(random.nextInt(1,46)) }
        }
        return lotto.toList()
    }

    fun getManyLotto(iterates:Int):List<List<Int>> {
        val manyLotto = mutableListOf<List<Int>>()
        repeat(iterates) {manyLotto.add(getLotto())}
        return manyLotto.toList()
    }

    fun checkRank(lotto:List<Int>, winningLotto:List<Int>, bonus:Int):Rank {
        var countOfMatch = 0
        lotto.forEach { num -> if (num in winningLotto) countOfMatch++ }
        return Rank.getRank(countOfMatch, (bonus in winningLotto))
    }

    fun checkRankMany(manyLotto:List<List<Int>>, winningLotto: List<Int>, bonus: Int):Map<Rank,Int> {
        val rankMap = mutableMapOf<Rank, Int>()
        for (rank in Rank.entries) rankMap.putIfAbsent(rank, 0)
        for (lotto in manyLotto) {
            val rank = checkRank(lotto, winningLotto, bonus)
            rankMap[rank] = rankMap.getOrDefault(rank, 0) + 1
        }
        return rankMap.toMap()
    }

    companion object {
        fun getRate(rankMap:Map<Rank, Int>):String {
            var total = 0
            for (rank in rankMap.keys) {
                total += rank.winningMoney * rankMap[rank]!!
            }

            val sum = rankMap.values.sum() * 1000
            if (sum == 0) return "0.0"
            return String.format("%.2f", (total.toDouble() / sum.toDouble()))
        }
    }
}