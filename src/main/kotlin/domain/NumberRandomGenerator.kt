package domain

class NumberRandomGenerator : RandomGenerator {
    override fun generate(): Set<Int> =
        (MINIMUM_GENERATOR_RANGE..MAXIMUM_GENERATOR_RANGE).toList().shuffled().take(6).toSet()

    companion object {
        private const val MINIMUM_GENERATOR_RANGE = 1
        private const val MAXIMUM_GENERATOR_RANGE = 45
    }
}
