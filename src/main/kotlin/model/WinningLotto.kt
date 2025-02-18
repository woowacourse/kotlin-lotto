package model

class WinningLotto(
    winningNumbers: Lotto,
    bonusNumber: LottoNumber,
) {
    init {
        require(!winningNumbers.lottoNumbers.map { it.number }.contains(bonusNumber.number))
    }
}
