package domain

class Lotto(val lottoNumbers : Set<LottoNumber>) {

    constructor(lottoNumbers : List<Int>) : this(lottoNumbers.map { LottoNumber.create(it) }.toSet())

    init {
        require(lottoNumbers.size == 6){"$PREFIX 로또번호는 6개여야합니다."}
    }

    companion object{
        const val PREFIX = "[Error]"
    }
}