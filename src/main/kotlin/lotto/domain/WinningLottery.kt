package lotto.domain

class WinningLottery(
    val lottery: Lottery,
    val bonusNumber: LotteryNumber
) {
    init {
        checkBonusNumberDuplicate()
    }

    private fun checkBonusNumberDuplicate() {
        require(!lottery.numbers.contains(bonusNumber)) {
            "$BONUS_NUMBER_DUPLICATE_ERROR\n" +
                "오류값 : ${bonusNumber.toInt()}"
        }
    }

    companion object {
        private const val BONUS_NUMBER_DUPLICATE_ERROR = "보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
