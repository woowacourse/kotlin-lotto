package lotto.domain

class WinningLotto(val winningNumber: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!winningNumber.numbers.contains(bonusNumber)) { ERROR_DUPLICATE_BONUS_NUMBER }
    }

    companion object {
        private const val ERROR_DUPLICATE_BONUS_NUMBER: String = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}