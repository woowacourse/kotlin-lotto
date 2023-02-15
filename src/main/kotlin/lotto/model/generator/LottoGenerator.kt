package lotto.model.generator

class LottoGenerator: NumberGenerator {
    override fun generate(): List<Int> {
        return (START_LOTTO_RANGE..END_LOTTO_RANGE).shuffled().sorted().subList(0, SIZE_OF_LOTTO)
    }

    companion object {
        private const val SIZE_OF_LOTTO = 6
        private const val START_LOTTO_RANGE = 1
        private const val END_LOTTO_RANGE = 45
    }
}
