package lotto.domain.model

class Lotto(val numbers: List<LottoNumber>) : LottoNumbers() {
    init {
        validateLottoNumbers(numbers)
    }
}
