package lotto.domain

class WinningLotto(
    val winningLotto: Lotto,
    val bounusNumber: Int,
) {
    init {
        require(!winningLotto.lottoNums.contains(LottoNumber(bounusNumber))) { BONUS_BALL_DUPLICATE_ERROR_MESSAGE }
    }

    companion object {
        private const val BONUS_BALL_DUPLICATE_ERROR_MESSAGE = "보너스 볼 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
