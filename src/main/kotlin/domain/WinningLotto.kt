package domain

class WinningLotto(val winningLotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!winningLotto.contains(bonusNumber)) { ERROR_CONTAIN_BONUS_NUMBER_IN_LOTTO }
    }

    fun match(lotto: Lotto): Int {
        return lotto.numbers.count { number ->
            winningLotto.contains(number)
        }
    }

    companion object {
        const val ERROR_CONTAIN_BONUS_NUMBER_IN_LOTTO = "로또 번호와 보너스 번호는 같을 수 없습니다."
    }
}
