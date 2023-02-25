package domain

class Lotto {
    val lottoNumbers: Set<LottoNumber>

    constructor(lottoNumbers: Set<LottoNumber>) {
        require(lottoNumbers.size == 6) { "[Error] 로또번호는 서로 다른 숫자 6개여야합니다." }
        this.lottoNumbers = lottoNumbers
    }

    constructor(lottoNumbers: List<String>) {
        require(lottoNumbers.size == lottoNumbers.distinct().size) { "[Error] 중복된 수가 있습니다." }
        require(lottoNumbers.size == 6) { "[Error] 로또번호는 서로 다른 숫자 6개여야합니다." }
        this.lottoNumbers = lottoNumbers
            .map { LottoNumber.of(it) }
            .sortedBy { it.number }
            .toSet()
    }
}
