package lotto.entity

class Lotto(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_NUMBER_COUNT) { ERROR_MESSAGE_WIN_NUMBER_IS_SIX.format(numbers.size) }
    }

    constructor(numbers: List<Int>) : this(numbers.map { LottoNumber.from(it) }.toSet())

    fun matchLottoNumberCount(lotto: Lotto): Int = numbers.intersect(lotto.numbers).size

    fun isMatchBonus(bonus: LottoNumber): Boolean = numbers.contains(bonus)

    override fun toString(): String = "[${numbers.joinToString(", ") { it.value.toString() }}]"

    companion object {
        const val ERROR_MESSAGE_WIN_NUMBER_IS_SIX = "로또 번호는 6개여야 합니다. 입력된 로또 번호 개수는 %d 입니다."
        const val LOTTO_NUMBER_COUNT = 6
    }
}
