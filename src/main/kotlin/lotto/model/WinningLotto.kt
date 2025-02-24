package lotto.model

class WinningLotto(
    val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    private val lottoNumbers: List<Int> = lotto.numbers.map { it.number }

    init {
        require(!lotto.numbers.map { it.number }.contains(bonusNumber.number)) { ERROR_LOTTO_BONUS_DUPLICATED_MESSAGE }
    }

    fun getCountOfMatch(lotto: Lotto): Int = lotto.numbers.count { it.number in lottoNumbers }

    fun isBonusNumberMatch(lotto: Lotto): Boolean = lotto.numbers.map { it.number }.contains(this.bonusNumber.number)

    companion object {
        private const val ERROR_LOTTO_BONUS_DUPLICATED_MESSAGE = "당첨 번호와 보너스 번호는 중복될 수 없습니다."
    }
}
