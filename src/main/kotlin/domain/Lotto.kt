package domain

class Lotto(val lottoNumbers: Set<LottoNumber>) {

    init {
        require(lottoNumbers.size == 6) { "[Error] 로또번호는 6개여야합니다." }
    }

    constructor(lottoNumbers: List<Int>) : this(lottoNumbers.map { LottoNumber.of(it) }.toSet())
}
