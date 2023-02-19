package lotto.entity

class WinLotto(val winNumber: Lotto, val bonus: LottoNumber) {
    init {
        require(!winNumber.numbers.contains(bonus)) { ERROR_MESSAGE_DUPLICATED_BONUS_NUMBER }
    }

    companion object {
        private const val ERROR_MESSAGE_DUPLICATED_BONUS_NUMBER = "보너스 번호와 당첨 번호는 중복될 수 없습니다"
    }
}
