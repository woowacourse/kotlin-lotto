package lotto.domain

import lotto.global.Message

const val LOTTO_PRICE = 1000
const val MAX_LOTTO_LENGTH = 6

data class Lotto(
    val value: Set<LottoNumber>,
) {
    init {
        require(value.size == MAX_LOTTO_LENGTH) { Message.ERR_NOT_SIX_ELEMENTS.msg }
    }

    fun contains(element: LottoNumber): Boolean = value.contains(element)

    fun getCountOfMatchWith(contrast: Lotto): Int = value.count { it in contrast.value }

    companion object {
        fun of(vararg numbers: Int): Lotto = of(numbers.toList())

        fun of(numbers: List<Int>): Lotto {
            require(numbers.distinct().size == numbers.size) { Message.ERR_ELEMENT_DUPLICATED.msg }
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
