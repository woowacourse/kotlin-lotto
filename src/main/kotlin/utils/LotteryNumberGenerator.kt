package utils

import model.LotteryNumber

interface LotteryNumberGenerator {
    fun generateNumbers(): List<LotteryNumber>
}
