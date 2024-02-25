package domain

import domain.model.LottoNumber

class Lotto(val numbers: Set<LottoNumber>) {

    constructor(vararg numbers: Int): this(numbers.sorted().map { LottoNumber(it) }.toSet())

    init {
        require(numbers.size == LOTTO_SIZE) { "로또 번호는 크기는 ${numbers.size}이면 안됩니다. 고유한 ${LOTTO_SIZE}개여야 합니다." }
    }

    operator fun contains(number: LottoNumber): Boolean {
        return numbers.any { it == number }
    }

    companion object {
        const val LOTTO_SIZE = 6

        fun makeRandomLotto(range: IntRange): Lotto {
            val randomNumbers = (range).shuffled().take(LOTTO_SIZE).sorted()
            val lottoNumbers = randomNumbers.map { LottoNumber(it) }.toSet()
            return Lotto(lottoNumbers)
        }
    }
}
