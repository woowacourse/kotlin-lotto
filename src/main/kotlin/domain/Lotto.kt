package domain

import domain.model.LottoNumber

class Lotto(val numbers: Set<LottoNumber>) {

    constructor(vararg numbers: Int) : this(numbers.sorted().map { LottoNumber(it) }.toSet())

    init {
        require(numbers.size == LOTTO_SIZE) { "입력된 로또 숫자는 ${numbers.size}개입니다. 로또 숫자는 고유한 ${LOTTO_SIZE}개여야 합니다." }
    }

    operator fun contains(number: LottoNumber): Boolean {
        return numbers.any { it == number }
    }

    override fun toString(): String {
        return numbers.map { it.value }.joinToString(", ")
    }

    companion object {
        const val LOTTO_SIZE = 6

        fun makeLotto(makeLottoStrategy: MakeLottoStrategy): Lotto {
            return makeLottoStrategy.makeLotto()
        }
    }
}
