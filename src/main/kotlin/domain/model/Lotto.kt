package domain.model

class Lotto(val lottoNumbers: List<LottoNumber>) {
    init {
        require(lottoNumbers.size == LOTTO_SIZE) { INVALID_LOTTO_SIZE }
        require(lottoNumbers.all { it.value in (1..45) }) { INVALID_LOTTO_NUMBERS }
        require(lottoNumbers.size == lottoNumbers.toSet().size) { DUPLICATED_LOTTO_NUMBERS }
    }

    companion object {
        fun of(vararg numbers: Int): Lotto = Lotto(numbers.map { LottoNumber(it) })

        fun List<LottoNumber>.toValues() = this.map { it.value }

        const val ERROR = "[ERROR]"
        const val LOTTO_SIZE = 6
        const val INVALID_LOTTO_SIZE = "$ERROR 로또 번호는 6개 입니다."
        const val INVALID_LOTTO_NUMBERS = "$ERROR 로또 번호는 1부터 45 사이입니다."
        const val DUPLICATED_LOTTO_NUMBERS = "$ERROR 로또 번호는 중복이 없습니다."
    }
}
