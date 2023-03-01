package model

class Money(private val value: Int) {

    fun requireOverMinimumPrice(): Boolean? {
        if (value < MINIMUM_PRICE) return null
        return true
    }

    fun requireCheckPriceUnit(): Boolean? {
        if (value % MINIMUM_PRICE != DIVISIBLE) return null
        return true
    }

    companion object {
        private const val MINIMUM_PRICE = 1000
        private const val DIVISIBLE = 0
    }
}
