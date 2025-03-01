package lotto.domain

import lotto.global.Message

const val LOTTO_PRICE = 1000
const val MAX_LOTTO_LENGTH = 6

data class Lotto(
    val value: List<LottoNumber>,
) {
    init {
        requireValidLotto(value)
    }

    fun contains(element: LottoNumber): Boolean = value.contains(element)

    fun getCountOfMatchWith(contrast: Lotto): Int = value.count { it in contrast.value }

    private fun requireValidLotto(input: List<LottoNumber>): List<LottoNumber> {
        var winningLotto = input.toList()
        require(input.size == MAX_LOTTO_LENGTH) { Message.ERR_NOT_SIX_ELEMENTS.msg }
        winningLotto = winningLotto.distinct()
        require(winningLotto.size == MAX_LOTTO_LENGTH) { Message.ERR_ELEMENT_DUPLICATED.msg }
        return input
    }

    companion object {
        fun of(vararg numbers: Int): Lotto = Lotto(numbers.map { LottoNumber.of(it) })

        fun of(numbers: List<Int>): Lotto = Lotto(numbers.map { LottoNumber.of(it) })

        private fun generateRandomLotto(): Lotto {
            val lotto = mutableListOf<LottoNumber>()
            val range = IntRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER).shuffled()
            for (i in 0 until MAX_LOTTO_LENGTH) {
                lotto.add(LottoNumber.of(range.get(i)))
            }

            return Lotto(lotto.toList())
        }

        fun generateRandomLotto(count: Int): List<Lotto> {
            val manyLotto = mutableListOf<Lotto>()
            repeat(count) { manyLotto.add(generateRandomLotto()) }
            return manyLotto.toList()
        }
    }
}
