package model.lottery

class LottoBonusNumberChecker {
    fun containsBonusNumber(
        lottery: Lottery,
        bonusNumber: LotteryNumber,
    ): Boolean = lottery.lotteryNumbers.any { it == bonusNumber }
}
