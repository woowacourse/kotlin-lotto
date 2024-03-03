package lotto.model.lottery.strategy

import lotto.model.lottery.LotteryNumber

interface LotteryNumberGenerator {
    fun generateNumbers(): Set<LotteryNumber>
}
