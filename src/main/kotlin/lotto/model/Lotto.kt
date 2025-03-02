package lotto.model

class Lotto(
    val numbers: Set<LottoNumber>,
) {
    init {
        require(numbers.size == LOTTO_NUMBERS_SIZE) { LOTTO_COUNT_MESSAGE }
    }

    fun match(winning: WinningLotto): Rank {
        val matchCount = numbers.count { winning.contains(it) }
        val bonusMatch = winning.isBonusMatch(this)
        return Rank.valueOf(matchCount, bonusMatch)
    }

    fun contains(number: LottoNumber): Boolean = numbers.contains(number)

    companion object {
        const val LOTTO_NUMBERS_SIZE: Int = 6
        const val LOTTO_COUNT_MESSAGE = "로또 번호는 6개여야 합니다."

        fun from(numbers: List<Int>): Lotto? {
            val lottoNumbers = numbers.mapNotNull { LottoNumber.from(it) }.toSet()
            return if (lottoNumbers.size == LOTTO_NUMBERS_SIZE) Lotto(lottoNumbers) else null
        }
    }
}
