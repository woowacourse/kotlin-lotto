package lotto.model.generator

import lotto.model.LotteryNumber

interface LotteryNumberGenerator {
    fun generateNumbers(): Set<LotteryNumber>
}
