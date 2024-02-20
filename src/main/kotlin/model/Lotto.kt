package model

class Lotto(private val numbers: List<Int>) {
        init {
            require(numbers.size == LOTTO_SIZE) {
                ERROR_LOTTO_SIZE
            }
        }

        companion object {
            const val LOTTO_SIZE = 6
            const val ERROR_LOTTO_SIZE = "로또 번호는 6개여야 합니다."
        }
    }
