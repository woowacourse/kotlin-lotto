package lotto.domain

class WinningLotto(
    val winningLotto: Lotto,
    val bounusNumber: String,
) {
    init {
        require(bounusNumber.all { it.isDigit() }) { BONUS_BALL_NUMBER_ERROR_MESSAGE }
        require(bounusNumber.isNotBlank()) { BONUS_BALL_NULL_ERROR_MESSAGE }
        require(!winningLotto.lottoNums.contains(LottoNumber(bounusNumber.toInt()))) { BONUS_BALL_DUPLICATE_ERROR_MESSAGE }
    }

    companion object {
        private const val BONUS_BALL_NUMBER_ERROR_MESSAGE = "보너스 볼 번호는 숫자로 입력해주세요"
        private const val BONUS_BALL_NULL_ERROR_MESSAGE = "보너스 볼 번호는 공백이 불가합니다"
        private const val BONUS_BALL_DUPLICATE_ERROR_MESSAGE = "보너스 볼 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
