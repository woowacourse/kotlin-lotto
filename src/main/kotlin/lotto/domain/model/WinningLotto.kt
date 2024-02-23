package lotto.domain.model

data class WinningLotto(val lotto: Lotto, val bonus: LottoNumber) {
    init {
        require(lotto.numbers.contains(bonus).not()) { DUPLICATED_BONUS_NUMBER_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val DUPLICATED_BONUS_NUMBER_EXCEPTION_MESSAGE = "보너스 번호는 당첨번호와 중복되면 안됩니다."
    }
}
