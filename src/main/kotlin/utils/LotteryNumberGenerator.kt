package utils

import model.LotteryNumber

interface LotteryNumberGenerator {
    fun generateNumbers(): Set<LotteryNumber>
}
