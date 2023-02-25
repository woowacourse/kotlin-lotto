package lotto.domain

class LotteryMachine {

    fun createWinningResult(winningLottery: WinningLottery, lotteries: Lotteries, amount: PurchaseAmount): WinningResult {
        val winningResult: MutableMap<Rank, Int> =
            Rank.values().associateWith { 0 }.toMutableMap()

        repeat(lotteries.size) {
            val countOfMatch: Int = lotteries.get(it).countMatches(winningLottery.lottery)
            val matchBonus: Boolean = lotteries.get(it).contains(winningLottery.bonusNumber)
            val rank: Rank = Rank.valueOf(countOfMatch, matchBonus)
            winningResult[rank] = winningResult[rank]!!.plus(1)
        }

        return WinningResult(winningResult, amount.toInt())
    }

    fun generateLotteries(count: Int): Lotteries {
        val generator: LotteryGenerator = LotteryGenerator()
        return generator.generateLotteries(count)
    }
}
