package domain

data class Count(private val count: Int) {
    init {
        require(count >= 0) { ERROR_MINUS.format(count) }
    }

    fun toInt() = count

    fun minusToInt(number: Int): Int = number - count

    companion object {
        private const val ERROR_MINUS = "0 이상의 숫자를 입력해야 합니다.\n잘못된 값 : %d"
    }
}
