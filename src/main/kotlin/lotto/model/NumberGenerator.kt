package lotto.model

class NumberGenerator {
    fun generateLottoNumbers(): List<Int> {
        return (START_RANGE..END_RANGE).map { it }
            .shuffled()
            .take(Lotto.LOTTO_SIZE)
            .sorted()
    }

    companion object {
        const val START_RANGE = 1
        const val END_RANGE = 45
    }
}
