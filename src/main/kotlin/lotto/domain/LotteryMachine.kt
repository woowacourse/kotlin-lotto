package lotto.domain

class LotteryMachine {

    fun createWinningResult(winningLottery: WinningLottery, lotteries: List<Lottery>, amount: PurchaseAmount): WinningResult {
        val winningResult: MutableMap<Rank, Int> =
            Rank.values().associateWith { 0 }.toMutableMap()

        repeat(lotteries.size) {
            val countOfMatch = lotteries[it].countMatches(winningLottery.lottery)
            val matchBonus = lotteries[it].contains(winningLottery.bonusNumber)
            val rank = Rank.valueOf(countOfMatch, matchBonus)
            winningResult[rank] = winningResult[rank]!!.plus(1)
        }

        return WinningResult(winningResult, amount.toInt())
    }
}
