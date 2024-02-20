package view

import model.Lottos

object OutputView {
    fun outputNumberOfLotto(numberOfLotto: Int) {
        println("${numberOfLotto}개를 구매했습니다.")
    }

    fun outputLottos(lottos: Lottos) {
        println(lottos)
    }
}