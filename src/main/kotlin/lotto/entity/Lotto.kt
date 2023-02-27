package lotto.entity

import lotto.model.Rank

class Lotto private constructor(private val numbers: Set<LottoNumber>) {

    init {
        require(numbers.size == LOTTO_COUNT) { String.format(ERROR_MESSAGE_LOTTO_NUMBER_IS_SIX, numbers) }
    }

    fun determineRank(countOfMatch: Int, matchBonus: Boolean): Rank {
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    fun determineCountOfMatch(winLotto: Lotto): Int {
        return numbers.intersect(winLotto.numbers).size
    }

    fun determineMatchBonus(bonus: LottoNumber): Boolean {
        return numbers.contains(bonus)
    }

    override fun toString(): String {
        return numbers.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (other === numbers) return true

        if (javaClass != other?.javaClass) return false

        other as Lotto

        if (numbers != other.numbers) return false

        return true
    }

    override fun hashCode(): Int {
        return numbers.hashCode()
    }

    companion object {
        const val LOTTO_COUNT = 6
        private const val ERROR_MESSAGE_LOTTO_NUMBER_IS_SIX = "로또 번호는 중복되지 않는 숫자 6개여야 합니다. 입력된 정보 : %s"

        fun from(value: List<LottoNumber>): Lotto {
            return Lotto(value.toSet())
        }
    }
}
