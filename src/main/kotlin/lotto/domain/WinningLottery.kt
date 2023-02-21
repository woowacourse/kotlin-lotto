package lotto.domain

class WinningLottery(
    val lottery: Lottery,
    val bonusNumber: LotteryNumber
) {
    init {
        checkBonusNumberDuplicate()
    }

    fun calculateResult(lotteries: List<Lottery>): WinningResult {
        val winningResult = WinningResult()

        repeat(lotteries.size) {
            val countOfMatch = lotteries[it].countMatches(lottery)
            val matchBonus = lotteries[it].containBonusNumber(bonusNumber)
            winningResult.countRank(Rank.valueOf(countOfMatch, matchBonus))
        }

        return winningResult
    }

    private fun checkBonusNumberDuplicate() {
        require(!lottery.numbers.contains(bonusNumber)) { BONUS_NUMBER_DUPLICATE_ERROR }
    }

    companion object {
        private const val BONUS_NUMBER_DUPLICATE_ERROR = "보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
