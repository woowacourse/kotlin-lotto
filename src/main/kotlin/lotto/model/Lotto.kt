package lotto.model

class Lotto(val lottoNumbers: Set<LottoNumber>) {
    init {
        require(lottoNumbers.size == LOTTO_SIZE) { LOTTO_SIZE_ERROR_MESSAGE }
    }

    override fun toString(): String = lottoNumbers.toString()

    fun getCountOfMatch(lotto: Lotto): Int = lottoNumbers.intersect(lotto.lottoNumbers.toSet()).size

    companion object {
        private const val LOTTO_SIZE = 6

        private const val LOTTO_SIZE_ERROR_MESSAGE = "로또의 숫자들은 중복되지 않는 6개입니다."
    }
}
