package lotto

class WinningLotto(
    val winningLotto: Lotto,
    val bounusNumber: Int,
) {
    init {
        require(winningLotto.lottoNums.contains(bounusNumber)) { "보너스 볼 번호는 당첨 번호와 중복될 수 없습니다." }
    }
}
