package lotto.domain

data class Lotto private constructor(val lotto: Set<LottoNumber>) {
    companion object {
        private const val LOTTO_SIZE = 6
        private const val ERROR_INVALID_LOTTO_SIZE = "로또 번호는 중복되지 않는 숫자 6개여야 합니다."

        fun of(lotto: Set<LottoNumber>): Lotto {
            if (lotto.size != LOTTO_SIZE) {
                throw IllegalArgumentException(ERROR_INVALID_LOTTO_SIZE)
            }
            return Lotto(lotto.sortedBy { it.number }.toSet())
        }
    }
}
