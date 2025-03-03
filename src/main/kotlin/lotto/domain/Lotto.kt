package lotto.domain

import lotto.global.LottoException

data class Lotto(
    val value: Set<LottoNumber>,
) {
    init {
        require(value.size == MAX_LOTTO_LENGTH) { LottoException.ERR_NOT_SIX_ELEMENTS.msg }
    }

    fun contains(element: LottoNumber): Boolean = value.contains(element)

    fun getCountOfMatchWith(contrast: Lotto): Int = value.intersect(contrast.value).size

    companion object {
        const val LOTTO_PRICE = 1000
        const val MAX_LOTTO_LENGTH = 6

        fun of(vararg numbers: Int): Lotto = of(numbers.toList())

        fun of(numbers: List<Int>): Lotto {
            require(numbers.distinct().size == numbers.size) { LottoException.ERR_ELEMENT_DUPLICATED.msg }
            return Lotto(
                numbers
                    .map {
                        LottoNumber.of(it)
                    }.toSet(),
            )
        }

        fun generateRandomLotto(count: Int): List<Lotto> =
            List(count) {
                val lottoNumbers =
                    (LottoNumber.MIN_LOTTO_NUMBER..LottoNumber.MAX_LOTTO_NUMBER)
                        .shuffled()
                        .take(MAX_LOTTO_LENGTH)
                of(lottoNumbers)
            }
    }
}
