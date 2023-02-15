package view

import domain.Lotto

class OutputView {
    fun outputGetAmount() {
        println("구입금액을 입력해 주세요.")
    }

    fun outputGetWinningNumbers() {
        println("지난 주 당첨 번호를 입력해 주세요.")
    }

    fun outputGetBonusNumber() {
        println("보너스 볼을 입력해 주세요.")
    }

    fun outputLottos(lottos: List<Lotto>) {
        println("${lottos.size}개를 구매했습니다.")
        lottos.forEach { println(it.toList()) }
        println()
    }
}
