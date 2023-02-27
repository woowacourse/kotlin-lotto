package domain

class Lotto(val lottoNumbers: List<LottoNumber>) {

    init {
        require(lottoNumbers.size == lottoNumbers.distinct().size) { "[Error] 중복된 수가 있습니다." }
        require(lottoNumbers.size == 6) { "[Error] 로또번호는 서로 다른 숫자 6개여야합니다." }
    }

    constructor(lottoNumber: String) : this(convertLotto(lottoNumber))

    companion object {
        private const val SEPARATOR = ", "

        private fun convertLotto(lottoNumber: String): List<LottoNumber> {
            return lottoNumber.split(SEPARATOR)
                .map { LottoNumber.of(it) }
                .sortedBy { it.number }
        }
    }
}
