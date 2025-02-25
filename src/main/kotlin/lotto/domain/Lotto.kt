package lotto.domain

class Lotto(private val lotto: Set<LottoNumber>) {
    init {
        validateLottoSize()
    }

    fun getSortedLotto() = lotto.sortedBy { it.number }.toSet()

    private fun validateLottoSize() {
        runCatching { lotto.size == LOTTO_SIZE }
            .onFailure { ERROR_LOTTO_SIZE }
    }

    companion object {
        const val LOTTO_SIZE = 6
        const val ERROR_LOTTO_SIZE = "[ERROR] 로또 번호는 중복되지 않는 숫자 6개여야 합니다."
    }
}
