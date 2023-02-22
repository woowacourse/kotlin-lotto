package domain

class Lotto(val lottoNumbers: Set<LottoNumber>) {

    init {
        require(lottoNumbers.size == 6) { "[Error] 로또번호는 6개여야합니다." }
    }

    constructor(lottoNumbers: List<String>) : this(
        lottoNumbers
            .map { LottoNumber.of(it.toIntOrNull() ?: throw NumberFormatException("[Error] 숫자로만 입력해주세요.")) }
            .toSet()
    ) {
        require(lottoNumbers.size == lottoNumbers.distinct().size) { "[Error] 중복된 수가 있습니다." }
    }
}
