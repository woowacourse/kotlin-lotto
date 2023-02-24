package lotto.domain

class LotteryMachine {

    fun createWinningResult(winningLottery: WinningLottery, lotteries: Lotteries, amount: PurchaseAmount): WinningResult {
        val winningResult: MutableMap<Rank, Int> =
            Rank.values().associateWith { 0 }.toMutableMap()

        repeat(lotteries.size) {
            val countOfMatch = lotteries.get(it).countMatches(winningLottery.lottery)
            val matchBonus = lotteries.get(it).contains(winningLottery.bonusNumber)
            val rank = Rank.valueOf(countOfMatch, matchBonus)
            winningResult[rank] = winningResult[rank]!!.plus(1)
        }

        return WinningResult(winningResult, amount.toInt())
    }
}
