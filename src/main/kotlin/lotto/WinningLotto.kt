package lotto

class WinningLotto(
    val winningLotto: Lotto,
    val bounusNumber: String,
) {
    init {
        require(bounusNumber.all { it.isDigit() }) { "보너스 볼 번호는 숫자로 입력해주세요" }
        require(bounusNumber.isNotBlank()) { "보너스 볼 번호는 공백이 불가합니다" }
        require(!winningLotto.lottoNums.contains(bounusNumber.toInt())) { "보너스 볼 번호는 당첨 번호와 중복될 수 없습니다." }
    }
}
