package domain

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!lotto.numbers.contains(bonusNumber)) { ERROR_CONTAIN_BONUS_NUMBER_IN_LOTTO }
    }

    fun getWinningNumbers(): Set<LottoNumber> = lotto.numbers

    companion object {
        const val ERROR_CONTAIN_BONUS_NUMBER_IN_LOTTO = "로또 번호와 보너스 번호는 같을 수 없습니다."
    }
}
