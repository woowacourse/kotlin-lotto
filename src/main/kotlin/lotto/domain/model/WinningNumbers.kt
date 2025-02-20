package lotto.domain.model

class WinningNumbers(val numbers: List<LottoNumber>) : LottoNumbers() {
    init {
        validateLottoNumbers(numbers)
    }
}
