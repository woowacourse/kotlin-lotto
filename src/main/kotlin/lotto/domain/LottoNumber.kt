package lotto.domain

class LottoNumber private constructor(val lottoNumber: Int) {
    init {
        require(lottoNumber in MIN_RANGE..MAX_RANGE) { LOTTO_NUM_ERROR_MESSAGE }
    }

    companion object {
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 45

        // [1~45] 범위의 LottoNumber 객체를 미리 생성하여 캐싱
        private val CACHE = (MIN_NUMBER..MAX_NUMBER).associateWith { LottoNumber(it) }

        // 팩토리 메서드 - 기존 객체를 재사용하도록 함
        fun of(number: Int): LottoNumber {
            return CACHE[number] ?: throw IllegalArgumentException("잘못된 로또 번호: $number")
        }

        private const val MIN_RANGE = 1
        private const val MAX_RANGE = 45
        private const val LOTTO_NUM_ERROR_MESSAGE = "로또 번호는 1에서 45까지의 숫자이다"
    }
}
