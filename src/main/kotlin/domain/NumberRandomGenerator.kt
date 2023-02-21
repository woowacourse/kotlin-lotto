package domain

class NumberRandomGenerator : RandomGenerator {
    override fun generate(): Set<Int> =
        lottoNumbers.shuffled().take(6).toSet()

    companion object {
        private const val MINIMUM_GENERATOR_RANGE = 1
        private const val MAXIMUM_GENERATOR_RANGE = 45
        private val lottoNumbers = (MINIMUM_GENERATOR_RANGE..MAXIMUM_GENERATOR_RANGE).toList()
    }
}
