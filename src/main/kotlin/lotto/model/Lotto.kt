package lotto.model

import java.util.*

data class Lotto(val numbers: TreeSet<LottoNumber>) {

    constructor(vararg numbers: Int) : this(TreeSet(numbers.map { LottoNumber.from(it) }))
    constructor(numbers: List<Int>) : this(TreeSet(numbers.map { LottoNumber.from(it) }))

    init {
        require(numbers.size == LOTTO_SIZE) { "입력된 로또 숫자는 ${numbers.size}개입니다. 로또 숫자는 고유한 ${LOTTO_SIZE}개여야 합니다." }
    }

    fun countMatch(lotto: Lotto): Int = (lotto.numbers intersect numbers).size

    operator fun contains(number: LottoNumber): Boolean = numbers.contains(number)

    override fun toString(): String = numbers.joinToString(", ") { "${it.value}" }

    companion object {
        const val LOTTO_SIZE = 6

        fun makeLotto(makeLottoStrategy: MakeLottoStrategy): Lotto = makeLottoStrategy.makeLotto()
    }
}
