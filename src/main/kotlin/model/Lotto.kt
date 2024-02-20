package model

class Lotto(val numbers: List<LottoNumber>) {
        init {
            require(numbers.size == LOTTO_SIZE) {
                ERROR_LOTTO_SIZE
            }

            require(numbers.distinctBy { it.number }.size == LOTTO_SIZE) {
                ERROR_LOTTO_DUPLICATE
            }
        }

        companion object {
            const val LOTTO_SIZE = 6
            const val ERROR_LOTTO_SIZE = "로또 번호는 6개여야 합니다."
            const val ERROR_LOTTO_DUPLICATE = "로또 번호는 중복될 수 없습니다."
        }
    }
