package lotto

class WinningLotto(
    vararg lotto: Int,
    val bonusNumber: Int,
) {
    val numbers: Set<Int> = lotto.toSet()
}

class WinningLottoTest
