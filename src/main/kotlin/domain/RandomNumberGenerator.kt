package domain

class RandomNumberGenerator : NumberGenerator {
    override fun generateNumber(): Int = (MINIMUM_NUMBER..MAXIMUM_NUMBER).random()
    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
    }
}
