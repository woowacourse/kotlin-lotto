package lotto.model

import java.util.*

data class Lotto(val numbers: Set<LottoNumber>) {

    constructor(vararg numbers: Int) : this(TreeSet(numbers.sorted().map { LottoNumber.from(it) }))
    constructor(numbers: List<Int>) : this(TreeSet(numbers.sorted().map { LottoNumber.from(it) }))

    init {
        require(numbers.size == LOTTO_SIZE) { "입력된 로또 숫자는 ${numbers.size}개입니다. 로또 숫자는 고유한 ${LOTTO_SIZE}개여야 합니다." }
    }

    fun countMatch(lotto: Lotto): Int = (lotto.numbers intersect numbers).size

    operator fun contains(number: LottoNumber): Boolean = numbers.contains(number)

    override fun toString(): String = numbers.joinToString(", ") { "${it.value}" }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val LOTTO_MINIMUM_NUMBER = 1
        private const val LOTTO_MAXIMUM_NUMBER = 45

        fun makeRandomLotto(numberGenerator: (IntRange) -> List<Int> = { it.shuffled().take(LOTTO_SIZE) }): Lotto {
            val randomNumbers = numberGenerator(LOTTO_MINIMUM_NUMBER..LOTTO_MAXIMUM_NUMBER)
            return Lotto(randomNumbers)
        }
    }
}
