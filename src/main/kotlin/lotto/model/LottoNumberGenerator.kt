package lotto.model

class LottoNumberGenerator {
    fun generateManual(numbers: List<Int>): Set<LottoNumber> {
        return numbers.map { LottoNumber(it) }.toSet()
    }

    fun generateAuto(): Set<LottoNumber> {
        return (START_RANGE..END_RANGE).map { it }
            .shuffled()
            .take(Lotto.LOTTO_SIZE)
            .sorted()
            .map { LottoNumber(it) }
            .toSet()
    }

    companion object {
        const val START_RANGE = 1
        const val END_RANGE = 45
    }
}
