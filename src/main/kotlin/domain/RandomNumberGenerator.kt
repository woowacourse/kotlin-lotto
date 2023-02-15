package domain

class RandomNumberGenerator : NumberGenerator {
    override fun generateNumbers(): Set<Int> =
        (MINIMUM_NUMBER..MAXIMUM_NUMBER).shuffled().take(6).sorted().toSet()
    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
    }
}
