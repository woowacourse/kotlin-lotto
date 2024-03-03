package lotto.model.lottery.generator

import lotto.model.lottery.LotteryNumber

interface LotteryNumberGenerator {
    fun generateNumbers(): Set<LotteryNumber>
}
