package domain

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!lotto.numbers.contains(bonusNumber)) { ERROR_CONTAIN_BONUS_NUMBER_IN_LOTTO.format(bonusNumber.toInt()) }
    }

    companion object {
        private const val ERROR_CONTAIN_BONUS_NUMBER_IN_LOTTO = "[ERROR] 중복 발생 번호 %d, 로또 번호와 보너스 번호는 같을 수 없습니다."
    }
}
