package domain

class Lotto(val lottoNumbers : Set<LottoNumber>) {

    init {
        require(lottoNumbers.size == 6){"$PREFIX 로또번호는 6개여야합니다."}
    }

    companion object{
        const val PREFIX = "[Error]"
    }
}