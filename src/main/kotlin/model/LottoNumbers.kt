package lotto.model

import model.LottoNumber

data class LottoNumbers(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_SIZE) {
            ERROR_LOTTO_SIZE
        }
        require(numbers.distinctBy { it.number }.size == LOTTO_SIZE) {
            ERROR_LOTTO_DUPLICATE
        }
    }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val ERROR_LOTTO_SIZE = "로또 번호는 6개여야 합니다."
        private const val ERROR_LOTTO_DUPLICATE = "로또 번호는 중복될 수 없습니다."

        fun lottoNumbersOf(vararg numbers: Int): LottoNumbers {
            return LottoNumbers(numbers.map { LottoNumber(it) }.toList())
        }
    }
}
