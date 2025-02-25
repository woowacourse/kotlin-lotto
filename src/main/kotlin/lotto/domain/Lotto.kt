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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Lotto

        return lotto == other.lotto
    }

    override fun hashCode(): Int {
        return lotto.hashCode()
    }

    companion object {
        const val LOTTO_SIZE = 6
        const val ERROR_LOTTO_SIZE = "[ERROR] 로또 번호는 중복되지 않는 숫자 6개여야 합니다."
    }
}
