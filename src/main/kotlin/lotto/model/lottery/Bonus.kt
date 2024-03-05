package lotto.model.lottery

class Bonus(val lotteryNumber: LotteryNumber) {
    companion object {
        const val EXCEPTION_DUPLICATED_NUMBER = "보너스 번호는 로또 번호에 포함될 수 없습니다"

        fun fromInput(
            input: Int,
            winningLottery: Lottery,
        ): Bonus {
            val lotteryNumber = LotteryNumber.from(input)
            require(validateUniqueWithWinning(lotteryNumber, winningLottery)) { EXCEPTION_DUPLICATED_NUMBER }
            return Bonus(lotteryNumber)
        }

        private fun validateUniqueWithWinning(
            number: LotteryNumber,
            winningLottery: Lottery,
        ) = !winningLottery.hasLotteryNumber(number)
    }
}
