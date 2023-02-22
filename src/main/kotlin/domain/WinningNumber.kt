package domain

class WinningNumber(val lottoNumbers: List<LottoNumber>, val bonusNumber: LottoNumber) : Lotto(lottoNumbers) {
    init {
        require(!lottoNumbers.contains(bonusNumber))
    }
}
